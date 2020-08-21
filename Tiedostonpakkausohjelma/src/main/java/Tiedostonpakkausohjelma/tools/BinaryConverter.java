package Tiedostonpakkausohjelma.tools;

/**
 * Luokka vastaa merkkien muuttamisesta bin‰‰rimuotoon.
 *
 */
public class BinaryConverter {

    public String StringToBinary(String text) {
        int binary = Integer.parseInt(text, 2);
        String str = new Character((char) binary).toString();
        return str;
    }

    public String BinaryToString(String text) {
        String s = "";

        for (int i = 0; i + 1 < text.length(); i++) {
            int y = Integer.valueOf(text.charAt(i));
            System.out.println(text.charAt(i) + " ");

            String bin = "";
            while (y > 0) {
                if (y % 2 == 1) {
                    bin += '1';
                } else {
                    bin += '0';
                }
                y /= 2;
            }

            String reverse = "";

            for (int x = bin.length() - 1; x >= 0; x--) {
                reverse += bin.charAt(x);
            }

            while (reverse.length() < 8) {
                reverse = "0" + reverse;
            }

            s += reverse;
            System.out.println(reverse);
        }

        int z = text.charAt(text.length() - 1);

        String bin = "";
        while (z > 0) {
            if (z % 2 == 1) {
                bin += '1';
            } else {
                bin += '0';
            }
            z /= 2;
        }

        String reverse = "";

        for (int x = bin.length() - 2; x >= 0; x--) {
            reverse += bin.charAt(x);
        }
        
        System.out.println(reverse);
        
        s += reverse;

        return s;
    }

    public String stringToBinary2(String s) {
        String str = "";
        for (int i = 0; i < s.length() / 8; i++) {

            int a = Integer.parseInt(s.substring(8 * i, (i + 1) * 8), 2);
            str += (char) (a);
        }
        return str;
    }
}
