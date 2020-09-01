package Tiedostonpakkausohjelma.algorithms;

import Tiedostonpakkausohjelma.algorithms.Node.ImplementComparator;
import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import Tiedostonpakkausohjelma.tools.BinaryConverter;
import Tiedostonpakkausohjelma.tools.BinaryHeap;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import Tiedostonpakkausohjelma.tools.CharAmountsMap;

public class Huffman {

    private String text;
    private HashMap<Character, Integer> values;
    private HashMap<Character, String> codes;
    private byte[] bytes;
    private PriorityQueue<Node> nodes;
    private Node first;
    private String code;
    private BinaryConverter bc;
    private FileHandler filehandler;
    private CharAmountsMap amounts;
    String decompressor = "";
    private int charAmount;
    private BinaryHeap heap;

    public Huffman(FileHandler filehandler) {
        this.values = new HashMap<>();
        this.filehandler = filehandler;
        nodes = new PriorityQueue<>(new ImplementComparator());
        codes = new HashMap<>();
        code = "";
        bc = new BinaryConverter();
    }

    /**
     * Metodi k�y String-muotoon muutetun tiedoston l�pi ja laskee
     * hajautustaulun avulla eri merkkien lukum��r�n.
     *
     */
    public void StartHuffman() throws IOException {
        text = filehandler.Read();
        amounts = buildHashMap(text);
        heap = createNodes(amounts);
        createTree(heap);
        createCode(first, "");
        compress();
        biteDiveder();
        
    }
    /**
     * Metodi laskee pakattavassa tiedostossa olevan merkkien m��r�n ja
     * sijoittaa ne hajautustauluun.
     *
     * @param s Pakattavan tiedoston sis�lt�m�n teksti String-muodossa.
     *
     * @return Hajautustaulu, jossa kirjaimien m��r�t on laskettu.
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
        for(int i = 0; i < chars.length; i++){
            bh.insert(new Node(map.getValue(chars[i]), chars[i]));
        }
        return bh;
    }

    /**
     * Metodi rakentaa Nodeista Huffman-puun.
     *
     * @param bh Prioriteettijono, jossa Nodet ovat.
     *
     * @return Puun ensimm�isen Noden.
     */
    public Node createTree(BinaryHeap bh) {
        while (bh.getLast() > 1) {
            Node a = bh.deleteMin();
            Node b = bh.deleteMin();

            int x = a.getNumber() + b.getNumber();

            Node n = new Node(x,'\u0238');
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
     * @param s string, joka kasvaa puuta alasp�in liikuttaessa
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
     * Metodi k�y tekstin l�pi ja muuntaa jokaisen kirjaimen koodiksi.
     *
     */
    public void compress() {
        String s = "";

        for (int i = 0; i < text.length(); i++) {
            s = amounts.returnCode(text.charAt(i));
            code += s;
        }
    }

    /**
     * Metodi jakaa koodin seitsem�n merkin mittaisiin paloihin ja muuttaa ne
     * merkeiksi ja lis�� alkuun avaimen, jolla puun saa muodostettua tiedostoa
     * purettaessa. Jokaisen seitsem�n merkin eteen lis�t��n 0, jotta niist�
     * muodostuu tavun mittainen, lukuunottamatta viimeist�, jonka eteen
     * lis�t��n 1.
     *
     * @return Pakattuun tiedostoon tallennettava teksti.
     *
     */
    public String divideToBytes() throws IOException {
        int y = 0;
        int x = 0;
        String str = "";

        for (int i = 0; i < code.length(); i++) {
            if (y == 7) {
                String s = "0" + code.substring(x, i);
                str = str + bc.stringToBinary2(s);
                x = i;
                y = 0;
            }
            y++;
        }
        
        if (code.length() % 7 != 0) {
            str = str + bc.stringToBinary2("1" + code.substring(x, code.length()));
        }
        decompressor += "22" + str;
        filehandler.writeToFile(decompressor, "tiedosto.txt");
        return decompressor;
    }
    
    public void biteDiveder(){
        decompressor += "22";
        byte[] b = decompressor.getBytes();
        int numberOfBytes;
        if(code.length() % 7 == 0){
            numberOfBytes = code.length()/7 + b.length;
        } else {
            numberOfBytes = code.length()/7 + 1 + b.length;
        }
        
        bytes = new byte[numberOfBytes];
        for(int i = 0; i < b.length; i++){
            bytes[i] = b[i];
        }
        int y = 0;
        int x = 0;
        int number = b.length;

        for (int i = 0; i < code.length(); i++) {
            if (y == 7) {
                String s = "0" + code.substring(x, i);
                bytes[number] = bc.stringToByte(s);
                x = i;
                y = 0;
                number++;
            }
            y++;
        }
        
        if (code.length() % 7 != 0) {
            bytes[number] = bc.stringToByte("1" + code.substring(x, code.length()));
        }

        filehandler.writeBytes("bitit", bytes);
    }

}
