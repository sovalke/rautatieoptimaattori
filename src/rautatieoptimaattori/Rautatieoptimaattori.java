package rautatieoptimaattori;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Rautatieoptimaattori {

    public static void main(String[] args) {
        Dijkstra r = new Dijkstra();
        r.lisaaYhteys("Helsinki","Kotka",219);
        r.lisaaYhteys("Helsinki","Lahti",104);
        r.lisaaYhteys("Helsinki","Tampere",187);
        r.lisaaYhteys("Helsinki","Turku",193);
        r.lisaaYhteys("Turku","Tampere",170);
        r.lisaaYhteys("Tampere","Jyväskylä",154);
        r.lisaaYhteys("Tampere","Lahti",175);
        r.lisaaYhteys("Tampere","Seinäjoki",160);
        r.lisaaYhteys("Seinäjoki","Oulu",335);
        r.lisaaYhteys("Seinäjoki","Vaasa",78);
        r.lisaaYhteys("Lahti","Kotka",115);
        r.lisaaYhteys("Kuopio","Oulu",359);
        r.lisaaYhteys("Kuopio","Joensuu",169);
        r.lisaaYhteys("Kuopio","Jyväskylä",164);
        
        // System.out.println("KÄYTÖSSÄ OLEVAT RADANPÄTKÄT");
        //r.tulostaReitit();
        System.out.println("-------------------------------------------");

        System.out.println("Dijkstra vauhdissa...");
        r.reitinPituus("Vaasa", "Helsinki");
        r.reitinPituus("Turku", "Kuopio");
        r.reitinPituus("Turku", "Helsinki");
        r.reitinPituus("Oulu", "Helsinki");
        r.reitinPituus("Oulu", "Tampere");
        
        System.out.println("-------------------------------------------");
        System.out.println("A* vauhdissa...");
        Astar a = new Astar();
        a.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        a.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        a.lisaaAsema("Tampere", 125, 61.498056, 23.760833);
        a.lisaaAsema("Oulu", 126, 65.013785, 25.472099);
        a.lisaaAsema("Seinäjoki", 127, 62.790278, 22.840278);
        
        a.lisaaYhteys("Helsinki", "Tampere", 187);
        a.lisaaYhteys("Helsinki","Turku",193);
        a.lisaaYhteys("Seinäjoki","Oulu",335);
        a.lisaaYhteys("Tampere","Seinäjoki",160);
        a.tulostaReitit();
        
        a.reitinPituus("Turku", "Tampere");
        a.reitinPituus("Oulu", "Helsinki");
        a.reitinPituus("Oulu", "Tampere");
        
        
        
    }

}
