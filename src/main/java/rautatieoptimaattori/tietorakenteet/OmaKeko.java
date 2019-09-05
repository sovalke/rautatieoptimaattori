package rautatieoptimaattori.tietorakenteet;

import rautatieoptimaattori.domain.VertailtavaSolmu;

public class OmaKeko {

    private final VertailtavaSolmu[] heap;
    private int knots;

    /**
     * Konstruktori.
     * @param size Luotavan keon koko.
     */
    public OmaKeko(int size) {
        if (size < 0) {
            size = 0;
        }
        this.heap = new VertailtavaSolmu[size + 1];
        this.knots = 0;
    }

    /**
     * Lisää uuden solmun kekoon.
     * @param knot käsiteltävä solmu
     * @return true, jos lisääminen onnistuu
     */
    public boolean add(VertailtavaSolmu knot) {
        this.knots = this.knots + 1;
        int current = this.knots;

        while (current > 1 
                && knot.etaisyys < heap[getParent(current)].etaisyys) {
            heap[current] = heap[getParent(current)];
            current = getParent(current);
        }
        
        heap[current] = knot;
        return true;
    }

    /**
     * Poistaa pienimmän solmun ja järjestää jäljellejääneet solmut.
     * @return pienin solmu
     */
    public VertailtavaSolmu poll() {
        if (isEmpty()) {
            return null;
        }
        VertailtavaSolmu first = heap[1];
        heap[1] = heap[knots];
        knots = knots - 1;
        int current = 1;
        
        while (2 * current <= knots) {
            
            int child = left(current);

            if (child != knots 
                    && heap[child].etaisyys >= heap[child + 1].etaisyys) {
                child = child + 1;
            }
            
            if (heap[current].etaisyys > heap[child].etaisyys) {
                VertailtavaSolmu a = heap[current];
                heap[current] = heap[child];
                heap[child] = a;
            } else {
                break;
            }
            current = child;
        }
        return first;
    }

    /**
     * Palauttaa pienimmän solmun, mutta ei poista sitä.
     * @return pienin solmu
     */
    public VertailtavaSolmu peek() {
        return heap[1];
    }

    /**
     * Palauttaa keon koon.
     * @return solmujen lkm heap-taulukossa
     */
    protected int size() {
        return heap.length;
    }
    
    /**
     * Tarkistaa, onko keko tyhjä.
     * @return true, jos keko on tyhjä.
     */
    public boolean isEmpty() {
        return this.knots == 0;
    }

    /**
     * Tarkistaa, onko keossa vain yksi solmu.
     * @return true, jos keossa on vain yksi solmu.
     */
    protected boolean onlyOne() {
        return this.knots == 1;
    }
    
    /**
     * Tarkistaa, onko keossa kaksi tai enemmän solmuja.
     * @return true, jos keossa on solmuja 2 tai enemmän.
     */
    protected boolean twoOrMore() {
        return this.knots >= 2;
    }

    /**
     * Palauttaa vasemman lapsen sijainnin.
     * @param location
     * @return 
     */
    protected int left(int location) {
        return (2 * location);
    }

    /**
     * Palauttaa oikean lapsen sijainnin.
     * @param location
     * @return 
     */
    protected int right(int location) {
        return (2 * location + 1);
    }
    
    /**
     * Palauttaa vanhemman sijainnin.
     * @param location
     * @return 
     */
    protected int getParent(int location) {
        return (location / 2);
    }

}
