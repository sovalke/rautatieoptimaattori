package tietorakenteet;

public class Pari<K, V> {

    private K key;
    private V value;

    public Pari(K key, V value) {
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
