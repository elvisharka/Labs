package com.example.client.core;

import com.example.client.command.impl.Remove;
import com.example.client.command.impl.Show;
import com.example.common.Constants;
import com.example.client.StudyGroupValidator;
import com.example.client.command.ClientCommandContainer;
import com.example.client.command.api.ClientCommand;
import com.example.client.command.impl.Add;
import com.example.client.command.impl.NoCommand;
import com.example.client.component.ClientPacketSender;
import com.example.client.component.ClientRequestReader;
import com.example.client.exception.CannotReadCommandProcessingResultException;
import com.example.client.exception.ClientCommandExecutionInvalidValuesException;
import com.example.common.component.ConnectionHandler;
import com.example.common.component.PacketReceiver;
import com.example.common.dto.ServerCommandNameAndArg;
import com.example.common.dto.ServerCommandProcessingResult;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Scanner;

public class Client {

    private final PacketReceiver packetReceiver;
    private final ClientRequestReader clientRequestReader;
    private final ClientPacketSender clientPacketSender;
    private final ConnectionHandler connectionHandler;

    public Client(PacketReceiver packetReceiver, ClientRequestReader clientRequestReader,
                  ClientPacketSender clientPacketSender, ConnectionHandler connectionHandler) {
        this.packetReceiver = packetReceiver;
        this.clientRequestReader = clientRequestReader;
        this.clientPacketSender = clientPacketSender;
        this.connectionHandler = connectionHandler;
    }

    public static void main(String[] args) throws IOException, CannotReadCommandProcessingResultException {
        ClientCommandContainer commandContainer = initClientCommandContainer();
        DatagramSocket datagramSocket = new DatagramSocket();
        Client client = new Client(
                new PacketReceiver(datagramSocket),
                new ClientRequestReader(),
                new ClientPacketSender(datagramSocket, InetAddress.getByName(Constants.LOCALHOST)),
                new ConnectionHandler(datagramSocket)
        );
        commandContainer.getClientCommandMap().forEach((key, value) -> System.out.println(key + " => " + value.description()));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command name");
        while (true) {
            String commandName = scanner.nextLine();
            if ("exit".equals(commandName)) {
                break;
            }
            ClientCommand clientCommand = commandContainer.findCommandByName(commandName);
            if (clientCommand instanceof NoCommand) {
                System.out.println("Such command does not exist");
                continue;
            }
            ServerCommandNameAndArg serverCommandNameAndArg;
            System.out.println("Command execution start");
            try {
                serverCommandNameAndArg = clientCommand.execute();
            } catch (ClientCommandExecutionInvalidValuesException exception) {
                System.out.println("Error when execution command: some of provided values are invalid");
                continue;
            }
            client.clientPacketSender.send(serverCommandNameAndArg, Constants.PORT);
            DatagramPacket datagramPacket = client.packetReceiver.getPacket(new byte[Constants.BUFFER_SIZE]);
            ServerCommandProcessingResult result = client.clientRequestReader.read(datagramPacket);
            System.out.println("Result of processing: " + (result.isResultOk ? "OK" : "ERROR"));
            System.out.println("Result description: " + result.description);
            System.out.println("Current state of collection: ");
            if (result.currentCollectionHolder.isEmpty()) {
                System.out.println("Collection is empty");
            } else {
                result.currentCollectionHolder.getStudyGroupMap()
                        .forEach((key, value) -> System.out.println(key + " => " + value));
            }
            System.out.println("Command execution finish");
        }
        client.connectionHandler.closeSocket();
    }

    private static ClientCommandContainer initClientCommandContainer() {
        return new ClientCommandContainer (
                new HashMap<String, ClientCommand>() {{
                    put(Constants.CLIENT_COMMAND_NAME_ADD, new Add(new StudyGroupValidator()));
                    put(Constants.CLIENT_COMMAND_NAME_REMOVE, new Remove());
                    put(Constants.CLIENT_COMMAND_NAME_SHOW, new Show());
                    put(Constants.CLIENT_COMMAND_NAME_NO_COMMAND, new NoCommand());
                }}
        );
    }

}
