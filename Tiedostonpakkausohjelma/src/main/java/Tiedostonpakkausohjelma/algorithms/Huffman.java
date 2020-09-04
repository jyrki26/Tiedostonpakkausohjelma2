package Tiedostonpakkausohjelma.algorithms;

import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import Tiedostonpakkausohjelma.tools.BinaryConverter;
import Tiedostonpakkausohjelma.tools.BinaryHeap;
import java.io.IOException;
import Tiedostonpakkausohjelma.tools.CharAmountsMap;

public class Huffman {

    private String text;
    private byte[] bytes;
    private Node first;
    private String code;
    private BinaryConverter bc;
    private FileHandler filehandler;
    private CharAmountsMap amounts;
    String decompressor = "";
    private int charAmount;
    private BinaryHeap heap;

    public Huffman(FileHandler filehandler) {
        this.filehandler = filehandler;
        code = "";
        bc = new BinaryConverter();
    }

    /**
     * Metodi käy String-muotoon muutetun tiedoston läpi ja laskee
     * hajautustaulun avulla eri merkkien lukumäärän.
     *
     */
    public void startHuffman() throws IOException {
        text = filehandler.read();
        amounts = buildHashMap(text);
        heap = createNodes(amounts);
        createTree(heap);
        createCode(first, "");
        compress();
        biteDivider();

    }

    /**
     * Metodi laskee pakattavassa tiedostossa olevan merkkien määrän ja
     * sijoittaa ne hajautustauluun.
     *
     * @param s Pakattavan tiedoston sisältämän teksti String-muodossa.
     *
     * @return Hajautustaulu, jossa kirjaimien määrät on laskettu.
     */
    public CharAmountsMap buildHashMap(String s) {
        CharAmountsMap map = new CharAmountsMap();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsChar(s.charAt(i))) {
                map.addChar(new HashMapNode(s.charAt(i), 1));
            } else {
                int value = map.getValue(s.charAt(i));
                map.addChar(new HashMapNode(s.charAt(i), value + 1));
            }
        }

        return map;
    }

    /**
     * Metodi muodostaa hajautustaulun tiedoista Nodet.
     *
     * @param map Hajautustaulu.
     *
     * @return Prioriteettijono, jossa Nodet ovat.
     */
    public BinaryHeap createNodes(CharAmountsMap map) {
        char[] chars = map.keyset();
        charAmount = chars.length;
        BinaryHeap bh = new BinaryHeap(new Node[charAmount * 2]);
        for (int i = 0; i < chars.length; i++) {
            bh.insert(new Node(map.getValue(chars[i]), chars[i]));
        }
        return bh;
    }

    /**
     * Metodi rakentaa Nodeista Huffman-puun.
     *
     * @param bh Prioriteettijono, jossa Nodet ovat.
     *
     * @return Puun ensimmäisen Noden.
     */
    public Node createTree(BinaryHeap bh) {
        while (bh.getLast() > 1) {
            Node a = bh.deleteMin();
            Node b = bh.deleteMin();

            int x = a.getNumber() + b.getNumber();

            Node n = new Node(x, '\u0238');
            n.setLeft(a);
            n.setRight(b);

            first = n;
            bh.insert(n);
        }
        return first;
    }

    /**
     * Metodi laskee puusta koodin jokaiselle merkille ja tallentaa ne
     * hashmappiin.
     *
     * @param node solmu, jolle metodi laskee arvon
     * @param s string, joka kasvaa puuta alaspäin liikuttaessa
     */
    public void createCode(Node node, String s) {
        if (node.getCharacter() == '\u0238') {
            decompressor += "0";
        } else {
            decompressor += "1" + Character.toString(node.getCharacter());
        }
        if (node.getLeft() == null && node.getRight() == null && node.getCharacter() != '\u0238') {
            amounts.addCode(node.getCharacter(), s);
            return;
        }

        createCode(node.getLeft(), s + "0");
        createCode(node.getRight(), s + "1");

    }

    /**
     * Metodi käy tekstin läpi ja muuntaa jokaisen kirjaimen koodiksi.
     *
     */
    public void compress() {
        String s = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            s = amounts.returnCode(c);
            code += s;
        }
    }

    public void biteDivider() {
        decompressor += "22";
        byte[] b = decompressor.getBytes();
        int numberOfBytes;
        numberOfBytes = code.length() / 8 + 1 + b.length;

        bytes = new byte[numberOfBytes];
        for (int i = 0; i < b.length; i++) {
            bytes[i] = b[i];
        }
        int y = 0;
        int x = 0;
        int number = b.length;

        for (int i = 0; i < code.length(); i++) {
            if (y == 8) {
                String s = code.substring(x, i);
                bytes[number] = bc.stringToByte(s);
                x = i;
                y = 0;
                number++;
            }
            y++;
        }

        if (code.length() % 8 != 0) {
            bytes[number] = bc.stringToByte("1" + code.substring(x, code.length()));
        } else {
            bytes[number] = bc.stringToByte("00000001");
        }

        filehandler.writeBytes("bitit", bytes);
        System.out.println("Pakkaus onnistui.");
    }

}
