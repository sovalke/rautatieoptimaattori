package rautatieoptimaattori.algorithms;

import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.Verkko;


public class DijkstraTest {

    /**
     * Konstruktori.
     */
    public DijkstraTest() {
    }

    /**
     * Testataan Dijkstran algoritmin toiminta helpossa (suora yhteys pisteiden
     * välillä).
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

        Dijkstra d = new Dijkstra(verkko);

        double Vastaus = d.reitinPituus(turku, tampere);
        assertEquals(170, Vastaus, 0.02);
    }

    /**
     * Testaa tapauksen, jossa yhteys pisteiden välillä ei ole suora.
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
        verkko.lisaaYhteys(riihimaki, helsinki, 69);

        Dijkstra d = new Dijkstra(verkko);

        double vastaus = d.reitinPituus(lahti, turku);
        assertEquals(297, vastaus, 0.02);
    }
    
    /**
     * Testaa tapauksen, jossa algoritmi käsittelee samaa solmua useaan kertaan.
     * @throws java.lang.Exception
     */
    @Test
    public void etsiReitti3() throws Exception {
        Verkko verkko = new Verkko();
        Solmu pasila = verkko.lisaaAsema("Pasila", 10, 60.198689,24.933521);
        Solmu kapyla = verkko.lisaaAsema("Käpylä", 977, 60.22017, 24.946034);
        Solmu oulunkyla = verkko.lisaaAsema("Oulunkylä", 15, 60.229, 24.967676);
        Solmu pukinmaki = verkko.lisaaAsema("Pukinmäki", 551, 60.242216, 24.993288);
        Solmu malmi = verkko.lisaaAsema("Malmi", 17, 60.25166, 25.011468);
        Solmu tapanila = verkko.lisaaAsema("Tapanila", 552, 60.2639, 25.030033);
        Solmu puistola = verkko.lisaaAsema("Puistola", 553, 60.276313, 25.036683);
        Solmu tikkurila = verkko.lisaaAsema("Tikkurila", 18, 60.292166, 25.044055);
        Solmu hiekkaharju = verkko.lisaaAsema("Hiekkaharju", 556, 60.303081, 25.049104);
        Solmu leinela = verkko.lisaaAsema("Leinelä", 1333, 60.322613, 25.039847);
        Solmu lentoasema = verkko.lisaaAsema("Lentoasema", 1332, 60.315732, 24.968343);
        Solmu aviapolis = verkko.lisaaAsema("Aviapolis", 1331, 60.30435, 24.956191);
        Solmu kivisto = verkko.lisaaAsema("Kivistö", 1330, 60.314607, 24.846938);
        Solmu vehkala = verkko.lisaaAsema("Vehkala", 1337, 60.295054, 24.843716);
        Solmu vantaankoski = verkko.lisaaAsema("Vantaankoski", 839, 60.285681, 24.848271);
        Solmu martinlaakso = verkko.lisaaAsema("Martinlaakso", 662, 60.27808, 24.852604);
        Solmu louhela = verkko.lisaaAsema("Louhela", 661, 60.270798, 24.853299);
        Solmu myyrmaki = verkko.lisaaAsema("Myyrmäki", 660, 60.261327, 24.854751);
        Solmu malminkartano = verkko.lisaaAsema("Malminkartano", 659, 60.249316, 24.861283);
        Solmu kannelmaki = verkko.lisaaAsema("Kannelmäki", 658, 60.239562, 24.877004);
        Solmu phaaga = verkko.lisaaAsema("Pohjois-Haaga", 657, 60.230078, 24.883252);
        Solmu huopalahti = verkko.lisaaAsema("Huopalahti", 72, 60.21835,24.893523);
        Solmu valimo = verkko.lisaaAsema("Valimo", 847, 60.222172, 24.8758);

        verkko.lisaaYhteys(pasila, kapyla, 3);
        verkko.lisaaYhteys(kapyla, oulunkyla, 1);
        verkko.lisaaYhteys(oulunkyla, pukinmaki, 2);
        verkko.lisaaYhteys(pukinmaki, malmi, 1);
        verkko.lisaaYhteys(malmi, tapanila, 2);
        verkko.lisaaYhteys(tapanila, puistola, 2);
        verkko.lisaaYhteys(puistola, tikkurila, 2);
        verkko.lisaaYhteys(tikkurila, hiekkaharju, 2);
        verkko.lisaaYhteys(hiekkaharju, leinela, 3);
        verkko.lisaaYhteys(leinela, lentoasema, 4);
        verkko.lisaaYhteys(lentoasema, aviapolis, 2);
        verkko.lisaaYhteys(aviapolis, kivisto, 6);
        verkko.lisaaYhteys(kivisto, vehkala, 2);
        verkko.lisaaYhteys(vehkala, vantaankoski, 1);
        verkko.lisaaYhteys(vantaankoski, martinlaakso, 1);
        verkko.lisaaYhteys(martinlaakso, louhela, 1);
        verkko.lisaaYhteys(louhela, myyrmaki, 1);
        verkko.lisaaYhteys(myyrmaki, malminkartano, 2);
        verkko.lisaaYhteys(malminkartano, kannelmaki, 2);
        verkko.lisaaYhteys(kannelmaki, phaaga, 1);
        verkko.lisaaYhteys(phaaga, huopalahti, 2);
        verkko.lisaaYhteys(huopalahti, pasila, 3);
        verkko.lisaaYhteys(huopalahti, valimo, 1);
        verkko.lisaaYhteys(tikkurila, valimo, 15);

        Dijkstra d = new Dijkstra(verkko);
        double vastaus = d.reitinPituus(aviapolis, valimo);
        assertEquals(20, vastaus, 0.02);
    }
    
    /**
     * Tarkistaa, toimiiko nullpointerin käsittely oikein (määränpääasemaa ei
     * löydy).
     */
    @Test
    public void nullPointer1() {
        Verkko verkko = new Verkko();
        Solmu[] taulukko = new Solmu[10];
        taulukko[0] = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Dijkstra d = new Dijkstra(verkko);
        String error ="";
        
        try {
            d.reitinPituus(taulukko[0], taulukko[1]);
        }
        catch(Exception e) {
            error = e.toString();
        }
        assertEquals(error, "java.lang.Exception: Kääk! Määränpääasemaa ei löydy!");
    }
    
    @Test
    /**
     * Tarkistaa, toimiiko nullpointerin käsittely oikein (lähtöasemaa ei löydy).
     */
    public void nullPointer2() {
        Verkko verkko = new Verkko();
        Solmu[] taulukko = new Solmu[10];
        taulukko[0] = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Dijkstra d = new Dijkstra(verkko);
        String error ="";
        
        try {
            d.reitinPituus(taulukko[1], taulukko[0]);
        }
        catch(Exception e) {
            error = e.toString();
        }
        assertEquals(error, "java.lang.Exception: Kääk! Lähtöasemaa ei löydy!");
    }

}
