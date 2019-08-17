package tietorakenteet;

public class OmaLista<T> {

    private T[] values;
    private int numberOfValues;

    public OmaLista() {
        this.values = (T[]) new Object[10];
        this.numberOfValues = 0;
    }

    // Metodi, jolla listalle voi lisätä uuden arvon
    public void add(T value) {
        if (this.numberOfValues == this.values.length) {
            grow();
        }

        this.values[this.numberOfValues] = value;
        this.numberOfValues++;
    }

    // Metodi, joka tarkistaa, löytyykö arvo listalta
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    // Metodi, joka poistaa halutun arvon
    public void remove(T value) {
        int indexOfValue = indexOf(value);
        if (indexOfValue < 0) {
            return; // ei löydy
        }

        moveToLeft(indexOfValue);
        this.numberOfValues--;
    }

    // Metodi, joka hakee tietystä indeksistä arvon
    public T value(int index) {
        if (index < 0 || index >= this.numberOfValues) {
            throw new ArrayIndexOutOfBoundsException("Indeksi " + index + " alueen [0, " + this.numberOfValues + "[ ulkopuolella.");
        }

        return this.values[index];
    }

    // Metodi, joka hakee tietyn arvon indeksin
    public int indexOf(T value) {
        for (int i = 0; i < this.numberOfValues; i++) {
            if (this.values[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    // Metodi, joka palauttaa listalla olevien olioiden lukumäärän
    public int size() {
        return this.numberOfValues;
    }

    
    // YKSITYISET APULUOKAT
    
    // Siirretään dataa taulukossa vasemmalle. Käytetään metodissa remove.
    private void moveToLeft(int fromIndex) {
        for (int i = fromIndex; i < this.numberOfValues - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    // Kasvatetaan listan pohjana olevan taulukon kokoa, että kaikki mahtuu siihen.
    // Käytetään metodissa add.
    private void grow() {
        int newSize = this.values.length + this.values.length / 2;
        T[] newObject = (T[]) new Object[newSize];
        for (int i = 0; i < this.values.length; i++) {
            newObject[i] = this.values[i];
        }

        this.values = newObject;
    }
}
