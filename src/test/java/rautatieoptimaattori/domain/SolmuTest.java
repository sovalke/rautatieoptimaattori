package rautatieoptimaattori.domain;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.domain.Solmu;

public class SolmuTest {
    
    public SolmuTest() {
    }

    @Test
    public void luoSolmu1() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        assertEquals(helsinki.getId(), 1);
    }
    
    @Test
    public void luoSolmu2() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        assertEquals(helsinki.getNimi(), "Helsinki");
    }
    
    @Test
    public void luoSolmu3() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        Solmu turku = new Solmu("Turku", 124, 60.451389, 22.266667);
        helsinki.lisaaYhteys(turku, 180);
        assertEquals(helsinki.getEtaisyys(turku), 180);
    }
    
    @Test
    public void luoSolmu4() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        Solmu turku = new Solmu("Turku", 124, 60.451389, 22.266667);
        helsinki.lisaaYhteys(turku, 180);
        HashMap naapurit = helsinki.getNaapurit();
        assertEquals(naapurit.size(), 1);
    }
    
    @Test
    public void haeXY() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        double x = helsinki.getX() / 1000000;
        double y = helsinki.getY() / 1000000;
        System.out.println(x);
        System.out.println(y);
        assertEquals(x, 60.166640, 0.00);
        assertEquals(y, 24.943536, 0.00);
    }
    
    @Test
    public void setNimi() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        int i = helsinki.setNimi("Helsingfors");
        assertEquals(i, 1);
    }
    
    @Test
    public void setNimi2() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        int i = helsinki.setNimi("");
        assertEquals(i, -1);
    }
    
    @Test
    public void setXY() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        int i = helsinki.setXY((long) 60.2, (long) 24.95);
        assertEquals(i, 1);
    }
    
    @Test
    public void setId() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        int i = helsinki.setId(2);
        assertEquals(i, 1);
    }
}
