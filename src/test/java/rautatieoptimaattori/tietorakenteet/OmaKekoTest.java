package rautatieoptimaattori.tietorakenteet;

import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.domain.Solmu;
import rautatieoptimaattori.domain.VertailtavaSolmu;

public class OmaKekoTest {

    /**
     * Konstruktori.
     */
    public OmaKekoTest() {
    }

    /**
     * Luo uuden keon ja tarkistaa sen koon.
     */
    @Test
    public void sizeTest() {
        OmaKeko keko = new OmaKeko(5);
        OmaKeko keko0 = new OmaKeko(-5);
        assertEquals(keko.size(), 6);
        assertEquals(keko0.size(), 1);
    }

    /**
     * Lisää uusia solmuja kekoon.
     */
    @Test
    public void addTest() {
        VertailtavaSolmu helsinki = new VertailtavaSolmu(new Solmu("Helsinki",
                1, 60.166640, 24.943536), 100);
        OmaKeko keko = new OmaKeko(5);
        assertEquals(keko.add(helsinki), true);
    }

    /**
     * Testaa, hakeeko peek-metodi pienimmän solmun oikein.
     */
    @Test
    public void peekTest() {
        VertailtavaSolmu turku = new VertailtavaSolmu(new Solmu("Turku", 
                124, 60.451389, 22.266667), 180);
        VertailtavaSolmu helsinki = new VertailtavaSolmu(new Solmu("Helsinki",
                1, 60.166640, 24.943536), 100);
        VertailtavaSolmu vaasa = new VertailtavaSolmu(new Solmu("Vaasa", 
                288, 63.097786, 21.621824), 500);
        OmaKeko keko = new OmaKeko(5);
        
        keko.add(turku);
        keko.add(helsinki);
        keko.add(vaasa);
        
        VertailtavaSolmu pienin = keko.peek();
        assertEquals(100, pienin.etaisyys, 0.0);
    }
    
    /**
     * Testaa, hakeeko ja poistaako poll-metodi pienimmän solmun oikein.
     */
    @Test
    public void pollTest() {
        
        VertailtavaSolmu turku = new VertailtavaSolmu(new Solmu("Turku", 
                124, 60.451389, 22.266667), 180);
        VertailtavaSolmu helsinki = new VertailtavaSolmu(new Solmu("Helsinki",
                1, 60.166640, 24.943536), 100);
        VertailtavaSolmu vaasa = new VertailtavaSolmu(new Solmu("Vaasa", 
                288, 63.097786, 21.621824), 500);
        OmaKeko keko = new OmaKeko(5);
        
        keko.add(turku);
        keko.add(helsinki);
        keko.add(vaasa);
        
        VertailtavaSolmu pienin = keko.poll();
        VertailtavaSolmu toiseksiPienin = keko.poll();
        keko.poll();
        
        assertEquals(100, pienin.etaisyys, 0.0);
        assertEquals(180, toiseksiPienin.etaisyys, 0.0);
        assertEquals(keko.poll(), null);
    }
    
    /**
     * Testaa, toimiiko metodi isEmpty oikein.
     */
    @Test
    public void isEmptyTest() {
        
        VertailtavaSolmu turku = new VertailtavaSolmu(new Solmu("Turku", 
                124, 60.451389, 22.266667), 180);
        VertailtavaSolmu helsinki = new VertailtavaSolmu(new Solmu("Helsinki",
                1, 60.166640, 24.943536), 100);

        OmaKeko keko = new OmaKeko(5);
        
        keko.add(turku);
        keko.add(helsinki);
        
        keko.poll();
        boolean onTyhja = keko.isEmpty();
        keko.poll();
        boolean onTyhja2 = keko.isEmpty();
        
        assertEquals(onTyhja, false);
        assertEquals(onTyhja2, true);
    }

    /**
     * Testaa, toimivatko metodit onlyOne ja twoOrMore oikein.
     */
    @Test
    public void oneTwoTest() {
        
        VertailtavaSolmu turku = new VertailtavaSolmu(new Solmu("Turku", 
                124, 60.451389, 22.266667), 180);
        VertailtavaSolmu helsinki = new VertailtavaSolmu(new Solmu("Helsinki",
                1, 60.166640, 24.943536), 100);

        OmaKeko keko = new OmaKeko(5);
        keko.add(turku);
        boolean vainYksi = keko.onlyOne(); // true
        boolean kaksiTaiEnemman = keko.twoOrMore(); // false
        
        keko.add(helsinki);
        boolean vainYksi2 = keko.onlyOne(); // false
        boolean kaksiTaiEnemman2 = keko.twoOrMore(); // true
        
        assertEquals(vainYksi, true);
        assertEquals(vainYksi2, false);
        assertEquals(kaksiTaiEnemman, false);
        assertEquals(kaksiTaiEnemman2, true);
    }
    
    
}
