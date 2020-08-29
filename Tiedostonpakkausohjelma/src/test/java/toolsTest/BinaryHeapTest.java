
package toolsTest;

import Tiedostonpakkausohjelma.algorithms.Node;
import Tiedostonpakkausohjelma.tools.BinaryHeap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class BinaryHeapTest {
    
    BinaryHeap bh;
    
    public BinaryHeapTest() {
        this.bh = new BinaryHeap(new Node[10]);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        bh.insert(new Node(3, 'a'));
        bh.insert(new Node(1, 'b'));
        bh.insert(new Node(5, 'c'));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void amountofNodesCorrect(){
        int last = bh.getLast();
        assertEquals(3, last);
    }
    
    @Test
    public void deleteMinWorks(){
        Node node = bh.deleteMin();
        int number = node.getNumber();
        char c = node.getCharacter();
        assertEquals(1, number);
        assertEquals('b', c);
        int last = bh.getLast();
        assertEquals(2, last);
    }
    
    
}
