package Tiedostonpakkausohjelma.fileHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileHandler {

    private File file;
    private byte[] b;

    public FileHandler(File file) {
        this.file = file;
    }

    /**
     * Metodi lukee tiedoston, joka halutaan pakata.
     *
     * @return Luettu tiedosto String-muodossa.
     *
     */
    public String read() {
        String text = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                text = text + line + "\n";
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        String correct = text.substring(0, text.length() - 1);
        return correct;
    }

    public void writeToFile(String text, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
            System.out.println("Tallennus onnistui.");
            writer.close();
        } catch (Exception e) {
            System.out.println("Tallennus epäonnistui. " + e);
        }

    }

    public void writeBytes(String filename, byte[] bytes) {
        b = bytes;
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(bytes);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    
    public byte[] readBytes(String file) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(file));
        return bytes;
    }
    
    public String compressionRate(){
        long original = file.length();
        long compressed = b.length;
        double rate = ((double)compressed / original) * 100;
        String s = "Alkuperäinen koko: " + original + " tavua.\n"+
                   "Pakattu koko: " + compressed + " tavua.\n" + 
                   "Pakattu tiedosto on " + rate + " prosenttia alkuperäisestä.";
        return s;
    }
}
