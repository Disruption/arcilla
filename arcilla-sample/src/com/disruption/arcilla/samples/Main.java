package com.disruption.arcilla.samples;

import com.disruption.arcilla.main.Arcilla;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // SETUP - INITIALIZE

        // Create a new Arcilla server. By default it binds to localhost, port 80
        // Constructors for specifying a different ip to bind, or port are provided
        Arcilla arcilla = new Arcilla();


        // SETUP - ADD REST ENDPOINTS

        // To add a rest endpoint to be recognized by the server you just need to call this method
        // which binds the relative path provided to be processed by the specified handler
        arcilla.addRestEndpointRequestHandler("/server/list", new ServerListHandler());

        // START THE SERVER

        // After you have created and set up the arcilla server to your likings, just call
        // startServer and everything will be up and running almost instantly
        // After start server, you can still add and remove rest endpoints "on the fly"
        arcilla.startServer();

        // FINALIZE THE SERVER
        // If the lifecycle of the server is not tied to your application's lifecycle, you can
        // simply call stopServer() to make it halt, but be aware it can not be reused afterwards.
    }
}
