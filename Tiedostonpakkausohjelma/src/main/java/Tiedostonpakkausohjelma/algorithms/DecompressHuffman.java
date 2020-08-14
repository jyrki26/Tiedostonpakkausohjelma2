
package Tiedostonpakkausohjelma.algorithms;

import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import java.util.HashMap;

    /**
     * Luokka vastaa pakatun tiedoston purkamisesta.
     *
     */
public class DecompressHuffman {
    FileHandler filehandler;
    String text;
    Node first = new Node(0, '-');
    int next = 0;
    private HashMap<Character, String> codes;
    
    public DecompressHuffman(String text, FileHandler filehandler) {
        this.filehandler = filehandler;
        this.text = text;
        this.codes = new HashMap<>();
    }
    
    /**
     * Metodi k‰ynnist‰‰ tiedoston purun.
     *
     */
    public void decompress(){
        String str[] = text.split("-", 2);
        System.out.println(str[0]);
        first = buildTree(str[0], next);
    }
    
    /**
     * Metodi rakentaa bin‰‰ripuun pakatun tiedoston alussa olevasta tekstist‰.
     *
     * @param text Teksti, jonka perusteella puu muodostetaan.
     * @param i Seuraavana k‰sitelt‰v‰ kirjain.
     */
    public Node buildTree(String text, int i){
        if(text.charAt(next) == '1'){
            Node n = new Node(0, text.charAt(next+1));
            System.out.println(n.getCharacter());
            return n;
        } else{
            Node n = new Node(0, '-');
            n.setLeft(buildTree(text, codeReader(text)));
            n.setRight(buildTree(text, codeReader(text)));
            return n;
        }
    }
    
    /**
     * Metodi m‰‰ritt‰‰ mik‰ kirjain k‰sitell‰‰n seuraavaksi metodissa buildTree.
     *
     * @param code Teksti, jonka perusteella puu muodostetaan.
     */
    public int codeReader(String code){
       int i = next;
       while(i < code.length()){
           i++;
           if(code.charAt(i) == '0' || code.charAt(i) == '1'){
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
     * @param s String-muotoinen teksti, jonka perusteella muodostuvat kutakin kirjainta kuvaavat bitit.
     */
    
    public void createCodes(Node node, String s){
        if (node.getLeft() == null && node.getRight() == null && node.getCharacter() != '-') {
            System.out.println(node.getCharacter() + "   |  " + s);
            codes.put(node.getCharacter(), s);
            return;
        }

        createCodes(node.getLeft(), s + "0");
        createCodes(node.getRight(), s + "1");
    }
}
