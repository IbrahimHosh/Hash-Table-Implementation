import java.util.Scanner;
import java.io.*;

public class TextScan {
    static String[] tokens;
    public TextScan(String filename){
        this.tokens = tokens;
        String[] args;
        args = new String[1];
        args[0] = filename;
        Scanner readFile = null;
        String s;
        int count = 0;

        System.out.println();
        System.out.println("Attempting to read from file: " + args[0]);
        try {
            readFile = new Scanner(new File(args[0]));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + args[0] + " not found");
            System.exit(1);
        }

        System.out.println("Connection to file: " + args[0] + " successful");
        System.out.println();
        while (readFile.hasNext()) {
            s = readFile.next();
            System.out.println("Token found: " + s);
            count++;
        }

        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();

    }

    public static void main(String[] args) {
    }  

}  
