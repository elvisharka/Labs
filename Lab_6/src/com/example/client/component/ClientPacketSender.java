package com.example.client.component;

import com.example.common.Constants;
import com.example.common.dto.ServerCommandNameAndArg;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientPacketSender {
    private final DatagramSocket datagramSocket;
    private final InetAddress inetAddress;

    public ClientPacketSender(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public void send(ServerCommandNameAndArg serverCommandNameAndArg, int port) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Constants.BUFFER_SIZE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        objectOutputStream.flush();
        objectOutputStream.writeObject(serverCommandNameAndArg);
        objectOutputStream.flush();
        byte[] sendBuf = byteArrayOutputStream.toByteArray();
        DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, inetAddress, port);
        datagramSocket.send(packet);
        objectOutputStream.close();
    }
}
