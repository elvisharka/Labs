package com.example.server.component;

import com.example.common.Constants;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class PacketSender {
    public void send(DatagramSocket socket, SocketAddress socketAddress, Object object) {
        try (ByteArrayOutputStream byteArrayOutputStream =
                        new ByteArrayOutputStream(Constants.BUFFER_SIZE);
                ObjectOutputStream objectOutputStream =
                        new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream))) {
            objectOutputStream.flush();
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            byte[] bufferToSend = byteArrayOutputStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(bufferToSend, bufferToSend.length, socketAddress);
            socket.send(packet);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
