package com.disruption.arcilla.samples;

import com.disruption.arcilla.main.Arcilla;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Arcilla arcilla = new Arcilla();
        arcilla.addRestEndpointRequestHandler("/server/list", new ServerListHandler());
        arcilla.startServer();
    }
}
