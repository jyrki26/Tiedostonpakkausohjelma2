package Tiedostonpakkausohjelma.tools;

/**
 * Luokka vastaa merkkien muuttamisesta binäärimuotoon.
 *
 */
public class BinaryConverter {

    public byte stringToByte(String s) {
        byte b = (byte) Integer.parseInt(s, 2);
        return b;
    }

    public int splitBytes(byte[] b) {
        Integer two = 50;
        int split = 0;
        int counter = 0;
        int counter2 = 1;
        byte b1 = two.byteValue();
        while (true) {
            if (b[counter] == b1 && b[counter2] == b1) {
                split = counter;
                break;
            }
            counter++;
            counter2++;
        }
        return split;
    }

    public String convertBytes(byte[] b) {
        String s = new String(b);
        return s;
    }

    public String byteToString(byte[] bytes) {
        int split = splitBytes(bytes);
        String str = "";
        byte[] b = new byte[split + 2];
        for (int i = 0; i < split + 2; i++) {
            b[i] = bytes[i];
        }
        str += convertBytes(b);
        for (int i = split + 2; i < bytes.length - 1; i++) {
            String s1 = String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0');
            str += s1;
        }
        String s2 = String.format("%8s", Integer.toBinaryString(bytes[bytes.length - 1] & 0xFF)).replace(' ', '0');
        int i = 0;
        while (i < s2.length()) {
            if (s2.charAt(i) == '1') {
                break;
            }
            i++;
        }
        String s3;
        if (i < 8) {
            s3 = s2.substring(i + 1, s2.length());
        } else {
            s3 = s2;
        }
        str += s3;
        return str;
    }
}
