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
        

        System.out.println("KÄYTÖSSÄ OLEVAT RADANPÄTKÄT");
        r.tulostaReitit();
        System.out.println("-----------------------");

        r.reitinPituus("Vaasa", "Helsinki");
        r.reitinPituus("Turku", "Kuopio");
        r.reitinPituus("Turku", "Helsinki");
        
        Astar a = new Astar();
        a.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        a.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        a.lisaaAsema("Tampere", 125, 61.498056, 23.760833);
    }

}
