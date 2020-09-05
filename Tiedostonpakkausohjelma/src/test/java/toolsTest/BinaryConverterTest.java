package toolsTest;

import Tiedostonpakkausohjelma.tools.BinaryConverter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryConverterTest {

    BinaryConverter bc = new BinaryConverter();

    public BinaryConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void stringConvertedCorrectly() {
        String s = "00000001";
        byte a = 1;
        byte converted = bc.stringToByte(s);

        String s2 = "01111111";
        byte b = 127;
        byte converted2 = bc.stringToByte(s2);

        String s3 = "00000000";
        byte c = 0;
        byte converted3 = bc.stringToByte(s3);

        String s4 = "11111111";
        byte d = -1;
        byte converted4 = bc.stringToByte(s4);

        assertEquals(a, converted);
        assertEquals(b, converted2);
        assertEquals(c, converted3);
        assertEquals(d, converted4);

    }

    @Test
    public void byteArraySplit() {
        byte[] bytes = new byte[5];

        byte a = 11;
        byte b = 50;
        byte c = 50;
        byte d = 42;
        byte e = 50;

        bytes[0] = a;
        bytes[1] = b;
        bytes[2] = c;
        bytes[3] = d;
        bytes[4] = e;

        byte[] bytes2 = new byte[5];

        byte a2 = 50;
        byte b2 = 5;
        byte c2 = 50;
        byte d2 = 50;
        byte e2 = 2;

        bytes2[0] = a2;
        bytes2[1] = b2;
        bytes2[2] = c2;
        bytes2[3] = d2;
        bytes2[4] = e2;

        int i = bc.splitBytes(bytes);
        int i2 = bc.splitBytes(bytes2);
        assertEquals(1, i);
        assertEquals(2, i2);

    }

    @Test
    public void convertBytes() {
        byte bytes[] = new byte[3];
        byte a = 50;
        byte b = 72;
        byte c = 111;

        bytes[0] = a;
        bytes[1] = b;
        bytes[2] = c;

        String s = "2Ho";
        String converted = bc.convertBytes(bytes);
        System.out.println(converted);
        assertEquals(s, converted);
    }

    @Test
    public void generateStringTest() {
        byte[] bytes = new byte[5];

        byte a = 48;
        byte b = 50;
        byte c = 50;
        byte d = 65;
        byte e = 69;

        bytes[0] = a;
        bytes[1] = b;
        bytes[2] = c;
        bytes[3] = d;
        bytes[4] = e;

        String s = "02201000001000101";
        String converted = bc.byteToString(bytes);

        assertEquals(s, converted);

    }

    @Test
    public void lastByteZero() {
        byte[] bytes = new byte[5];

        byte a = 48;
        byte b = 50;
        byte c = 50;
        byte d = 65;
        byte e = 0;

        bytes[0] = a;
        bytes[1] = b;
        bytes[2] = c;
        bytes[3] = d;
        bytes[4] = e;

        String s = "0220100000100000000";
        String converted = bc.byteToString(bytes);

        assertEquals(s, converted);

    }

}
