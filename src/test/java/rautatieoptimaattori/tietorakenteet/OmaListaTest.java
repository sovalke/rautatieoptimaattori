package rautatieoptimaattori.tietorakenteet;

import org.junit.Test;
import static org.junit.Assert.*;

public class OmaListaTest {

    /**
     * Konstruktori.
     */
    public OmaListaTest() {
    }

    /**
     * Testaa, toimiiko add-metodi odotetusti.
     */
    @Test
    public void addTest() {
        OmaLista lista = new OmaLista();
        boolean tulos = lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        lista.add(7);
        lista.add(8);
        lista.add(9);
        lista.add(10);
        lista.add(11);
        assertEquals(tulos, true);
    }

    /**
     * Testaa, palauttaako contains-metodi oikean arvon.
     */
    @Test
    public void containsTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        assertEquals(lista.contains(2), false);
        assertEquals(lista.contains(5), true);
    }

    /**
     * Testaa, poistaako remove-metodi olion listalta.
     */
    @Test
    public void removeTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        lista.add(6);
        
        assertEquals(lista.remove(5), true);
        assertEquals(lista.contains(5), false);
        assertEquals(lista.contains(6), true);
    }
    
    /**
     * Testaa, toimiiko remove odotetusti, kun poistettavaa oliota ei ole
     * olemassa.
     */
    @Test
    public void removeTest2() {
        OmaLista lista = new OmaLista();
        assertEquals(lista.remove(5), false);
    }
    

    /**
     * Testaa, hakeeko value-metodi halutun arvon.
     */
    @Test
    public void valueTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        lista.add(6);
        assertEquals(lista.value(1), 6);
    }
    
    /**
     * Testaa, miten value-metodi käyttäytyy, kun haetaan arvoa indeksin
     * ulkopuolelta.
     */
    @Test
    public void valueTest2() {
        OmaLista lista = new OmaLista();
        String error ="";
        
        try {
            lista.value(100);
        }
        catch(Exception e) {
            error = e.toString();
        } 
        
        assertEquals(error, "java.lang.ArrayIndexOutOfBoundsException: Indeksi "
                + "100 alueen [0, 0[ ulkopuolella.");
    }

    /**
     * Testaa, hakeeko indexOf-metodi halutun arvon.
     */
    @Test
    public void indexOfTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        lista.add(6);
        assertEquals(lista.indexOf(5), 0);
        assertEquals(lista.indexOf(7), -1);
    }

    /**
     * Testaa, hakeeko size-metodi listalla olevien olioiden määrän.
     */
    @Test
    public void sizeTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        lista.add(6);
        lista.add(7);
        assertEquals(lista.size(), 3);
    }
    
    /**
     * Testaa, tyhjääkö clear-metodi listan.
     */
    @Test
    public void clearTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        lista.add(6);
        lista.add(7);
        lista.clear();
        assertEquals(lista.numberOfValues, 0);
    }
    
    /**
     * Testaa, palauttaako isEmpty-metodi oikean tuloksen.
     */
    @Test
    public void isEmptyTest() {
        OmaLista lista = new OmaLista();
        boolean tyhja = lista.isEmpty();
        lista.add(5);
        boolean eiTyhja = lista.isEmpty();
        assertEquals(tyhja, true);
        assertEquals(eiTyhja, false);
        
    }   
    
}
