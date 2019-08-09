
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

//    @Test
//    public void tulostaReitit() {
//        Verkko verkko = new Verkko();
//        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
//        Solmu turku = verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
//        String tuloste = verkko.tulostaReitit();
//    }

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
        Solmu helsinki = verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        Solmu solmu = verkko.getSolmu(123);
        assertEquals(solmu.getId(), 123);
    }

}
