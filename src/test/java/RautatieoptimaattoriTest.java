
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rautatieoptimaattori.algorithms.Astar;
import rautatieoptimaattori.algorithms.Dijkstra;
import rautatieoptimaattori.Verkko;

public class RautatieoptimaattoriTest {

    public RautatieoptimaattoriTest() {
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
    public void luoVerkko() {
        Verkko verkko = new Verkko();
        verkko.lisaaAsema("Helsinki", 123, 60.166640, 24.943536);
        verkko.lisaaAsema("Turku", 124, 60.451389, 22.266667);
        int vastaus = verkko.getKoko();
        assertEquals(2, vastaus);
    }

}
