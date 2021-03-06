package Tiedostonpakkausohjelma.tools;

import Tiedostonpakkausohjelma.algorithms.HashMapNode;

/**
 * Luokka luo sovelletun hajautustaulun, jossa avaimena toimii char.
 * Ensimmäisenä saman hajautusarvon saava node lisätään taulukkoon hajautusarvon
 * kohdalle ja loput saman hajautusarvon saavat linkitetään siihen. Avaimen
 * perusteella voidaan hakea joko merkkien määrä alkuperäisessä tiedostossa tai
 * Huffamanin-koodi.
 *
 */
public class CharAmountsMap {

    HashMapNode[] map;
    private int size;
    private int nodes;

    public CharAmountsMap() {
        this.map = new HashMapNode[30];
        this.size = 30;
        this.nodes = 0;
    }

    /**
     * Metodi lisää uuden avaintietoparin hajautustauluun.
     *
     * @param node Solmu.
     */
    public void addChar(HashMapNode node) {
        int s = hash(node.getKey());

        if (map[s] == null) {
            map[s] = node;
            nodes++;
        } else {
            addNode(map[s], node);
        }

        if (nodes > size * 3 / 4) {
            increaseSize();
        }
    }

    /**
     * Metodia avulla tarkastetaan onko jo saman avaimen omaavaa avaintietoparia
     * hajautustaulussa.
     *
     * @param node Hajautustaulussa oleva avaintietopari.
     * @param hmn Uusi avaintietopari.
     */
    private void addNode(HashMapNode node, HashMapNode hmn) {
        if (node.getKey() == hmn.getKey()) {
            int value = hmn.getValue();
            node.setValue(value);
            return;
        }
        if (node.getNext() == null) {
            node.setNext(hmn);
            nodes++;
        } else {
            addNode(node.getNext(), hmn);
        }
    }

    /**
     * Metodi laskee hajautusarvon.
     *
     * @param c Merkki jonka perusteella hajautusarvo lasketaan.
     *
     * @return Hajautusarvo
     */
    public int hash(char c) {
        int i = (int) c;
        int hash = (i + size) % size;
        return hash;
    }

    /**
     * Metodi kasvattaa taulukon koon kaksinkertaiseksi ja laskee uudet
     * hajautusarvot, jos vähintään avaintietopareja on vähintään 3/4 taulukon
     * koosta.
     */
    private void increaseSize() {
        HashMapNode temp[] = map;
        map = new HashMapNode[2 * size];
        int newSize = 2 * size;
        size = newSize;
        nodes = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                HashMapNode n = temp[i];
                HashMapNode nod = new HashMapNode(n.getKey(), n.getValue());
                addChar(nod);
                while (n.getNext() != null) {
                    n = n.getNext();
                    nod = new HashMapNode(n.getKey(), n.getValue());
                    addChar(nod);
                }
            }
        }
    }

    /**
     * Metodi ilmoittaa sisältääkö hajautustaulu jotakin avainta.
     *
     * @param c Avain.
     *
     * @return Boolean-arvo.
     */
    public boolean containsChar(char c) {
        int i = hash(c);
        if (map[i] == null) {
            return false;
        }
        return search(map[i], c) != null;
    }

    /**
     * Metodi palauttaa avaimen perusteella arvon.
     *
     * @param c Avain.
     *
     * @return Arvo.
     */
    public int getValue(char c) {
        int i = hash(c);
        return search(map[i], c).value;
    }

    /**
     * Metodi palauttaa hajautustaulun kaikki avaimet.
     *
     * @return Avaimet taulukkona..
     */
    public char[] keyset() {
        char[] c = new char[nodes];
        int chars = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] != null) {
                HashMapNode n = map[i];
                c[chars] = n.getKey();
                chars++;
                while (n.getNext() != null) {
                    n = n.getNext();
                    c[chars] = n.getKey();
                    chars++;
                }
            }
        }
        return c;
    }

    /**
     * Metodi avulla lisätään nodelle Huffman-koodissa käytettävä koodi.
     *
     * @param c Avaimena käytettävä merkki.
     * @param code Koodi, joka lisätään.
     *
     */
    public void addCode(char c, String code) {
        int i = hash(c);
        search(map[i], c).setCode(code);
    }

    /**
     * Metodi palauttaa koodin merkin perusteella.
     *
     * @param c Avain.
     *
     * @return Koodi.
     */
    public String returnCode(char c) {
        int i = hash(c);
        return search(map[i], c).getCode();
    }

    /**
     * Metodi etsii noden taulukon tietystä kohdasta.
     *
     * @param node Taulukon tietyssä kohdassa oleva node.
     * @param key Avain, jota etsitään.
     *
     * @return Etsittävä node.
     */
    public HashMapNode search(HashMapNode node, char key) {
        if (node.getKey() == key) {
            return node;
        }
        if (node.getNext() != null) {
            return search(node.getNext(), key);
        }
        return null;
    }
}
