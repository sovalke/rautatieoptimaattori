package rautatieoptimaattori;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Rautatieoptimaattori {

    public static void main(String[] args) {
        
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        verkko.lisaaAsema("Tampere", 125, 61.498056, 23.760833);
        verkko.lisaaAsema("Oulu", 126, 65.013785, 25.472099);
        verkko.lisaaAsema("Seinäjoki", 127, 62.790278, 22.840278);
        
        verkko.lisaaYhteys("Helsinki","Tampere",187);
        verkko.lisaaYhteys("Helsinki","Turku",193);
        verkko.lisaaYhteys("Turku","Tampere",170);
        verkko.lisaaYhteys("Tampere","Seinäjoki",160);
        verkko.lisaaYhteys("Seinäjoki","Oulu",335); 
        
        
        Dijkstra r = new Dijkstra( verkko );

        // System.out.println("KÄYTÖSSÄ OLEVAT RADANPÄTKÄT");
        verkko.tulostaReitit();
        System.out.println("-------------------------------------------");

        System.out.println("Dijkstra vauhdissa...");
        r.reitinPituus("Turku", "Tampere");
        r.reitinPituus("Turku", "Helsinki");
        r.reitinPituus("Oulu", "Helsinki");
        r.reitinPituus("Oulu", "Tampere");
        
        System.out.println("-------------------------------------------");
        System.out.println("A* vauhdissa...");
        
        Astar a = new Astar(verkko);
        a.reitinPituus("Turku", "Tampere");
        a.reitinPituus("Turku", "Helsinki");
        a.reitinPituus("Oulu", "Helsinki");
        a.reitinPituus("Oulu", "Tampere");
        a.reitinPituus("Tampere", "Oulu");
        
        
        
    }

}
