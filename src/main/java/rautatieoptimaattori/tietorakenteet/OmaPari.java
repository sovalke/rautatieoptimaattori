package rautatieoptimaattori.tietorakenteet;

import java.util.Map;

/**
 * Yksinkertainen paria mallintava luokka.
 * @param <K>
 * @param <V>
 */
public class OmaPari<K, V> implements Map.Entry {

    private final K key;
    private V value;

    /**
     * Konstruktori.
     * @param key avain
     * @param value arvo
     */
    public OmaPari(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Hakee avaimen.
     * @return avain
     */
    @Override
    public K getKey() {
        return key;
    }

    /**
     * Hakee arvon.
     * @return arvo
     */
    @Override
    public V getValue() {
        return value;
    }

    /**
     * Asettaa arvon.
     * @param arvo
     * @return 
     */
    @Override
    public Object setValue(Object arvo) {
        this.value = (V) arvo;
        return this.value;
    }




}
