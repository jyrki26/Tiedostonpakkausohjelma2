package Tiedostonpakkausohjelma.tools;

import Tiedostonpakkausohjelma.algorithms.Node;

/**
 * Luokka toteuttaa binäärikeon sovelluksessa tarvittavilta osin.
 */
public class BinaryHeap {

    Node[] nodes;
    private int last;

    public BinaryHeap(Node[] nodes) {
        this.nodes = nodes;
        this.last = 0;
    }
    
    /**
     * Metodi lisää kekoon uuden solmun ja toteuttaa samalla kekoehdon säilymisen.
     *
     * @param node Lisättävä solmu.
     */
    public void insert(Node node) {
        last += 1;
        int p = last;
        while (p > 1 && node.getNumber() < nodes[parent(p)].getNumber()) {
            nodes[p] = nodes[parent(p)];
            p = parent(p);
        }
        nodes[p] = node;
    }
    
    /**
     * Metodi poistaa pienimmän, eli keon ensimmäisen solmun. Käytännössä solmu siirtyy viimeisestä solmusta ilmoittavan arvon last-ulkopuolelle.
     *
     * @return Pienin solmu.
     */
    
    public Node deleteMin() {
        Node first = nodes[1];
        nodes[1] = nodes[last];
        last -= 1;
        pushDown(1);
        return first;
    }
    
    
    /**
     * Metodi laskee keossa ensimmäiseksi nostetun viimeisen solmun oikealle paikalle.
     *
     * @param s Solmun sijainti keossa.
     */
    private void pushDown(int node) {
        int smaller;
        if (left(node) > last) {
            return;
        } else if (left(node) == last) {
            smaller = left(node);
        } else {
            if (nodes[left(node)].getNumber() < nodes[right(node)].getNumber()) {
                smaller = left(node);
            } else {
                smaller = right(node);
            }
        }
        if (nodes[node].getNumber() > nodes[smaller].getNumber()) {
            change(node, smaller);
            pushDown(smaller);
        }
    }
    
    /**
     * Metodi laskee solmun vanhemman.
     * 
     * @param position Solmun sijainti.
     */
    private int parent(int position) {
        return position / 2;
    }
    
    /**
     * Metodi laskee solmun vasemman lapsen.
     * 
     * @param position Solmun sijainti.
     */
    private int left(int position) {
        return 2 * position;
    }
    
    /**
     * Metodi laskee oikean lapsen.
     * 
     * @param position Solmun sijainti.
     */
    private int right(int position) {
        return 2 * position + 1;
    }
    
    /**
     * Metodi vaihtaa kahden solmun paikat.
     * 
     * @param first Ensimmäisen vaihdettavan solmun sijainti.
     * @param second Toisen vaihdettavan solmun sijainti.
     */
    private void change(int first, int second) {
        Node temp = nodes[first];
        nodes[first] = nodes[second];
        nodes[second] = temp;
    }
    
    /**
     * Metodi palauttaa viimeisen solmun arvon ja samalla keon koon.
     * 
     */
    public int getLast() {
        return last;
    }

}
