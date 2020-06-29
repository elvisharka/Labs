package com.example.server.component;

import com.example.common.dto.ServerCommandNameAndArg;
import com.example.server.exception.CannotReadCommandAndArgException;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class ServerRequestReader {
    public ServerCommandNameAndArg readObject(DatagramPacket packet) throws CannotReadCommandAndArgException {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(byteStream))) {
            return (ServerCommandNameAndArg) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            throw new CannotReadCommandAndArgException();
        }
    }
}
