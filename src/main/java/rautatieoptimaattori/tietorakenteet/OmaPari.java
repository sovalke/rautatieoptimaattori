package tietorakenteet;

public class OmaPari<K, V> {

    private K key;
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
    public K getKey() {
        return key;
    }

    /**
     * Hakee arvon.
     * @return arvo
     */
    public V getValue() {
        return value;
    }

    /**
     * Asettaa arvon.
     * @param arvo
     */
    public void setValue(V arvo) {
        this.value = arvo;
    }
}
