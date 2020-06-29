package com.example.common.component;

import com.example.server.exception.CannotReceivePacketException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PacketReceiver {
    private DatagramSocket datagramSocket;

    public PacketReceiver(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public DatagramPacket getPacket(byte[] buffer) throws CannotReceivePacketException {
        try {
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);
            return datagramPacket;
        } catch (IOException ioException) {
            throw new CannotReceivePacketException();
        }
    }
}
