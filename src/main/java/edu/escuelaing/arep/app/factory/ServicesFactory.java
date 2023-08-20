package edu.escuelaing.arep.app.factory;

import edu.escuelaing.arep.app.service.Service;
import edu.escuelaing.arep.app.service.impl.MovieServices;
import edu.escuelaing.arep.app.service.impl.OMDBConnection;

public class ServicesFactory {
    
    public ServicesFactory(){}

    public Service createServices(String type){

        switch(type) {

            case "MOVIE":
                return new MovieServices();
            case "OMDB":
                return new OMDBConnection();
            default:
                return null;
                
        }
    }
}
