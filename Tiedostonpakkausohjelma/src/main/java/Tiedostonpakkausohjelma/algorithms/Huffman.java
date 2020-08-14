
package Tiedostonpakkausohjelma.algorithms;

import Tiedostonpakkausohjelma.algorithms.Node.ImplementComparator;
import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import Tiedostonpakkausohjelma.tools.BinaryConverter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;


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
    String decompressor = "";

    public Huffman(String text, FileHandler filehandler) {
        this.text = text;
        this.values = new HashMap<>();
        this.filehandler = filehandler;
        bytes = text.getBytes();
        nodes = new PriorityQueue<>(new ImplementComparator());
        codes = new HashMap<>();
        code = "";
        bc = new BinaryConverter();
    }

    /**
     * Metodi k‰y String-muotoon muutetun tiedoston l‰pi ja laskee
     * hajautustaulun avulla eri merkkien lukum‰‰r‰n.
     *
     */
    public void StartHuffman() throws IOException {
        BuildHashMap();
        for (Character key : values.keySet()) {
            nodes.add(new Node(values.get(key), key));
        }
        CreateTree();
        CreateCode(first, "");
        compress();
        System.out.println(divideToBytes());
        System.out.println(decompressor);
    }

    void BuildHashMap() {
        for (int i = 0; i < text.length(); i++) {
            if (!values.containsKey(text.charAt(i))) {
                values.put(text.charAt(i), 1);
            } else {
                values.put(text.charAt(i), values.get(text.charAt(i)) + 1);
            }
        }
        System.out.println(values);
    }

    void CreateTree() {
        
        while (nodes.size() > 1) {
            Node a = nodes.poll();
            Node b = nodes.poll();
            
            int x = a.getNumber() + b.getNumber();
            
            Node n = new Node(x, '-');
            n.setLeft(a);
            n.setRight(b);

            first = n;
            nodes.add(n);
        }
        
    }

    public void CreateCode(Node node, String s) {
        if(node.getCharacter() == '-'){
            decompressor += "0";
        } else {
            decompressor += "1" + Character.toString(node.getCharacter());
        }
        if (node.getLeft() == null && node.getRight() == null && node.getCharacter() != '-') {
            System.out.println(node.getCharacter() + "   |  " + s);
            codes.put(node.getCharacter(), s);
            return;
        }

        CreateCode(node.getLeft(), s + "0");
        CreateCode(node.getRight(), s + "1");

    }
    
    public void compress(){
        String s = "";
        
        for(int i =0; i < text.length(); i++){
            s = codes.get(text.charAt(i));
            code += s;
        }
        
        System.out.println(code);
    }
    
    public String divideToBytes() throws IOException{
        int y = 0;
        int x = 0;
        String str = "";
        
        for(int i = 0; i < code.length(); i++){
            y++;
            if(y == 8){
                str = str + bc.StringToBinary(code.substring(x, i));
                x = i;
                y = 0;
            }
        }
        decompressor += "-" + str;
        filehandler.writeToFile(decompressor, "tiedosto.txt");
        return decompressor;
    }

}
