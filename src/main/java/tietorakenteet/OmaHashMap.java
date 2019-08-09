package tietorakenteet;

public class OmaHashMap<K, V> {
    private OmaLista<Pari<K, V>>[] values;
    private int numberOfValues;

    public OmaHashMap() {
        this.values = new OmaLista[32];
        this.numberOfValues = 0;
    }

    // Metodi, joka hakee avaimen perusteella hajautustaulusta arvon
    public V get(K key) {
        int hashValue = Math.abs(key.hashCode() % this.values.length);
        
        // Avainta ei löydy. Palautetaan null.
        if (this.values[hashValue] == null) {
            return null;
        }
        
        // Etsitään avain listalta.
        OmaLista<Pari<K, V>> valuesInIndex = this.values[hashValue];

        for (int i = 0; i < valuesInIndex.size(); i++) {
            if (valuesInIndex.value(i).getKey().equals(key)) {
                return valuesInIndex.value(i).getValue();
            }
        }

        return null;
    }

    // Metodi, joka lisää hajautustauluun avain-arvoparin (tai päivittää
    // olemassaolevan arvon)
    public void put(K key, V value) {
        // Haetaan arvot.
        OmaLista<Pari<K, V>> valuesInIndex = getKeyRelatedList(key);
        
        // Haetaan avaimen perusteella arvoa...
        int index = getIndexOfKey(valuesInIndex, key);

        // Jos ei löydy, lisätään uusi pari;
        // jos löytyy, päivitetään vanha.
        if (index < 0) {
            valuesInIndex.add(new Pari<>(key, value));
            this.numberOfValues++;
        } else {
            valuesInIndex.value(index).setValue(value);
        }

        // Tarkistetaan, täytyykö listan kokoa kasvattaa.
        if (1.0 * this.numberOfValues / this.values.length > 0.75) {
            grow();
        }
    }

    // Metodi, joka poistaa avain-arvoparin hajautustaulusta.
    public V remove(K key) {
        OmaLista<Pari<K, V>> valuesInIndex = getKeyRelatedList(key);
        
        // Lista on tyhjä; palautetaan null.
        if (valuesInIndex.size() == 0) {
            return null;
        }

        // Avainta ei ole listalla; palautetaan null.
        int indeksi = getIndexOfKey(valuesInIndex, key);
        if (indeksi < 0) {
            return null;
        }

        // Haetaan pari.
        Pari<K, V> pari = valuesInIndex.value(indeksi);
        valuesInIndex.remove(pari);
        
        return pari.getValue();
    }

    
    // YKSITYISET METODIT
    
    // Kasvattaa taulukon kokoa tarvittaessa, jotta kaikki data mahtuu.
    // Luodaan uusi, entistä isompi taulukko ja siirretään vanhat arvot siihen.
    private void grow() {
        OmaLista<Pari<K, V>>[] newList = new OmaLista[this.values.length * 2];

        for (int i = 0; i < this.values.length; i++) {
            copy(newList, i);
        }

        this.values = newList;
    }

    // Metodi, joka kopioi vanhan taulukon yksi indeksi kerrallaan uuteen
    // taulukkoon.
    private void copy(OmaLista<Pari<K, V>>[] uusi, int fromIndex) {
        for (int i = 0; i < this.values[fromIndex].size(); i++) {
            Pari<K, V> value = this.values[fromIndex].value(i);

            int hajautusarvo = Math.abs(value.getKey().hashCode() % uusi.length);
            if (uusi[hajautusarvo] == null) {
                uusi[hajautusarvo] = new OmaLista<>();
            }

            uusi[hajautusarvo].add(value);
        }
    }

    // Metodi, joka hakee avaimeen liittyvän listan.
    private OmaLista<Pari<K, V>> getKeyRelatedList(K key) {
        int hashValue = Math.abs(key.hashCode() % values.length);
        
        // Jos listaa ei löydy, luodaan se.
        if (values[hashValue] == null) {
            values[hashValue] = new OmaLista<>();
        }

        return values[hashValue];
    }

    // Metodi, joka etsii avaimen indeksin listalta.
    private int getIndexOfKey(OmaLista<Pari<K, V>> list, K key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.value(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;
    }

}
