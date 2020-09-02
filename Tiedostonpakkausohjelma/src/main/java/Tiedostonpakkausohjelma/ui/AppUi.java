package Tiedostonpakkausohjelma.ui;

import Tiedostonpakkausohjelma.algorithms.DecompressHuffman;
import Tiedostonpakkausohjelma.algorithms.Huffman;
import Tiedostonpakkausohjelma.fileHandler.FileHandler;
import java.io.File;
import java.io.FileNotFoundException;
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
     * @throws java.io.IOException
     */
    public void start() throws IOException {
        System.out.println("K‰ynniss‰");
        while (true) {
            dialog();
            if (number == 3) {
                System.out.println("Ohjelma sammutettu.");
                break;
            } else {
            }
        }
    }

    /**
     * Metodi k‰ynnist‰‰ k‰yttˆliittym‰n dialogin, jossa valitaan mit‰ halutaan
     * tehd‰.
     *
     */
    void dialog() throws IOException {
        System.out.println("Paina 1 + ENTER, jos haluat pakata tiedoston");
        System.out.println("Paina 2 + ENTER, jos haluat purkaa tiedoston");
        System.out.println("Paina 3 + ENTER, jos haluat lopettaa");
        System.out.println("Anna valinta: ");
        number = Integer.parseInt(scanner.nextLine());
        if (number == 1) {
            compressHuffman();
        }
        if (number == 2) {
            decompressHuffman();
        }
    }

    /**
     * Metodi k‰ynnist‰‰ tiedoston pakkauksen Huffmanin algoritmilla.
     *
     */
    void compressHuffman() throws IOException {
        System.out.println("Anna pakattavan tiedoston osoite niin, ett‰ \\-merkin tilalla on /-merkki ");
        file = new File(scanner.nextLine());
        System.out.println("Tiedoston luku ei onnistunut. Tarkista tiedoston nimi ja yrit‰ uudestaan.");
        long startTime = System.currentTimeMillis();
        filereader = new FileHandler(file);
        Huffman huffman = new Huffman(filereader);
        try {
            huffman.startHuffman();
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("Aikaa pakkaamiseen kului: " + endTime + " millisekuntia");
            System.out.println(filereader.compressionRate());
        } catch (IOException e) {
            System.out.println("Tiedoston luku ei onnistunut. Tarkista tiedoston nimi ja yrit‰ uudestaan.");
        } catch (Exception e) {
        }
    }

    /**
     * Metodi k‰ynnist‰‰ Huffmanin algoritmilla pakatun tiedoston purkamisen.
     *
     */
    void decompressHuffman() throws IOException {
        System.out.println("Anna purettavan tiedoston osoite niin, ett‰ \\-merkin tilalla on /-merkki ");
        String text = scanner.nextLine();
        long startTime = System.currentTimeMillis();
        file = new File(text);
        filereader = new FileHandler(file);
        DecompressHuffman decompress = new DecompressHuffman(text, filereader);
        try {
            decompress.decompress();
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("Aikaa purkamiseen meni: " + endTime + " millisekuntia");
        } catch (IOException e) {
            System.out.println("Tiedoston luku ei onnistunut. Tarkista tiedoston nimi ja yrit‰ uudestaan.");
        } catch (Exception e) {
        }
    }

}
