package rautatieoptimaattori.tietorakenteet;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Oma HashMap-luokka.
 * @param <K>
 * @param <V>
 */
public class OmaHashMap<K, V> implements Map<K, V> {

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
     * Kopioi vanhan taulukon yksi indeksi kerrallaan uuteen taulukkoon.
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
     *
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
     *
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

    /**
     * Hakee avaimen perusteella hajautustaulusta arvon
     *
     * @param key hajautustaulun avain
     * @return null, jos arvoa ei löydy
     */
    @Override
    public V get(Object key) {

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
     *
     * @param key avain
     * @param value arvo
     */
    @Override
    public V put(K key, V value) {

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

        return value;
    }

    /**
     * Poistaa avain-arvoparin hajautustaulusta.
     *
     * @param key avain, jonka perusteella poisto tehdään
     * @return null, jos paria ei löydy tai poisto epäonnistuu
     */
    @Override
    public V remove(Object key) {
        OmaLista<OmaPari<K, V>> valuesInIndex = getKeyRelatedList((K) key);

        if (valuesInIndex.isEmpty()) {
            return null;
        }

        int indeksi = getIndexOfKey(valuesInIndex, (K) key);

        if (indeksi < 0) {
            return null;
        }

        OmaPari<K, V> pari = valuesInIndex.value(indeksi);
        valuesInIndex.remove(pari);
        this.numberOfValues--;
        return pari.getValue();
    }

    /**
     * Tarkistaa, löytyykö avainta hajautustaulusta.
     *
     * @param key haettava avain
     * @return true, jos avain löytyy
     */
    @Override
    public boolean containsKey(Object key) {
        V luku = get((K) key);

        if (luku != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tyhjää listan ja asettaa sen koon alkuarvoksi (32).
     */
    @Override
    public void clear() {
        this.values = new OmaLista[32];
        this.numberOfValues = 0;
    }

    /**
     * Hakee tiedon HashMapiin tallennettujen parien lukumäärästä.
     * 
     * @return int lukumäärä
     */
    @Override
    public int size() {
        return this.numberOfValues;
    }

    /**
     * Tarkistaa, löytyykö arvoa hajautustaulusta.
     * 
     * @return true, jos tallennettuja arvoja ei ole
     */
    @Override
    public boolean isEmpty() {
        return this.numberOfValues == 0;
    }

    /**
     * Palauttaa HashMapin parit settinä.
     * 
     * @return OmaLista-olio (Set-tyypin olio).
     */
    @Override
    public Set entrySet() {
        OmaLista palautettava = new OmaLista();

        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] != null) {
                for (int z = 0; z < this.values[i].size(); z++) {
                    palautettava.add(this.values[i].value(z));
                }
            }
        }

        return palautettava;
    }
    
    /**
     * Palauttaa HashMapin avaimet Set-tyypin oliona.
     * 
     * @return OmaLista-olio (Set-tyypin olio).
     */
    @Override
    public Set keySet() {
        OmaLista palautettava = new OmaLista();

        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] != null) {
                for (int z = 0; z < this.values[i].size(); z++) {
                    palautettava.add(this.values[i].value(z).getKey());
                }
            }
        }

        return palautettava;
    }
    
    /**
     * Palauttaa HashMapin arvot Collection-tyypin oliona.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     */
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }

    /**
     * Lisää kaikki parametrina annetun HashMapin parit tähän HashMapiin.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     * 
     * @param m lisättävä HashMap
     */
    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }
    
    /**
     * Tarkistaa, löytyykö arvoa hajautustaulusta.
     * EI KÄYTÖSSÄ TÄSSÄ TOTEUTUKSESSA.
     *
     * @param value haettava arvo
     * @return true, jos arvo löytyy
     */
    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Tätä metodia ei tueta vielä.");
    }

}
