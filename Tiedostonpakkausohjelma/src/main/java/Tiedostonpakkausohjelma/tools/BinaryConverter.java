package Tiedostonpakkausohjelma.tools;

/**
 * Luokka vastaa merkkien muuttamisesta binäärimuotoon.
 *
 */
public class BinaryConverter {

    public String StringToBinary(String text) {
        int binary = Integer.parseInt(text, 2);
        String str = new Character((char) binary).toString();
        return str;
    }
}
