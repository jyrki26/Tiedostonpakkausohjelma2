package algorithmsTest;

import Tiedostonpakkausohjelma.algorithms.Huffman;
import Tiedostonpakkausohjelma.algorithms.Node;
import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import Tiedostonpakkausohjelma.tools.BinaryHeap;
import Tiedostonpakkausohjelma.tools.CharAmountsMap;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanTest {

    String s = "abbcccd.";
    Huffman huffman = new Huffman(new FileHandler(new File("test.txt")), "pakattu.txt");
    BinaryHeap heap;
    CharAmountsMap map;
    Node first;
    Huffman testHuffman;
    CharAmountsMap testMap;
    BinaryHeap testHeap;
    Node testFirst;

    public HuffmanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        map = huffman.buildHashMap(s);
        heap = huffman.createNodes(map);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void charactersInValuesCorrect() {
        assertTrue(map.containsChar('a'));
        assertTrue(map.containsChar('.'));
        int i = map.keyset().length;
        assertEquals(5, i);
    }

    @Test
    public void amountOfCharactersInValuesCorrect() {
        int a = 1;
        int b = map.getValue('a');
        assertEquals(a, b);
        int c = 3;
        int d = map.getValue('c');
        assertEquals(c, d);
    }

    @Test
    public void amountOfNodesIsCorrect() {
        int s = heap.getLast();
        assertEquals(5, s);
    }

    @Test
    public void nodesAreFormedCorrectly() {
        Node node = heap.deleteMin();
        char c = node.getCharacter();
        assertEquals('a', c);
        int a = node.getNumber();
        assertEquals(1, a);
    }

    @Test
    public void firstFormedCorrectly() {
        first = huffman.createTree(heap);
        char c = first.getCharacter();
        int i = first.getNumber();

        assertEquals('\u0238', c);
        assertEquals(i, 8);
    }

}
