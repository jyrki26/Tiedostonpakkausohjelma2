package toolsTest;

import Tiedostonpakkausohjelma.algorithms.HashMapNode;
import Tiedostonpakkausohjelma.tools.CharAmountsMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharAmountsTest {

    CharAmountsMap map;

    public CharAmountsTest() {
        this.map = new CharAmountsMap();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        map.addChar(new HashMapNode('a', 3)); // integer value = 97
        map.addChar(new HashMapNode('b', 5)); // integer value = 98
        map.addChar(new HashMapNode('c', 1)); // integer value = 99
        map.addChar(new HashMapNode('!', 9)); // integer value = 33
        map.addChar(new HashMapNode('?', 11)); // integer value = 63
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hashIsCountedCorrectly() {
        int a = map.hash('a');
        int exclamation = map.hash('!');
        int question = map.hash('?');

        assertEquals(7, a);
        assertEquals(3, exclamation);
        assertEquals(3, question);
    }

    @Test
    public void keysetLength() {
        int i = map.keyset().length;
        assertEquals(5, i);
    }

    @Test
    public void sameHashInKeyset() {
        char exclamation = map.keyset()[0];
        char question = map.keyset()[1];
        assertEquals(exclamation, '!');
        assertEquals(question, '?');
    }

    @Test
    public void addAndReturnCode() {
        map.addCode('a', "01");
        map.addCode('?', "1111");
        String a = map.returnCode('a');
        String question = map.returnCode('?');

        assertEquals(a, "01");
        assertEquals(question, "1111");
    }

    @Test
    public void searchWorks() {
        HashMapNode n = new HashMapNode('a', 5);
        HashMapNode hmn = map.search(n, 'a');
        assertEquals(hmn.getKey(), 'a');
        assertEquals(hmn.getValue(), 5);
    }

    @Test
    public void searchWithSameHash() {
        HashMapNode n = new HashMapNode('?', 5);
        HashMapNode hmn = map.search(n, '?');
        assertEquals(hmn.getKey(), '?');
        assertEquals(hmn.getValue(), 5);
    }

    @Test
    public void searchWithNonexistingChar() {
        HashMapNode n = new HashMapNode('A', 5);
        HashMapNode hmn = map.search(n, 'l');
        Assert.assertNull(hmn);
    }

    @Test
    public void containsCharWorks() {
        assertTrue(map.containsChar('a'));
        assertTrue(map.containsChar('?'));
        assertTrue(map.containsChar('!'));
    }

    @Test
    public void containsCharFalse() {
        assertFalse(map.containsChar('A'));
        assertFalse(map.containsChar('ö'));
    }
}

