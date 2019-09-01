/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rautatieoptimaattori.tietorakenteet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sonja
 */
public class OmaListaTest {

    /**
     * Konstruktori.
     */
    public OmaListaTest() {
    }

    /**
     * Testaa, toimiiko add-metodi odotetusti.
     */
    public void addTest() {
        OmaLista lista = new OmaLista();
        assertEquals(lista.add(5), true);
    }

    /**
     * Testaa, palauttaako contains-metodi oikean arvon.
     */
    public void containsTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        assertEquals(lista.contains(2), false);
        assertEquals(lista.contains(5), true);
    }

    /**
     * Testaa, poistaako remove-metodi olion listalta.
     */
    public void removeTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        lista.add(6);
        lista.remove(5);
        assertEquals(lista.contains(5), false);
        assertEquals(lista.contains(6), true);
    }

    /**
     * Testaa, hakeeko value-metodi halutun arvon.
     */
    public void valueTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        lista.add(6);
        assertEquals(lista.value(1), 6);
    }

    /**
     * Testaa, hakeeko indexOf-metodi halutun arvon.
     */
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
    public void sizeTest() {
        OmaLista lista = new OmaLista();
        lista.add(5);
        lista.add(6);
        lista.add(7);
        assertEquals(lista.size(), 3);
    }
    
}
