package Tiedostonpakkausohjelma.algorithms;


public class HashMapNode {
    public char key;
    public int value;
    public HashMapNode next;
    String code;

    public HashMapNode(char key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.code = null;
    }

    public void setNext(HashMapNode next) {
        this.next = next;
    }

    public HashMapNode getNext() {
        return next;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
    
}
