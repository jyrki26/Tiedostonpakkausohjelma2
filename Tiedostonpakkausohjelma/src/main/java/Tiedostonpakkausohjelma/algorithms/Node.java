package Tiedostonpakkausohjelma.algorithms;

import java.util.Comparator;

/**
 * Luokka toteuttaa Huffman-noden, jota käytetään binäärikeon muodostamiseen.
 *
 */
public class Node {

    int number;
    char character;
    Node left;
    Node right;

    public Node(int number, char character) {
        this.number = number;
        this.character = character;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return Character.toString(character) + " = " + number;
    }

    public static class ImplementComparator implements Comparator<Node> {

        @Override
        public int compare(Node a, Node b) {
            return a.number - b.number;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public char getCharacter() {
        return character;
    }
}
