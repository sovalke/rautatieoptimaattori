package rautatieoptimaattori.algorithms;

import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;
import rautatieoptimaattori.io.Aineistokasittelija;

public class AstarTest {

    /**
     * Konstruktori.
     */
    public AstarTest() {
    }

    /**
     * Testataan A*-algoritmin toiminta helpossa perustapauksessa (suora yhteys
     * pisteiden välillä).
     *
     * @throws java.lang.Exception
     */
    @Test
    public void etsiReitti1() throws Exception {
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu turku = verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        Solmu tampere = verkko.lisaaAsema("Tampere", 125, 61.498056, 23.760833);
        Solmu oulu = verkko.lisaaAsema("Oulu", 126, 65.013785, 25.472099);
        Solmu seinajoki = verkko.lisaaAsema("Seinäjoki", 127, 62.790278, 22.840278);
        Solmu riihimaki = verkko.lisaaAsema("Riihimäki", 128, 60.738889, 24.772222);
        Solmu lahti = verkko.lisaaAsema("Lahti", 129, 60.983333, 25.655556);

        verkko.lisaaYhteys(helsinki, tampere, 187);
        verkko.lisaaYhteys(helsinki, turku, 193);
        verkko.lisaaYhteys(turku, tampere, 170);
        verkko.lisaaYhteys(tampere, seinajoki, 160);
        verkko.lisaaYhteys(seinajoki, oulu, 335);
        verkko.lisaaYhteys(lahti, riihimaki, 59);
        verkko.lisaaYhteys(lahti, helsinki, 104);
        verkko.lisaaYhteys(tampere, riihimaki, 116);

        Astar a = new Astar(verkko);

        double aVastaus = a.reitinPituus(turku, tampere);
        assertEquals(170, aVastaus, 0.02);
    }

    /**
     * Testataan A*-algoritmin heuristiikkaa.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void etsiReitti2() throws Exception {
        Verkko verkko = new Verkko();
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu turku = verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        Solmu tampere = verkko.lisaaAsema("Tampere", 125, 61.498056, 23.760833);
        Solmu oulu = verkko.lisaaAsema("Oulu", 126, 65.013785, 25.472099);
        Solmu seinajoki = verkko.lisaaAsema("Seinäjoki", 127, 62.790278, 22.840278);
        Solmu riihimaki = verkko.lisaaAsema("Riihimäki", 128, 60.738889, 24.772222);
        Solmu lahti = verkko.lisaaAsema("Lahti", 129, 60.983333, 25.655556);

        verkko.lisaaYhteys(helsinki, tampere, 187);
        verkko.lisaaYhteys(helsinki, turku, 193);
        verkko.lisaaYhteys(turku, tampere, 170);
        verkko.lisaaYhteys(tampere, seinajoki, 160);
        verkko.lisaaYhteys(seinajoki, oulu, 335);
        verkko.lisaaYhteys(lahti, riihimaki, 59);
        verkko.lisaaYhteys(lahti, helsinki, 104);
        verkko.lisaaYhteys(tampere, riihimaki, 116);

        Astar a = new Astar(verkko);

        double vastaus = a.reitinPituus(lahti, turku);
        assertEquals(345, vastaus, 0.02);
    }

    /**
     * Testaa tapauksen, jossa algoritmi käsittelee samaa solmua useaan kertaan.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void etsiReitti3() throws Exception {
        Aineistokasittelija data = new Aineistokasittelija();
        data.lisaaAsemat("./data/testdata/stations.csv");
        data.lisaaYhteydet("./data/testdata/trains.csv");
        Verkko verkko = data.getVerkko();
        Astar r = new Astar(verkko);
        long reitti = r.reitinPituus(verkko.getSolmu(130), verkko.getSolmu(1));

        assertEquals(reitti, 9546000, 0.02);
    }

    /**
     * Katsotaan, toimiiko nullpointerin käsittely oikein (määränpääasemaa ei
     * löydy).
     */
    @Test
    public void nullPointer1() {
        Verkko verkko = new Verkko();
        Solmu[] taulukko = new Solmu[10];
        taulukko[0] = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Astar r = new Astar(verkko);
        String error = "";

        try {
            r.reitinPituus(taulukko[0], taulukko[1]);
        } catch (Exception e) {
            error = e.toString();
        }
        assertEquals(error, "java.lang.NullPointerException");
    }

    /**
     * Katsotaan, toimiiko nullpointerin käsittely oikein (lähtöasemaa ei
     * löydy).
     */
    @Test
    public void nullPointer2() {
        Verkko verkko = new Verkko();
        Solmu[] taulukko = new Solmu[10];
        taulukko[0] = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Astar r = new Astar(verkko);
        String error = "";

        try {
            r.reitinPituus(taulukko[1], taulukko[0]);
        } catch (Exception e) {
            error = e.toString();
        }
        assertEquals(error, "java.lang.NullPointerException");
    }
}
