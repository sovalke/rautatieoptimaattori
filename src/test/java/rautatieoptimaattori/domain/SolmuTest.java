package rautatieoptimaattori.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.tietorakenteet.OmaHashMap;

public class SolmuTest {
    
    /**
     * Konstruktori.
     */
    public SolmuTest() {
    }

    /**
     * Testaa, onnistuuko solmun luominen ja id:n hakeminen.
     */
    @Test
    public void luoSolmu1() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        assertEquals(helsinki.getId(), 1);
    }
    
    /**
     * Testaa, onnistuuko solmun luominen ja nimen hakeminen.
     */
    @Test
    public void luoSolmu2() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        assertEquals(helsinki.getNimi(), "Helsinki");
    }
    
    /**
     * Testaa, onnistuuko solmun ja yhteyden luominen ja etäisyyden hakeminen.
     */
    @Test
    public void luoSolmu3() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        Solmu turku = new Solmu("Turku", 124, 60.451389, 22.266667);
        helsinki.lisaaYhteys(turku, 180);
        assertEquals(helsinki.getEtaisyys(turku), 180);
    }
    
    /**
     * Testaa, onnistuuko solmun ja yhteyden luominen ja naapuritietojen hake-
     * minen.
     */
    @Test
    public void luoSolmu4() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        Solmu turku = new Solmu("Turku", 124, 60.451389, 22.266667);
        helsinki.lisaaYhteys(turku, 180);
        OmaHashMap naapurit = helsinki.getNaapurit();
        assertEquals(naapurit.size(), 1);
    }
    
    /**
     * Testaa, toimiiko koordinaattien haku odotetusti.
     */
    @Test
    public void haeXY() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        double x = helsinki.getX() / 1000000;
        double y = helsinki.getY() / 1000000;
        assertEquals(x, 60.166640, 0.00);
        assertEquals(y, 24.943536, 0.00);
    }
    
    /**
     * Testaa, toimiiko setNimi-setteri oikein.
     */
    @Test
    public void setNimi() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        int i = helsinki.setNimi("Helsingfors");
        assertEquals(i, 1);
    }
    
    /**
     * Testaa, toimiiko ohjelma oikein, kun asemalle yritetään antaa nimeksi
     * tyhjä merkkijono.
     */
    @Test
    public void setNimi2() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        int i = helsinki.setNimi("");
        assertEquals(i, -1);
    }
    
    /**
     * Testaa, toimiiko koordinaattisetteri oikein.
     */
    @Test
    public void setXY() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        int i = helsinki.setXY((long) 60.2, (long) 24.95);
        assertEquals(i, 1);
    }
    
    /**
     * Testaa, toimiiko id-setteri oikein.
     */
    @Test
    public void setId() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        int i = helsinki.setId(2);
        assertEquals(i, 1);
    }
}
