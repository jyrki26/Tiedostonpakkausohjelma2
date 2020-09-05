package algorithmsTest;

import Tiedostonpakkausohjelma.algorithms.DecompressHuffman;
import Tiedostonpakkausohjelma.algorithms.Node;
import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecompressTest {

    DecompressHuffman decompress = new DecompressHuffman("testFile", new FileHandler(new File("testFile")), "purettu.txt");
    String s;
    Node first;
    String s2;
    Node second;

    public DecompressTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        s = "01b01a1c";
        first = decompress.buildTree(s, 0);
        decompress = new DecompressHuffman("testFile", new FileHandler(new File("testFile")), "purettu.txt");
        s2 = "0110101c";
        second = decompress.buildTree(s2, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void treeFormedCorrectly() {
        Node left = first.getLeft();
        Node right = first.getRight();
        Node rightLeft = right.getLeft();
        Node rightRight = right.getRight();

        assertEquals('\u0238', first.getCharacter());
        assertEquals('b', left.getCharacter());
        assertEquals('\u0238', right.getCharacter());
        assertEquals('a', rightLeft.getCharacter());
        assertEquals('c', rightRight.getCharacter());

    }

    @Test
    public void oneAndTwoInTree() {
        Node left = second.getLeft();
        Node right = second.getRight();
        Node rightLeft = right.getLeft();
        Node rightRight = right.getRight();

        assertEquals('\u0238', first.getCharacter());
        assertEquals('1', left.getCharacter());
        assertEquals('\u0238', right.getCharacter());
        assertEquals('0', rightLeft.getCharacter());
        assertEquals('c', rightRight.getCharacter());
    }

    @Test
    public void findRightChar() {
        char a = decompress.findChar(second, "0");
        char b = decompress.findChar(second, "10");
        char c = decompress.findChar(second, "11");

        assertEquals(a, '1');
        assertEquals(b, '0');
        assertEquals(c, 'c');

    }
}
