package tietorakenteet;

public class OmaPari<K, V> {

    private K key;
    private V value;

    public OmaPari(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V arvo) {
        this.value = arvo;
    }
}
