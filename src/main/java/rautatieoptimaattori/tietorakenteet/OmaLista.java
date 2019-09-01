package rautatieoptimaattori.tietorakenteet;

public class OmaLista<T> {

    private T[] values;
    private int numberOfValues;

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
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    /**
     * Poistaa halutun arvon listalta.
     *
     * @param value arvo
     * @return true, jos onnistuu, false jos arvoa ei ole listalla
     */
    public boolean remove(T value) {
        int indexOfValue = indexOf(value);
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
    public int size() {
        return this.numberOfValues;
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
}
