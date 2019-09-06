package rautatieoptimaattori.tietorakenteet;

import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.domain.Solmu;

/**
 * OmaPari-luokan yksikkötestit.
 */
public class OmaPariTest {
    
    /**
     * Konstruktori.
     */
    public OmaPariTest() {
    }
    

    /**
     * Luo uuden parin ja testaa, voiko avainta hakea getKey-metodilla.
     */
    @Test
    public void getKeyTest() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        OmaPari pari = new OmaPari("Helsinki", helsinki);
        assertEquals(pari.getKey(), "Helsinki");
    }
    
    /**
     * Luo uuden parin ja testaa, voiko arvoa hakea getValue-metodilla.
     */
    @Test
    public void getValueTest() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        OmaPari pari = new OmaPari("Helsinki", helsinki);
        assertEquals(pari.getValue(), helsinki);
    }
    
    /**
     * Luo uuden parin ja testaa, voiko arvoa asettaa setValue-metodilla.
     */
    @Test
    public void setValueTest() {
        Solmu helsinki = new Solmu("Helsinki", 1, 60.166640, 24.943536);
        Solmu turku = new Solmu("Turku", 124, 60.451389, 22.266667);
        OmaPari pari = new OmaPari("Helsinki", helsinki);
        
        assertEquals(pari.setValue(turku), turku);
    }
    
}
