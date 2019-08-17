package rautatieoptimaattori.domain;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.algorithms.Astar;
import rautatieoptimaattori.algorithms.Dijkstra;
import rautatieoptimaattori.domain.Verkko;
import tietorakenteet.OmaLista;

public class VerkkoTest {

    public VerkkoTest() {
    }

    @Test
    public void luoVerkko() {
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        int vastaus = verkko.getKoko();
        assertEquals(2, vastaus);
    }

    @Test
    public void lisaaYhteys() {
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu turku = verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        int tulos = verkko.lisaaYhteys(turku, helsinki, 180);
        assertEquals(1, tulos);
    }
    
    // Testataan tilannetta, jossa verkkoon yritetään lisätä olematonta asemaa.
    @Test
    public void LisaaOlematonYhteys() {
        Solmu[] taulukko = new Solmu[10];
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        taulukko[0] = helsinki;
        int tulos = verkko.lisaaYhteys(taulukko[0], taulukko[1], 100);
        assertEquals(-1, tulos);
    }

    @Test
    public void onkoSolmua() {
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        boolean status = verkko.onkoSolmua(helsinki);
        assertEquals(true, status);
    }
    
    @Test
    public void getSolmu() throws Exception {
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu solmu = verkko.getSolmu(123);
        assertEquals(solmu.getId(), 123);
    }
    
    @Test
    public void tuplaLisäys() throws Exception {
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        verkko.lisaaAsema("Turku", 123, 60.166640, 24.943536);
        Solmu testi = verkko.getSolmu(123);
        assertEquals(testi.getNimi(), "Turku");
    }
    
    @Test
    public void tuntematonAsema() throws Exception {
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        String error ="";
        try {
            verkko.getSolmu(1);
        }
        catch(Exception e) {
            error = e.toString();
            System.out.println(error);
        }
        assertEquals(error, "java.lang.Exception: Asemaa ei löydy.");
    }
    
    @Test
    public void Reitit() {
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu turku = verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        verkko.lisaaYhteys(turku, helsinki, 180);
        
        OmaLista<String> lista = verkko.Reitit();
        assertEquals(lista.value(0), "Helsinki-Turku 180");
    }

}
