package Tiedostonpakkausohjelma.algorithms;

import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import Tiedostonpakkausohjelma.tools.BinaryConverter;
import java.io.IOException;

/**
 * Luokka vastaa pakatun tiedoston purkamisesta.
 *
 */
public class DecompressHuffman {

    FileHandler filehandler;
    String file;
    Node first = new Node(0, '\u0238');
    int next = 0;
    String convertedToBytes;
    BinaryConverter bc;
    String saveToFile;

    public DecompressHuffman(String file, FileHandler filehandler) {
        this.filehandler = filehandler;
        this.file = file;
        bc = new BinaryConverter();

    }

    /**
     * Metodi käynnistää tiedoston purun.
     *
     */
    public void decompress() throws IOException {
        byte[] bytes = filehandler.readBytes(file);
        String s = bc.byteToString(bytes);
        String str[] = s.split("22", 2);
        first = buildTree(str[0], next);
        saveToFile = recreateText(str[1]);
        filehandler.writeToFile(saveToFile, "purettu.txt");
    }

    /**
     * Metodi rakentaa binääripuun pakatun tiedoston alussa olevasta tekstistä.
     *
     * @param text Teksti, jonka perusteella puu muodostetaan.
     * @param i Seuraavana käsiteltävä kirjain.
     * @return Palauttaa puun ensimmäisen solmun.
     */
    public Node buildTree(String text, int i) {
        if (text.charAt(next) == '1') {
            Node n = new Node(0, text.charAt(next + 1));
            next += 1;
            return n;
        } else {
            Node n = new Node(0, '\u0238');
            n.setLeft(buildTree(text, codeReader(text)));
            n.setRight(buildTree(text, codeReader(text)));
            return n;
        }
    }

    /**
     * Metodi määrittää mikä kirjain käsitellään seuraavaksi metodissa
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
     * Metodi etsii puusta oikean merkin.
     *
     * @param node Ensimmäinen node.
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
     * @param text Alkuperäinen koodi.
     * 
     * @return Alkuperäinen teksti.
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
