package rautatieoptimaattori.algorithms;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Lomitusjärjestämisluokan yksikkötestit.
 */
public class JarjestajaTest {

    /**
     * Konstruktori.
     */
    public JarjestajaTest() {
    }

    /**
     * Testaa Jarjesta-metodin toimintaa.
     */
    @Test
    public void jarjestaTest() {
        long[] taulukko = new long[1000];
        Random ran = new Random();
        for (int i = 0; i < taulukko.length; i++) {
            int x = ran.nextInt(20) + 1;
            taulukko[i] = x;
        }

        Jarjestaja jarjestaja = new Jarjestaja();
        taulukko[12] = 1;
        long[] result = jarjestaja.jarjesta(taulukko, 0, 999);
        long tulos = result[0];
        long odotus = 1;
        assertEquals(tulos, odotus);
    }

}
