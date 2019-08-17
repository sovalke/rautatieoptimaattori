package rautatieoptimaattori.algorithms;

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


public class DijkstraTest {

    public DijkstraTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    // Tällä testillä testataan Dijkstran algoritmin toiminta helpossa
    // perustapauksessa (suora yhteys pisteiden välillä).
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

        Dijkstra d = new Dijkstra(verkko);

        double Vastaus = d.reitinPituus(124, 125);
        assertEquals(170, Vastaus, 0.02);
    }

    @Test
    public void etsiReitti2() throws Exception {
        // Tapaus, jossa yhteys pisteiden välillä ei ole suora.
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

        Dijkstra d = new Dijkstra(verkko);

        double vastaus = d.reitinPituus(129, 124);
        assertEquals(297, vastaus, 0.02);

    }

}