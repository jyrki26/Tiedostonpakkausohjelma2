
package Tiedostonpakkausohjelma.ui;

import Tiedostonpakkausohjelma.algorithms.DecompressHuffman;
import Tiedostonpakkausohjelma.algorithms.Huffman;
import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class AppUi {
    Scanner scanner;
    File file;
    FileHandler filereader;
    int number;

    public AppUi() {
        scanner = new Scanner(System.in);
    }

    /**
     * Metodi k‰ynnist‰‰ ohjelman.
     *
     */
    public void Start() throws IOException {
        System.out.println("K‰ynniss‰");
        while (true) {
            Dialog();
            if(number == 3){
                System.out.println("Ohjelma sammutettu.");
                break;
            }
        }
    }

    /**
     * Metodi k‰ynnist‰‰ k‰yttˆliittym‰n dialogin, jossa valitaan mit‰ halutaan
     * tehd‰.
     *
     */
    void Dialog() throws IOException {
        System.out.println("Paina 1 + ENTER, jos haluat pakata tiedoston");
        System.out.println("Paina 2 + ENTER, jos haluat purkaa tiedoston");
        System.out.println("Paina 3 + ENTER, jos haluat lopettaa");
        System.out.print("Anna valinta: ");
        number = Integer.parseInt(scanner.nextLine());
        if (number == 1) {
            CompressHuffman();
        }
        if (number == 2) {
            decompressHuffman();
        }
    }

    /**
     * Metodi k‰ynnist‰‰ tiedoston pakkauksen Huffmanin algoritmilla.
     *
     */
    void CompressHuffman() throws IOException {
        System.out.println("Anna pakattavan tiedoston osoite niin, ett‰ \\-merkin tilalla on /-merkki ");
        file = new File(scanner.nextLine());
        filereader = new FileHandler(file);
        String text = filereader.Read();
        Huffman huffman = new Huffman(text, filereader);
        huffman.StartHuffman();
    }
    
    /**
     * Metodi k‰ynnist‰‰ Huffmanin algoritmilla pakatun tiedoston purkamisen.
     *
     */
    void decompressHuffman(){
        System.out.println("Anna purettavan tiedoston osoite niin, ett‰ \\-merkin tilalla on /-merkki ");
        file = new File(scanner.nextLine());
        filereader = new FileHandler(file);
        String text = filereader.Read();
        DecompressHuffman decompress = new DecompressHuffman(text, filereader);
        decompress.decompress();
    }

}
