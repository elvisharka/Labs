package com.example.common.component;

import java.net.DatagramSocket;

public class ConnectionHandler {
    private final DatagramSocket socket;

    public ConnectionHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void closeSocket() {
        socket.close();
    }
}
