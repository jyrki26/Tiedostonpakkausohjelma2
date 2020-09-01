package Tiedostonpakkausohjelma.algorithms;

import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import Tiedostonpakkausohjelma.tools.BinaryConverter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Luokka vastaa pakatun tiedoston purkamisesta.
 *
 */
public class DecompressHuffman {

    FileHandler filehandler;
    String file;
    Node first = new Node(0, '\u0238');
    int next = 0;
    private HashMap<Character, String> codes;
    String convertedToBytes;
    BinaryConverter bc;
    String saveToFile;

    public DecompressHuffman(String file, FileHandler filehandler) {
        this.filehandler = filehandler;
        this.file = file;
        this.codes = new HashMap<>();
        bc = new BinaryConverter();

    }

    /**
     * Metodi k‰ynnist‰‰ tiedoston purun.
     *
     */
    public void decompress() throws IOException {
        byte[] bytes = filehandler.readBytes(file);
        String s = bc.byteToString(bytes);
        String str[] = s.split("22", 2);
        System.out.println(str[0]);
        first = buildTree(str[0], next);
        createCodes(first, "");
        convertedToBytes = convertToBytes(str[1]);
        System.out.println(convertedToBytes);
        saveToFile = recreateText(str[1]);
        filehandler.writeToFile(saveToFile, "purettu.txt");
    }

    /**
     * Metodi rakentaa bin‰‰ripuun pakatun tiedoston alussa olevasta tekstist‰.
     *
     * @param text Teksti, jonka perusteella puu muodostetaan.
     * @param i Seuraavana k‰sitelt‰v‰ kirjain.
     */
    public Node buildTree(String text, int i) {
        if (text.charAt(next) == '1') {
            Node n = new Node(0, text.charAt(next + 1));
            System.out.println(n.getCharacter());
            return n;
        } else {
            Node n = new Node(0, '\u0238');
            n.setLeft(buildTree(text, codeReader(text)));
            n.setRight(buildTree(text, codeReader(text)));
            return n;
        }
    }

    /**
     * Metodi m‰‰ritt‰‰ mik‰ kirjain k‰sitell‰‰n seuraavaksi metodissa
     * buildTree.
     *
     * @param code Teksti, jonka perusteella puu muodostetaan.
     */
    public int codeReader(String code) {
        int i = next;
        while (i < code.length()) {
            i++;
            if (code.charAt(i) == '0' || code.charAt(i) == '1') {
                next = i;
                break;
            }
        }
        return next;
    }

    /**
     * Metodi purkaa puun hajautustauluun.
     *
     * @param node Kulloinkin k‰sitelt‰v‰ solmu.
     *
     * @param s String-muotoinen teksti, jonka perusteella muodostuvat kutakin
     * kirjainta kuvaavat bitit.
     */
    public void createCodes(Node node, String s) {
        if (node.getLeft() == null && node.getRight() == null && node.getCharacter() != '\u0238') {
            System.out.println(node.getCharacter() + "|" + s);
            System.out.println("_________________");
            codes.put(node.getCharacter(), s);
            return;
        }

        createCodes(node.getLeft(), s + "0");
        createCodes(node.getRight(), s + "1");
    }

    /**
     * Metodi muuntaa Huffman-koodin takaisin tavun mittaisiksi numerosarjoiksi.
     *
     * @param s Pakattu Huffman-koodi.
     *
     * @return Alkuper‰inen koodi.
     */
    public String convertToBytes(String s) {
        String bytes = bc.BinaryToString(s);
        String fixed = "";
        int length = bytes.length();
        int start = 1;
        while ((start + 7) < length) {
            fixed += (bytes.substring(start, start + 7));
            start += 8;
        }
        if (start < length) {
            fixed += (bytes.substring(start, length));
            System.out.println("loppu" + bytes.substring(start, length));
        }
        
        System.out.println("fixed " + fixed);
        return fixed;
    }
    
    /**
     * Metodi etsii puusta oikean merkin.
     *
     * @param node Ensimm‰inen node.
     * @param s Huffman-koodi.
     * 
     * @return Oikea merkki.
     */
    public char findChar(Node node, String s) {
        Node n = node;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                n = n.getLeft();
            } else {
                n = n.getRight();
            }
        }
        return n.getCharacter();
    }
    
    /**
     * Metodi pilkkoo koodin paloihin ja hakee oikean merkin metodin findChar avulla.
     *
     * @param text Alkuper‰inen koodi.
     * 
     * @return Alkuper‰inen teksti.
     */
    public String recreateText(String text) {
        String full = "";
        String help = "";
        for (int i = 0; i < text.length(); i++) {
            help += text.charAt(i);
            if (findChar(first, help) != '\u0238') {
                full += findChar(first, help);
                help = "";
            }
        }
        return full;
    }
}
