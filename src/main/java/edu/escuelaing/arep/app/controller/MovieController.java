package edu.escuelaing.arep.app.controller;

import java.io.IOException;

import edu.escuelaing.arep.app.factory.ServicesFactory;
import edu.escuelaing.arep.app.service.Service;

public class MovieController {

    private Service movieServices = (new ServicesFactory()).createServices("MOVIE");

    public MovieController(){}

    public String getMovieByName(String name) throws IOException{
        return movieServices.getMovieByName(name);
    }

}
