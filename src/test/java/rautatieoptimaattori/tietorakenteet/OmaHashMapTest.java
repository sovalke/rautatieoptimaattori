package rautatieoptimaattori.tietorakenteet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * OmaHashMap-luokan yksikkötestit.
 */
public class OmaHashMapTest {

    /**
     * Konstruktori.
     */
    public OmaHashMapTest() {
    }

    /**
     * Luo uuden HashMapin ja tarkistaa sen koon.
     */
    @Test
    public void sizeTest() {
        OmaHashMap mappi = new OmaHashMap();
        mappi.put(123, "Norsu");
        mappi.put(124, "Mammutti");
        assertEquals(mappi.size(), 2);
    }

    /**
     * Testaa get-metodin toimintaa.
     */
    @Test
    public void getTest() {
        OmaHashMap mappi = new OmaHashMap();
        mappi.put(123, "Norsu");
        mappi.put(124, "Mammutti");
        mappi.put(125, "Gaselli");
        mappi.put(126, "Seepra");
        mappi.put(127, "Leijona");
        mappi.put(128, "Majava");
        mappi.put(129, "Myyrä");
        mappi.put(130, "Marsu");
        mappi.put(131, "Nokikana");
        mappi.put(132, "Uikku");
        mappi.put(133, "Lumikko");
        mappi.put(134, "Mäyrä");
        mappi.put(135, "Vesihiisi");
        mappi.put(136, "Saukko");
        mappi.put(137, "Sisilisko");
        mappi.put(138, "Käärme");
        mappi.put(139, "Tiikeri");
        mappi.put(140, "Sarvikuono");
        mappi.put(141, "Rusakko");
        mappi.put(142, "Metsäjänis");
        mappi.put(143, "Sopuli");
        mappi.put(144, "Hamsteri");
        mappi.put(145, "Chinchilla");
        mappi.put(146, "Hyeena");
        mappi.put(147, "Servaali");
        mappi.put(148, "Pantteri");
        mappi.put(149, "Puuma");
        mappi.put(150, "Gorilla");
        mappi.put(151, "Simpanssi");
        mappi.put(152, "Laiskiainen");
        mappi.put(153, "Kiivi");
        mappi.put(154, "Tiikeripussikissa");
        mappi.put(155, "Kvokka");
        mappi.put(156, "Fasaani");

        assertEquals(mappi.get(123), "Norsu");
    }

    /**
     * Luo uuden HashMapin ja tarkistaa sen koon.
     */
    @Test
    public void removeTest() {
        OmaHashMap mappi = new OmaHashMap();
        mappi.put(123, "Norsu");
        
        assertEquals(mappi.remove(123), "Norsu");
        assertEquals(mappi.remove(124), null);
    }

    /**
     * Katsoo, toimiiko containsKey-metodi odotetusti.
     */
    @Test
    public void containsKeyTest() {
        OmaHashMap mappi = new OmaHashMap();
        mappi.put(123, "Norsu");
        assertEquals(mappi.containsKey(123), true);
        assertEquals(mappi.containsKey(124), false);
    }

    /**
     * Katsoo, toimiiko clear-metodi odotetusti. Varmistaa myös isEmpty-metodin
     * toiminnan.
     */
    @Test
    public void clearTest() {
        OmaHashMap mappi = new OmaHashMap();
        mappi.put(123, "Norsu");
        mappi.put(124, "Mammutti");
        int lkm = mappi.size();
        mappi.clear();
        assertEquals(lkm, 2);
        assertEquals(mappi.isEmpty(), true);
    }
    
    /**
     * Katsoo, saadaanko keySet-metodilla paluuarvona oikeankokoinen setti.
     */
    @Test
    public void KeySetTest() {
        OmaHashMap mappi = new OmaHashMap();
        mappi.put(123, "Norsu");
        mappi.put(124, "Mammutti");
        OmaLista setti = (OmaLista) mappi.keySet();
        
        assertEquals(setti.numberOfValues, 2);
    }

}
