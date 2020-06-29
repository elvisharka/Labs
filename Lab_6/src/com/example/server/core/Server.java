package com.example.server.core;

import com.example.common.Constants;
import com.example.common.component.ConnectionHandler;
import com.example.common.component.PacketReceiver;
import com.example.common.dto.ServerCommandNameAndArg;
import com.example.common.dto.ServerCommandProcessingResult;
import com.example.server.command.ServerCommandContainer;
import com.example.server.command.api.ServerCommand;
import com.example.server.command.impl.Add;
import com.example.server.command.impl.NoCommand;
import com.example.server.command.impl.Remove;
import com.example.server.command.impl.Show;
import com.example.server.component.CollectionHolder;
import com.example.server.component.CommandProcessor;
import com.example.server.component.PacketSender;
import com.example.server.component.ServerRequestReader;
import com.example.server.exception.CannotReadCommandAndArgException;
import com.example.server.repository.api.StudyGroupRepository;
import com.example.server.repository.impl.FileStudyGroupRepository;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Logger;

public class Server {

    public static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private ConnectionHandler connectionHandler;
    private PacketReceiver packetReceiver;
    private ServerRequestReader serverRequestReader;
    private CommandProcessor commandProcessor;
    private PacketSender packetSender;
    private CollectionHolder collectionHolder;
    private StudyGroupRepository repository;

    boolean running;

    public Server(ConnectionHandler connectionHandler, PacketReceiver packetReceiver, ServerRequestReader serverRequestReader,
                  CommandProcessor commandProcessor, PacketSender packetSender, CollectionHolder collectionHolder,
                  StudyGroupRepository repository, boolean running) {
        this.connectionHandler = connectionHandler;
        this.packetReceiver = packetReceiver;
        this.serverRequestReader = serverRequestReader;
        this.commandProcessor = commandProcessor;
        this.packetSender = packetSender;
        this.collectionHolder = collectionHolder;
        this.repository = repository;
        this.running = running;
    }

    public static void main(String[] args) throws SocketException {
        LOGGER.info("Starting server");
        if (args != null && args.length == 1 && args[0] != null && !"".equals(args[0])) {
            executeWithSafe(args);
        } else {
            LOGGER.severe(Constants.LOG_PREFIX_ERROR
                    + ": there is not exactly 1 program argument (name of file expected)");
        }
    }

    private static void executeWithSafe(String[] args) throws SocketException {
        String fileName = args[0];
        System.out.println("File name from args: " + fileName);
        execute(fileName);
    }

    private static void execute(String fileName) throws SocketException {
        StudyGroupRepository studyGroupRepository = new FileStudyGroupRepository(fileName);
        CollectionHolder collectionHolder = new CollectionHolder(
                LocalDateTime.now(), studyGroupRepository.findAll()
        );
        collectionHolder.getStudyGroupMap().forEach(
                (key, value) -> LOGGER.info(key + " => " + value)
        );
        ServerCommandContainer commandContainer = new ServerCommandContainer(
                new HashMap<String, ServerCommand>() {{
                    put(Constants.SERVER_COMMAND_NAME_ADD, new Add(collectionHolder));
                    put(Constants.SERVER_COMMAND_NAME_REMOVE, new Remove(collectionHolder));
                    put(Constants.SERVER_COMMAND_NAME_SHOW, new Show(collectionHolder));
                    put(Constants.SERVER_COMMAND_NAME_NO_COMMAND, new NoCommand());
                }}
        );
        DatagramSocket datagramSocket = new DatagramSocket(Constants.PORT);
        Server server = new Server(
                new ConnectionHandler(datagramSocket),
                new PacketReceiver(datagramSocket),
                new ServerRequestReader(),
                new CommandProcessor(commandContainer),
                new PacketSender(),
                collectionHolder,
                studyGroupRepository,
                true
        );
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            studyGroupRepository.saveAll(collectionHolder);
        }));
        server.run();
    }

    public void run() {
        DatagramSocket socket = null;
        DatagramPacket packet = null;
        while (running) {
            try {
                socket = connectionHandler.getSocket();
                packet = packetReceiver.getPacket(new byte[Constants.BUFFER_SIZE]);
                LOGGER.info("Packet received");
                ServerCommandNameAndArg serverCommandNameAndArg = serverRequestReader.readObject(packet);
                ServerCommandProcessingResult serverCommandProcessingResult =
                        commandProcessor.process(serverCommandNameAndArg, collectionHolder.getStudyGroupMap());
                packetSender.send(socket, packet.getSocketAddress(), serverCommandProcessingResult);
                LOGGER.info("Response sent");
            } catch (CannotReadCommandAndArgException exception) {
                packetSender.send(socket, packet.getSocketAddress(), new ServerCommandProcessingResult(
                        collectionHolder, false, "ERROR. Cannot read command and arg"
                ));
            }
        }
        connectionHandler.closeSocket();
    }
}
