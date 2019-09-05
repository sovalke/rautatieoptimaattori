package rautatieoptimaattori.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VertailtavaSolmuTest {
    
    /**
     * Konstruktori.
     */
    public VertailtavaSolmuTest() {
    }

    /**
     * Testaa, palauttaako equals-metodi oikean boolean-arvon, kun objektit
     * ovat samat.
     */
    @Test
    public void equalsTest1() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        VertailtavaSolmu testisolmu = new VertailtavaSolmu(helsinki, 500);
        boolean tulos = testisolmu.equals(new VertailtavaSolmu(helsinki, 500));
        assertEquals(tulos, true);
    }
    
    /**
     * Testaa, palauttaako equals-metodi oikean boolean-arvon, kun objektit
     * eivät ole samat / kun tarjottu objekti on väärää oliotyyppiä.
     */
    @Test
    public void equalsTest2() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        Solmu turku = new Solmu("Turku", 124, 60.451389, 22.266667);
        VertailtavaSolmu testisolmu = new VertailtavaSolmu(helsinki, 500);
        boolean tulos = testisolmu.equals(new VertailtavaSolmu(turku, 300));
        boolean tulos2 = testisolmu.equals(turku);
        assertEquals(tulos, false);
        assertEquals(tulos2, false);
    }
    
    /**
     * Testaa, palauttaako compareTo-metodi oikean vertailuarvon.
     */
    @Test
    public void compareTest() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        Solmu turku = new Solmu("Turku", 124, 60.451389, 22.266667);
        VertailtavaSolmu testisolmu = new VertailtavaSolmu(helsinki, 500);
        VertailtavaSolmu testisolmu2 = new VertailtavaSolmu(turku, 200);
        
        int tulos = testisolmu.compareTo(testisolmu2);
        assertEquals(tulos, 300);
    }
    
}
