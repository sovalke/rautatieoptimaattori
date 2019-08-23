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
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.io.Aineistokasittelija;
import suorituskyky.Suorituskykytesti;
import tietorakenteet.OmaLista;

public class Rautatieoptimaattori {

    public static void main(String[] args) {

        try {
            Aineistokasittelija data = new Aineistokasittelija();
            int asemaLkm = data.lisaaAsemat("./data/stations.csv");
            System.out.println("Asemia on nyt " + asemaLkm + ".");
            
            data.lisaaYhteydet("./data/trains.csv");
            Verkko verkko = data.getVerkko();

            Astar r = new Astar(verkko);
            long reittiB = r.reitinPituus(verkko.getSolmu(130), verkko.getSolmu(1));
            System.out.println("A*:     " + verkko.getSolmu(130).getNimi() + "-" + verkko.getSolmu(1).getNimi() + ": " + reittiB + " ms");

            Dijkstra d = new Dijkstra(verkko);
            long reittiA = d.reitinPituus(verkko.getSolmu(130), verkko.getSolmu(1));
            System.out.println("Dijkstra: " + verkko.getSolmu(130).getNimi() + "-" + verkko.getSolmu(1).getNimi() + ": " + reittiA + " ms");

            OmaLista<String> lista = verkko.Reitit();
            
            // Printataan kaikki yhteydet
//            for (int i = 0; i < lista.size(); i++) {
//                String var = lista.value(i);
//                System.out.println(var + " ms");
//            }
            
            Suorituskykytesti testi = new Suorituskykytesti();
            testi.testaa();

        } catch (Exception ex) {
            System.out.println("VIRHE: " + ex.getMessage());
            System.exit(-1);
        }

    }

}
