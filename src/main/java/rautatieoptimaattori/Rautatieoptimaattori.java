package rautatieoptimaattori;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import rautatieoptimaattori.algorithms.Astar;
import rautatieoptimaattori.algorithms.Dijkstra;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import rautatieoptimaattori.io.Aineistokasittelija;

public class Rautatieoptimaattori {

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        
//        Verkko verkko = new Verkko();
//        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
//        verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
//        verkko.lisaaAsema("Tampere", 125, 61.498056, 23.760833);
//        verkko.lisaaAsema("Oulu", 126, 65.013785, 25.472099);
//        verkko.lisaaAsema("Seinäjoki", 127, 62.790278, 22.840278);
//        verkko.lisaaAsema("Riihimäki", 128, 60.738889, 24.772222);
//        verkko.lisaaAsema("Lahti", 129, 60.983333, 25.655556);
//        
//        verkko.lisaaYhteys("Helsinki","Tampere",187);
//        verkko.lisaaYhteys("Helsinki","Turku",193);
//        verkko.lisaaYhteys("Turku","Tampere",170);
//        verkko.lisaaYhteys("Tampere","Seinäjoki",160);
//        verkko.lisaaYhteys("Seinäjoki","Oulu",335); 
//        verkko.lisaaYhteys("Lahti","Riihimäki",59);
//        verkko.lisaaYhteys("Lahti","Helsinki",104);
//        verkko.lisaaYhteys("Tampere","Riihimäki",116);
//        
//        
//        Dijkstra r = new Dijkstra(verkko);
//
//        verkko.tulostaReitit();
//        System.out.println("-------------------------------------------");
//
//        System.out.println("Dijkstra vauhdissa...");
//        r.reitinPituus("Turku", "Tampere");
//        r.reitinPituus("Turku", "Helsinki");
//        r.reitinPituus("Oulu", "Helsinki");
//        r.reitinPituus("Oulu", "Tampere");
//        r.reitinPituus("Lahti", "Turku");
//        
//        System.out.println("-------------------------------------------");
//        System.out.println("A* vauhdissa...");
//        
//        Astar a = new Astar(verkko);
//        a.reitinPituus("Turku", "Tampere");
//        a.reitinPituus("Turku", "Helsinki");
//        a.reitinPituus("Oulu", "Helsinki");
//        a.reitinPituus("Oulu", "Tampere");
//        a.reitinPituus("Tampere", "Oulu");
//        a.reitinPituus("Lahti", "Turku");
//        System.out.println(a.reitinPituus("Lahti", "Turku") + r.reitinPituus("Lahti", "Turku"));
        
        Aineistokasittelija data = new Aineistokasittelija();
        data.lisaaAsemat("./data/stations.csv");
        data.lisaaYhteydet("./data/trains.csv");
        Verkko verkko = data.getVerkko();
        
        verkko.getKoko();
        Astar r = new Astar(verkko);
        r.reitinPituus(130, 1);
        
        Dijkstra d = new Dijkstra(verkko);
        d.reitinPituus(130, 1);
        
    }

}
