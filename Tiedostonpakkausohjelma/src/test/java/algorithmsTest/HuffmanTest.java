
package algorithmsTest;

import Tiedostonpakkausohjelma.algorithms.Huffman;
import Tiedostonpakkausohjelma.algorithms.Node;
import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import java.io.File;
import java.util.HashMap;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class HuffmanTest {
    
    String s = "abbcccd.";
    Huffman huffman = new Huffman(s, new FileHandler(new File("test.txt")));
    HashMap<Character, Integer> hm;
    PriorityQueue<Node> pq;
    Node first;
    
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
        hm = huffman.buildHashMap(s);
        pq = huffman.createNodes(hm);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void charactersInValuesCorrect(){
        assertTrue(hm.containsKey('a'));
        assertTrue(hm.containsKey('.'));
        int s = hm.size();
        assertEquals(5, s);
    }
    
    @Test
    public void amountOfCharactersInValuesCorrect(){
        int a = 1;
        int b = hm.get('a');
        assertEquals(a, b);
        int c = 3;
        int d = hm.get('c');
        assertEquals(c, d);
    }
    
    @Test
    public void nodesAreFormedCorrectly(){
        Node node = pq.peek();
        char c = node.getCharacter();
        assertEquals('a', c);
        int a = node.getNumber();
        assertEquals(1, a);
    }
    
    @Test
    public void amountOfNodesIsCorrect(){
        int s = pq.size();
        assertEquals(5, s);
    }
    
    @Test
    public void firstFormedCorrectly(){
        first = huffman.createTree(pq);
        char c = first.getCharacter();
        int i = first.getNumber();
        
        assertEquals('-', c);
        assertEquals(i, 8);
    }
    
    
}
