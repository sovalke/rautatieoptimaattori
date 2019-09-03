package rautatieoptimaattori;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import rautatieoptimaattori.algorithms.Astar;
import rautatieoptimaattori.algorithms.Dijkstra;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.io.Aineistokasittelija;
import rautatieoptimaattori.suorituskyky.Suorituskykytesti;
import rautatieoptimaattori.tietorakenteet.OmaLista;

public class Rautatieoptimaattori {

    /**
     * Pääluokka. Tänne laitetaan kaikki suoritettava tavara.
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        Aineistokasittelija data = new Aineistokasittelija();
        int asemaLkm = data.lisaaAsemat("./data/stations.csv");
        System.out.println("Asemia on nyt " + asemaLkm + ".");
            
        data.lisaaYhteydet("./data/trains.csv");
        Verkko verkko = data.getVerkko();
        
        
        // Tulostaa kaikki asemat id-järjestyksessä.
        
        Solmu[] asemat = verkko.asemat();
        Arrays.sort(asemat);
        
        for (Object asema : asemat) {
            Solmu a = (Solmu) asema;
            System.out.println(a.getId() + " " + a.getNimi());
        }

//        Astar r = new Astar(verkko);
//        long reittiB = r.reitinPituus(verkko.getSolmu(130), verkko.getSolmu(1));
//        System.out.println("A*:     " + verkko.getSolmu(130).getNimi() + "-" 
//                + verkko.getSolmu(1).getNimi() + ": " + reittiB + " ms");
//
//        Dijkstra d = new Dijkstra(verkko);
//        long reittiA = d.reitinPituus(verkko.getSolmu(130), verkko.getSolmu(1));
//        System.out.println("Dijkstra: " + verkko.getSolmu(130).getNimi() + "-" 
//                + verkko.getSolmu(1).getNimi() + ": " + reittiA + " ms");

//                Suorituskykytesti testi = new Suorituskykytesti();
//                testi.testaa();
    }

}
