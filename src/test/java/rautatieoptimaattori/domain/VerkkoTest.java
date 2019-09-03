package rautatieoptimaattori.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.tietorakenteet.OmaLista;

public class VerkkoTest {

    /**
     * Konstruktori.
     */
    public VerkkoTest() {
    }

    /**
     * Testaa, onnistuuko verkon luonti ongelmitta.
     */
    @Test
    public void luoVerkko() {
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        int vastaus = verkko.getKoko();
        assertEquals(2, vastaus);
    }

    /**
     * Testaa, onnistuuko yhteyden lisääminen verkkoon.
     */
    @Test
    public void lisaaYhteys() {
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu turku = verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        int tulos = verkko.lisaaYhteys(turku, helsinki, 180);
        assertEquals(1, tulos);
    }
    
    /**
     * Testaa tilannetta, jossa verkkoon yritetään lisätä yhteyttä, jota ei
     * ole olemassa.
     */
    @Test
    public void LisaaOlematonYhteys() {
        Solmu[] taulukko = new Solmu[10];
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        taulukko[0] = helsinki;
        int tulos = verkko.lisaaYhteys(taulukko[0], taulukko[1], 100);
        assertEquals(-1, tulos);
    }

    /**
     * Testaa, toimiiko onkoSolmua-metodi oikein.
     * @throws java.lang.Exception
     */
    @Test
    public void onkoSolmua() throws Exception {
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        boolean status = false;
        
        if (verkko.onkoSolmua(helsinki) != null) {
            status = true;
        }
        
        assertEquals(true, status);
    }
    
    /**
     * Testaa, palauttaako getSolmu-metodi oikean olion.
     * @throws java.lang.Exception
     */
    @Test
    public void getSolmu() throws Exception {
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu solmu = verkko.getSolmu(123);
        assertEquals(solmu.getId(), 123);
    }
    
    /**
     * Testaa tilannetta, jossa verkkoon lisätään samalla id:llä kaksi eri
     * asemaa.
     * @throws java.lang.Exception
     */
    @Test
    public void tuplaLisäys() throws Exception {
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        verkko.lisaaAsema("Turku", 123, 60.166640, 24.943536);
        Solmu testi = verkko.getSolmu(123);
        assertEquals(testi.getNimi(), "Turku");
    }
    
    /**
     * Testaa tilannetta, jossa verkkoon yritetään lisätä olematonta asemaa.
     * @throws java.lang.Exception
     */
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
        }
        assertEquals(error, "");
    }
    
    /**
     * Testaa, palauttaako Reitit-metodi oikeanlaisen syötteen.
     */
    @Test
    public void Reitit() {
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu turku = verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        verkko.lisaaYhteys(turku, helsinki, 180);
        
        OmaLista<String> lista = verkko.reitit();
        assertEquals(lista.value(0), "Helsinki-Turku 180");
    }

}
