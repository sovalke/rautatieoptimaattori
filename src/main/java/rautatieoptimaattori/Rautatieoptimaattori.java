package rautatieoptimaattori;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import rautatieoptimaattori.algorithms.Astar;
import rautatieoptimaattori.algorithms.Dijkstra;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import rautatieoptimaattori.io.Aineistokasittelija;

public class Rautatieoptimaattori {

    public static void main(String[] args) {
        
        try {
            Aineistokasittelija data = new Aineistokasittelija();
            int asemaLkm = data.lisaaAsemat("./data/stations.csv");
            System.out.println("Asemia on nyt " + asemaLkm + ".");
            
            data.lisaaYhteydet("./data/trains.csv");
            Verkko verkko = data.getVerkko();
            
            verkko.tulostaReitit();
            
            Astar r = new Astar(verkko);
            r.reitinPituus(verkko.getSolmu(130), verkko.getSolmu(1));
            
            Dijkstra d = new Dijkstra(verkko);
            d.reitinPituus(130, 1);
            
        } catch (Exception ex) {
            System.out.println( "VIRHE: " + ex.getMessage() );
            System.exit(-1);
        }
        
    }

}
