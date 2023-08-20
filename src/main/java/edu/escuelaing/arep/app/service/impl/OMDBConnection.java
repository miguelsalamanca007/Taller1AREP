package edu.escuelaing.arep.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.escuelaing.arep.app.service.Service;

public class OMDBConnection implements Service {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_KEY = "ae22c37d";
    private static final String GET_URL = "http://www.omdbapi.com/?t=";

    public OMDBConnection() {
    }

    public String getMovieByName(String name) throws IOException {

        String movieInformation = "";
        URL movieURL = new URL(GET_URL + name + "&apikey=" + API_KEY);
        HttpURLConnection connection = (HttpURLConnection) movieURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            movieInformation = movieInformation + response.toString();
            System.out.println(response.toString());
        } else {
            movieInformation = movieInformation + "GET request did not work";
            System.out.println("GET request did not work");
        }
        System.out.println("GET DONE");

        return movieInformation;
    }

}
