package edu.escuelaing.arep.app.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import edu.escuelaing.arep.app.controller.MovieController;
import edu.escuelaing.arep.app.view.movieSearchEngine;

public class WebServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverInstance = null;
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        MovieController movieController = new MovieController();

        try {
            serverInstance = new ServerSocket(16000);
        } catch (IOException error) {
            System.err.println("Couldn't listen on port 160000");
            System.exit(1);
        }

        Socket clientConnection = null;

        while (!(serverInstance.isClosed())) {
            try {
                System.out.println("\nListening...");
                clientConnection = serverInstance.accept();
                System.out.println("Connection Established!");
            } catch (IOException error2) {
                System.out.println("Couldn't listen on port 16000");
                System.exit(1);
            }

            PrintWriter clientResponseWriter = new PrintWriter(clientConnection.getOutputStream(), true);
            BufferedReader clientRequestReader = new BufferedReader(
                    new InputStreamReader(clientConnection.getInputStream()));

            String inputLine;
            String movieInformation = "";
            String resource = "";
            String movieName = "";

            boolean isFirstLine = true;

            while ((inputLine = clientRequestReader.readLine()) != null) {

                if (isFirstLine) {
                    System.out.println("Input line " + inputLine);
                    resource = inputLine.split("/")[1].split(" ")[0];
                    movieName = inputLine.split("/")[2].split(" ")[0];
                    isFirstLine = false;
                }

                if (!clientRequestReader.ready()) {
                    break;
                }
            }

            if (resource.equals("movie")) {
                if (cache.containsKey(movieName)) {
                    movieInformation = cache.get(movieName);
                } else {
                    movieInformation = movieController.getMovieByName(movieName);
                    cache.put(movieName, resource);
                }
                clientResponseWriter.println("HTTP/1.1 200 OK");
                clientResponseWriter.println("Content-Type: application/json");
                clientResponseWriter.println("Content-Length: 343");
                clientResponseWriter.println();
                clientResponseWriter.println(movieInformation);
            } else if (resource.equals("home") || resource.equals("") || resource.equals(" ")) {
                clientResponseWriter.println(movieSearchEngine.getHomePage());
            } else {
                System.out.println("Nothing has been entered\n");
            }

            clientResponseWriter.close();
            clientRequestReader.close();
            clientConnection.close();

        }

        serverInstance.close();
    }
}