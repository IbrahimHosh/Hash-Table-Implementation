import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable<T>{
    NGen<T>[] table;
    public HashTable(){
        table = new NGen[113];                                                        // set default table to 113 which is a prime number and reduces collisions
    }
    public HashTable(int size){table = new NGen[size];}
    private int Hash1(T item){
        if(item.toString().length() == 1){
            return (26 * item.toString().charAt(0)) % table.length;                                                              // Hash function with the least amount of average collisions. Does this by comparing first and last element then modulo.
        }
        return (26356 * item.toString().charAt(0) + item.toString().charAt(item.toString().length()-1)) % table.length;
    }
    private int Hash2(T item){
        if(item.toString().length() == 1){
            return (item.toString().charAt(0)) % table.length;                                   // hash function with the second least average collisions. adds first and second then modulo
        }
        return (item.toString().charAt(0) + item.toString().charAt(1)) % table.length;
    }
    private int Hash3(T item){
        return (table.length + item.toString().charAt(0)) / table.length; // has function with the most collisions
    }
    public void add(T item){
        if(item ==null){
            return; }
        int index = Hash1(item);
        if(table[index] == null){table[index] = new NGen<T>(item,null);}
        else{
            NGen<T> temp = table[index];
            while(temp!= null){
                if(temp.getData().equals(item)){                                  // checks if item is a duplicate. if not then adds.
                    return;
                }
                temp = temp.getNext();
            }
            NGen<T> new_node = new NGen<>(item,table[index]);
            table[index] = new_node;
        }
    }
    public void display(){
        int longest_chain = 0;
        int non_empty = 0;
        int unique = 0;
        int avg_col = 0;
        for(int i = 0; i < table.length;i++){
            int count = 0;
            if(table[i] != null){
                non_empty++;                                                  // goes through linked list and adds to non-empty
            }
            while(table[i]!= null){
                count++;
                table[i] = table[i].getNext();
            }
            if(count > longest_chain){
                longest_chain = count;                                               // checks longest chain
            }
            unique += count;
            System.out.println( i +": "+ count);
        }
        int empty = table.length - non_empty;
        avg_col = unique/non_empty;
        System.out.println("Average Collision Length: " + avg_col);
        System.out.println("Longest chain: " + longest_chain);
        System.out.println("Unique tokens: " + unique);
        System.out.println("non-empty indexes: " + non_empty);
        System.out.println("empty indexes: " + empty);

    }
    public static void main(String args[]){
        HashTable hashTable = new HashTable();
        Scanner readFile = null;
        String s;
        int count = 0;
        args = new String[1];
        args[0] = "C:\\Users\\leona\\Desktop\\Project5\\src\\keywords.txt";               // if you want to run a file change arg[0] to file path as string.
//        System.out.println();
//        System.out.println("Attempting to read from file: " + args[0]);
        try {
            readFile = new Scanner(new File(args[0]));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + args[0] + " not found");
            System.exit(1);
        }

//        System.out.println("Connection to file: " + args[0] + " successful");
//        System.out.println();
        while (readFile.hasNext()) {
            s = readFile.next();
//            System.out.println("Token found: " + s);
            hashTable.add(s);
            count++;
        }

//        System.out.println();
//        System.out.println(count + " Tokens found");
//        System.out.println();
        hashTable.display();
    }
}
