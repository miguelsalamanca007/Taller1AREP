package edu.escuelaing.arep.app.service.impl;

import java.io.IOException;

import edu.escuelaing.arep.app.factory.ServicesFactory;
import edu.escuelaing.arep.app.service.Service;

public class MovieServices implements Service {

    public MovieServices() {
    }

    private Service omdbConnection = (new ServicesFactory()).createServices("OMDB");

    public String getMovieByName(String name) throws IOException {
        return omdbConnection.getMovieByName(name);
    }

}
