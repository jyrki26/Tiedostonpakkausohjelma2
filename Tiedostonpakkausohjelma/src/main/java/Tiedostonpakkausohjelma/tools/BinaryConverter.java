package Tiedostonpakkausohjelma.tools;

/**
 * Luokka vastaa merkkien muuttamisesta bin‰‰rimuotoon.
 *
 */
public class BinaryConverter {

    public String StringToBinary(String text) {
        int binary = Integer.parseInt(text, 2);
        String str = new Character((char) binary).toString();
        System.out.println("str byte " + str);
        return str;
    }

    public String BinaryToString(String text) {
        String s = "";

        for (int i = 0; i < text.length(); i++) {
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
            System.out.println("reverse " + reverse);
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
        
        System.out.println("vika reverse" + reverse);
        
        s += reverse;

        return s;
    }

    public String stringToBinary2(String s) {
        String str = "";
        for (int i = 0; i < s.length() / 8; i++) {

            int a = Integer.parseInt(s.substring(8 * i, (i + 1) * 8), 2);
            str += (char) (a);
        }
        System.out.println("str " + str);
        return str;
    }
    
    public byte stringToByte(String s){
        byte b = (byte)Integer.parseInt(s, 2);
        return b;
    }
    
    public String byteToString(byte[] bytes){
        int split = splitBytes(bytes);
        String str = "";
        byte[] b = new byte[split+2];
        for(int i = 0; i < split+2; i++){
            b[i] = bytes[i];
        }
        str += convertBytes(b);
        for(int i = split+2; i < bytes.length-1; i++){
            String s1 = String.format("%7s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0');
            str += s1;
        }
        String s2 = String.format("%7s", Integer.toBinaryString(bytes[bytes.length-1] & 0xFF)).replace(' ', '0');
        int i = 0;
        while(s2.charAt(i) != '1'){
            i++;
        }
        String s3 = s2.substring(i+1, s2.length());
        str += s3;
        System.out.println(str);
        return str;
    }
    
    public int splitBytes(byte[] b){
        Integer two = 50;
        int split = 0;
        int counter = 0;
        int counter2 = 1;
        byte b1 = two.byteValue();
        System.out.println(b1);
        while(true){
            if(b[counter] == b1 && b[counter2] == b1){
                split = counter;
                break;
            }
            counter++;
            counter2++;
        }
        return split;
    }
    
    public String convertBytes(byte[] b){
        String s = new String(b);
        return s;
    }
}
