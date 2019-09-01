package tietorakenteet;

public class OmaHashMap<K, V> {

    private OmaLista<OmaPari<K, V>>[] values;
    private int numberOfValues;

    /**
     * Konstruktori: luo uuden olion.
     */
    public OmaHashMap() {
        this.values = new OmaLista[32];
        this.numberOfValues = 0;
    }

    /**
     * Hakee avaimen perusteella hajautustaulusta arvon
     * @param key hajautustaulun avain
     * @return null, jos arvoa ei löydy
     */
    public V get(K key) {

        int hashValue = Math.abs(key.hashCode() % this.values.length);

        if (this.values[hashValue] == null) {
            return null;
        }

        OmaLista<OmaPari<K, V>> valuesInIndex = this.values[hashValue];

        for (int i = 0; i < valuesInIndex.size(); i++) {
            if (valuesInIndex.value(i).getKey().equals(key)) {
                return valuesInIndex.value(i).getValue();
            }
        }

        return null;
    }

    /**
     * Lisää hajautustauluun avain-arvoparin (tai päivittää olemassaolevan
     * arvon)
     * @param key avain
     * @param value arvo
     */
    public void put(K key, V value) {

        if (key == null | value == null) {
            return;
        }

        OmaLista<OmaPari<K, V>> valuesInIndex = getKeyRelatedList(key);
        int index = getIndexOfKey(valuesInIndex, key);

        if (index < 0) {
            valuesInIndex.add(new OmaPari<>(key, value));
            this.numberOfValues++;
        } else {
            valuesInIndex.value(index).setValue(value);
        }

        if (1.0 * this.numberOfValues / this.values.length > 0.75) {
            grow();
        }
    }

    /**
     * Poistaa avain-arvoparin hajautustaulusta.
     * @param key avain, jonka perusteella poisto tehdään
     * @return null, jos paria ei löydy tai poisto epäonnistuu
     */
    public V remove(K key) {
        OmaLista<OmaPari<K, V>> valuesInIndex = getKeyRelatedList(key);

        if (valuesInIndex.size() == 0) {
            return null;
        }
        
        int indeksi = getIndexOfKey(valuesInIndex, key);
        
        if (indeksi < 0) {
            return null;
        }

        OmaPari<K, V> pari = valuesInIndex.value(indeksi);
        valuesInIndex.remove(pari);
        return pari.getValue();
    }

    /**
     * Tarkistaa, löytyykö avainta hajautustaulusta.
     * @param key haettava avain
     * @return true, jos avain löytyy
     */
    public boolean containsKey(K key) {
        V luku = get(key);

        if (luku != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Kasvattaa taulukon kokoa tarvittaessa, jotta kaikki data mahtuu.
     */
    private void grow() {
        OmaLista<OmaPari<K, V>>[] newList = new OmaLista[this.values.length * 2];

        for (int i = 0; i < this.values.length; i++) {
            copy(newList, i);
        }

        this.values = newList;
    }

    /**
     * kopioi vanhan taulukon yksi indeksi kerrallaan uuteen taulukkoon.
     */
    private void copy(OmaLista<OmaPari<K, V>>[] uusi, int fromIndex) {

        if (this.values[fromIndex] != null) {
            for (int i = 0; i < this.values[fromIndex].size(); i++) {
                OmaPari<K, V> value = this.values[fromIndex].value(i);

                int hajautusarvo = Math.abs(value.getKey().hashCode() % uusi.length);
                if (uusi[hajautusarvo] == null) {
                    uusi[hajautusarvo] = new OmaLista<>();
                }

                uusi[hajautusarvo].add(value);
            }
        }
    }

    /**
     * Hakee avaimeen liittyvän listan.
     * @param key avain
     */
    private OmaLista<OmaPari<K, V>> getKeyRelatedList(K key) {
        int hashValue = Math.abs(key.hashCode() % values.length);

        if (values[hashValue] == null) {
            values[hashValue] = new OmaLista<>();
        }

        return values[hashValue];
    }

    /**
     * Metodi, joka etsii avaimen indeksin listalta.
     * @return -1, jos avainta ei löydy.
     */
    private int getIndexOfKey(OmaLista<OmaPari<K, V>> list, K key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.value(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;
    }

}
