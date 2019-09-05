package rautatieoptimaattori.tietorakenteet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class OmaLista<T> implements Iterable<T>, Set<T> {

    protected T[] values;
    protected int numberOfValues;

    /**
     * Konstruktori: luo uuden listan.
     */
    public OmaLista() {
        this.values = (T[]) new Object[10];
        this.numberOfValues = 0;
    }

    /**
     * Lisää listalle uuden arvon.
     *
     * @param value lisättävä arvo
     * @return true, kun onnistuu
     */
    @Override
    public boolean add(T value) {
        if (this.numberOfValues == this.values.length) {
            grow();
        }

        this.values[this.numberOfValues] = value;
        this.numberOfValues++;
        return true;
    }

    /**
     * Tarkistaa, löytyykö arvo listalta.
     *
     * @param value arvo
     * @return true, jos löytyy
     */
    @Override
    public boolean contains(Object value) {
        return indexOf((T) value) >= 0;
    }

    /**
     * Poistaa halutun arvon listalta.
     *
     * @param value poisettava arvo
     * @return true, jos onnistuu, false jos arvoa ei ole listalla
     */
    @Override
    public boolean remove(Object value) {
        int indexOfValue = indexOf((T) value);
        if (indexOfValue < 0) {
            return false; // ei löydy
        }

        moveToLeft(indexOfValue);
        this.numberOfValues--;
        return true;
    }

    /**
     * Hakee tietystä indeksistä arvon.
     *
     * @param index indeksi
     * @return arvot taulukkona
     */
    public T value(int index) {
        if (index < 0 || index >= this.numberOfValues) {
            throw new ArrayIndexOutOfBoundsException("Indeksi " + index 
                    + " alueen [0, " + this.numberOfValues + "[ ulkopuolella.");
        }

        return this.values[index];
    }

    /**
     * Hakee tietyn arvon indeksin.
     *
     * @param value arvo
     * @return indeksi (-1, jos ei löydy)
     */
    public int indexOf(T value) {
        for (int i = 0; i < this.numberOfValues; i++) {
            if (this.values[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Palauttaa listalla olevien olioiden lukumäärän.
     *
     * @return int lukumäärä
     */
    @Override
    public int size() {
        return this.numberOfValues;
    }
    
    /**
     * Tyhjää listan.
     *
     */
    @Override
    public void clear() {
        this.values = (T[]) new Object[10];
        this.numberOfValues = 0;
    }
    
    /**
     * Tarkistaa, onko lista tyhjä.
     *
     * @return true, jos lista on tyhjä.
     */
    @Override
    public boolean isEmpty() {
        return this.numberOfValues == 0;
    }

    /**
     * Siirtää dataa taulukossa vasemmalle. Käytetään metodissa remove.
     *
     * @param fromIndex mistä indeksistä lähtien
     */
    private void moveToLeft(int fromIndex) {
        for (int i = fromIndex; i < this.numberOfValues - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    /**
     * Kasvatetaan listan pohjana olevan taulukon kokoa.
     */
    private void grow() {
        int newSize = this.values.length + this.values.length / 2;
        T[] newObject = (T[]) new Object[newSize];
        for (int i = 0; i < this.values.length; i++) {
            newObject[i] = this.values[i];
        }

        this.values = newObject;
    }

    /**
     * Alaluokka OmaLista-olion läpikäymistä varten.
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> item = new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length && values[index] != null;
            }

            @Override
            public T next() {
                return values[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return item;
    }

    /**
     * Palauttaa kaikki listan oliot Arrayna.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }

    /**
     * Muuntaa parametrina annetun taulukon Array-olioksi.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     * @param <T>
     */
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }

    /**
     * Tarkistaa, sisältääkö listä kaikki parametrina annetut arvot.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }

    /**
     * Lisää kaikki parametrina annetun Collectionin oliot listaan.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }

    /**
     * Säilyttää listalla vain sellaiset oliot, jotka sisältyvät parametrina
     * annettuun Collectio-olioon.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }

    /**
     * Poistaa kaikki oliot, jotka vastaavat parametrina annetun
     * Collection-olion olioita.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }
}
