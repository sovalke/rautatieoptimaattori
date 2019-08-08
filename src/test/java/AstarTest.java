
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.Solmu;
import rautatieoptimaattori.algorithms.Astar;
import rautatieoptimaattori.algorithms.Dijkstra;
import rautatieoptimaattori.Verkko;

public class AstarTest {

    public AstarTest() {
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
//     Tällä testillä testataan A*-algoritmin toiminta helpossa
//     perustapauksessa (suora yhteys pisteiden välillä).
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

//    @Test
//    public void etsiReitti2() {
//        // Tällä testillä testataan A*-algoritmin heuristiikkaa.
//        // Matkalla Lahdesta Turkuun lyhin reitti kulkee Helsingin kautta, mutta
//        // A* kulkee heuristiikan takia Riihimäen ja Tampereen kautta. Niinpä
//        // Dijkstra palauttaa reitin pituudeksi 297 km, mutta A* 345 km.
//        Verkko verkko = new Verkko();
//        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
//        verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
//        verkko.lisaaAsema("Tampere", 125, 61.498056, 23.760833);
//        verkko.lisaaAsema("Oulu", 126, 65.013785, 25.472099);
//        verkko.lisaaAsema("Seinäjoki", 127, 62.790278, 22.840278);
//        verkko.lisaaAsema("Riihimäki", 128, 60.738889, 24.772222);
//        verkko.lisaaAsema("Lahti", 129, 60.983333, 25.655556);
//
//        verkko.lisaaYhteys("Helsinki", "Tampere", 187);
//        verkko.lisaaYhteys("Helsinki", "Turku", 193);
//        verkko.lisaaYhteys("Turku", "Tampere", 170);
//        verkko.lisaaYhteys("Tampere", "Seinäjoki", 160);
//        verkko.lisaaYhteys("Seinäjoki", "Oulu", 335);
//        verkko.lisaaYhteys("Lahti", "Riihimäki", 59);
//        verkko.lisaaYhteys("Lahti", "Helsinki", 104);
//        verkko.lisaaYhteys("Tampere", "Riihimäki", 116);
//
//        Astar a = new Astar(verkko);
//
//        double vastaus = a.reitinPituus("Lahti", "Turku");
//        assertEquals(345.0, vastaus, 0.02);
//
//    }
}
