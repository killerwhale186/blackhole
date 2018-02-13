//Meng Chau
//AP comp sci
//1/30/18
import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class lab12 {

    public static void main(String[] args) {

        Record[] alphabet = new Record[26]; // array of 26 records for each alphabet and it's frequency

        File cipher = new File("Cipher.txt"), replace = new File("Replace.txt");
        Scanner file = null, replacers = null;
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Scanner in = new Scanner(System.in);
        int stillInUse = 1;

        try {
            file = new Scanner(cipher); // file for organizing the frequencies of each original letter
            replacers = new Scanner(replace); // file to read in each associate letter for each frequency
        }
        catch (FileNotFoundException e) {
            System.out.print("File(s) " + cipher + " and/or " + replace + " does not exist");
            System.exit(0);
        }

        for (int k = 0; k < alphabet.length; k++) {
            alphabet[k] = new Record(); // initialize with 0 count paired with letters a-z using constructor methods
            alphabet[k].setFrequency(0);
            alphabet[k].setLetter(letters.charAt(k));
        }

        while (file.hasNextLine() == true) {
            String next = file.nextLine();
            for (int j = 0; j < next.length(); j++) {
                if (Character.isLetter(next.charAt(j)) == true) {
                    alphabet[letters.indexOf(next.charAt(j))].increment();
                    // as it reads in each line of the file, it increments one
                    // count for the corresponding letter based on it's position
                    // in the abc's
                }
            }
        }

        System.out.print("Here are the frequencies of each letter in the file " + cipher + ":\n\n");
        for (int i = 0; i < alphabet.length; i++) {
            System.out.print(alphabet[i].getLetter() + ": " + alphabet[i].getFrequency() + "\n");
        }

        System.out.print("\nHere is the list of record objects and their frequencies in decending order:\n\n");
        Arrays.sort(alphabet);
        for (int d = 0; d < alphabet.length; d++) {
            System.out.print(alphabet[d].getLetter() + ": " + alphabet[d].getFrequency() + "\n");
        }

        // assigns the new frequency chart field for each record object a-z
        for (int a = 0; a < alphabet.length; a++) {
            alphabet[a].setAssociate(replacers.next().charAt(0));
        }

        System.out.print("\nHere is the list of record objects and their associate:\n\n");
        for (int d = 0; d < alphabet.length; d++) {
            System.out.print(alphabet[d].getLetter() + ": " + alphabet[d].getAssociate() + "\n");
        }
        
        while (stillInUse == 1) {
            //AAA
            //Record[] alphabet2 = alphabet;
            //alphabet2 = new Record[alphabet.length];
            //for (int i = 0; i < alphabet.length; i++) {
            //    alphabet2[i] = alphabet[i];
            //}

            Scanner file2 = null;
            try {
                file2 = new Scanner(cipher); // file for printing out the un-coded message
            }
            catch (FileNotFoundException e) {
                System.out.print("File(s) " + cipher + " and/or " + replace + " does not exist");
                System.exit(0);
            }

            //AAA
            Arrays.sort(alphabet);
            System.out.print("\nFiltering duplicate frequencies in the text file-");
            for (int b = 0; b < alphabet.length - 1; b++) {
                if (alphabet[b].getFrequency() == alphabet[b + 1].getFrequency()) { // if the current frequency is unique
                    System.out.print("\nDuplicate frequencies found with ["
                            + Character.toUpperCase(alphabet[b].getLetter()) + ":" +
                            Character.toUpperCase(alphabet[b].getAssociate()) + " and "
                            + Character.toUpperCase(alphabet[b + 1].getLetter()) + ":" +
                            Character.toUpperCase(alphabet[b + 1].getAssociate()) + "] (original : associate)");

                    System.out.print("\nchoose a re-assignment order for the two respective associate letters: \n");
                    char replacement = in.next().charAt(0);
                    char replacement2 = in.next().charAt(0);
                    alphabet[b].setAssociate(replacement); // needs to switch, not replace both!! <--------------
                    alphabet[b + 1].setAssociate(replacement2);
                    b++;
                }
            }

            //AAA
            Arrays.sort(alphabet, new LetterComparator());
            System.out.print("\n\n[Here is the decoded message]: \n\n");
            while (file2.hasNextLine() == true) {
                String nextLine = file2.nextLine();
                for (int i = 0; i < nextLine.length(); i++) {
                    if (Character.isLetter(nextLine.charAt(i)) == false) {
                        System.out.print(nextLine.charAt(i)); // prints non letter characters
                    }
                    else { // if the character is a letter, print the associate of that letter
                        //AAA
                        //for (int k = 0; k < alphabet.length; k++) { // organizes and replaces based on frequency
                        //    if (alphabet2[k].getLetter() == nextLine.charAt(i)) { // once the corresponding original letter and index is found
                        //        System.out.print(Character.toUpperCase(alphabet2[k].getAssociate())); // print its associate letter from the frequency chart
                        //    }
                        //}
                        int index = letters.indexOf(nextLine.charAt(i)); // index of original letter correlates directly to the associate in the array
                        System.out.print(Character.toUpperCase(alphabet[index].getAssociate()));
                    }
                }
                System.out.print("\n");
            }

            System.out.print("\nWould you like to decode again? (1 yes || 0 no): ");
            stillInUse = in.nextInt();
            if (stillInUse == 0) { // outputting text to file
                File output = new File("output.txt");
                Scanner file3 = null;
                PrintWriter writer = null;

                try {
                    file3 = new Scanner(cipher); // file for writing the final message to a text file
                    writer = new PrintWriter(output); // file writing destination
                }
                catch (FileNotFoundException e) {
                    System.out.print("File(s) " + cipher + " and/or " + output + " does not exist");
                    System.exit(0);
                }

                Arrays.sort(alphabet, new LetterComparator()); // sort by original letter

                while (file3.hasNextLine() == true) {
                    String nextLine = file3.nextLine();
                    for (int i = 0; i < nextLine.length(); i++) {
                        if (Character.isLetter(nextLine.charAt(i)) == false) {
                            writer.print(nextLine.charAt(i));
                        }
                        else {
                            int index = letters.indexOf(nextLine.charAt(i)); // index of original letter correlates directly to the associate in the array
                            writer.print(Character.toUpperCase(alphabet[index].getAssociate()));
                        }
                    }
                    writer.print("\n");
                }
                System.out.print("\nMessage has been read into the file " + output + ", quitting program");
                writer.close();
                System.exit(0);
            }
        }
    }
}