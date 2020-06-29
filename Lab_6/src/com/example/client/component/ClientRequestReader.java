package com.example.client.component;

import com.example.client.exception.CannotReadCommandProcessingResultException;
import com.example.common.dto.ServerCommandProcessingResult;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class ClientRequestReader {
    public ServerCommandProcessingResult read(DatagramPacket packet) throws CannotReadCommandProcessingResultException {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
             ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(byteStream))) {
            return (ServerCommandProcessingResult) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            throw new CannotReadCommandProcessingResultException();
        }
    }
}
