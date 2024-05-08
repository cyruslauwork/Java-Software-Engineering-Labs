package lab1;

//
// COM3101 Lab 1
// Vocab
// Student ID: s226064
// Student Name: LAU Ka Pui
// Sesson: L02
//
import java.util.*;
import java.io.*;

public class Vocab {
    
    boolean exit = false;

    //Scanners and data structure here
    Scanner scanner = new Scanner(System.in);
    HashMap<String, String> vocab = new HashMap();

    public void handleCommands() {
        // Repeat until /exit
        do {
            //	   prompt for the next command
            //	   and call the appropriate methods (doAdd doDelete doFind doImport) to handle it
            System.out.println("Enter a word in English, or a valid command (/add, /del, /import, /exit)");
            String userInput = scanner.nextLine();
            if (userInput.startsWith("/")) {
                // Remove the leading "/" character
                String command = userInput.substring(1).trim();
                switch (command) {
                    case "add":
                        doAdd();
                        break;
                    case "del":
                        doDelete();
                        break;
                    case "exit":
                        exit = true;
                        break;
                    case "import":
                        doImport();
                        break;
                    default:
                        System.out.println("Invalid command");
                }
            } else {
                doFind(userInput);
            }
        } while (exit != true);
        scanner.close();
    }

    public void doAdd() {
        // prompt the users to enter a word and a meaning pair, and put them into your data structure 
        System.out.println("Enter a word or phrase to be added");
        String entry = scanner.nextLine();
        System.out.println("Enter its meaning");
        String meaning = scanner.nextLine();
        vocab.put(entry, meaning);
    }

    public void doDelete() {
        // prompt the user to enter a word, and remove it (word and meaning) from your data structure
        // show appropriate messages to indicate the results (i.e., successfully removed or nt)
        try {
            System.out.println("Enter the word or phrase to be deleted: ");
            String entry = scanner.nextLine();
            vocab.remove(entry);
            System.out.println("Entry deleted");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException occurred: " + e.getMessage());
        }
    }

    public void doFind(String word) {
        // prompt the user to enter a word, 
        // find the meaning from the data structure and show it to the user
        // show an appropriate message if the word is not found
        try {
            String meaning = vocab.get(word);
            if (meaning != null) {
                System.out.printf("'%s' means %s\n", word, meaning);
            } else {
                System.out.printf("'%s' is not found\n", word);
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException occurred: " + e.getMessage());
        }
    }

    public void doImport() {
        // prompt the user to enter a file name
        // load all entries (word and meaning pairs) from the file into your data structure
        // show appripriate messages in case of errors.
        try {
            // Prompt the user to enter the name of the text file
            System.out.print("Enter the name of file to import from: ");
            String fileName = scanner.nextLine();

            // Create a FileReader to read the text file
            FileReader fileReader = new FileReader(fileName);

            // Create a BufferedReader to read lines from the file
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into word and meaning using the colon delimiter
                String[] entry = line.split(":");

                // Check if the line has a valid format with word and meaning
                if (entry.length >= 2) {
                    String word = entry[0].trim();
                    String meaning = entry[1].trim();

                    // Add the word and meaning pair to the data structure
                    vocab.put(word, meaning);
                }
                count++;
            }

            // Close the readers
            bufferedReader.close();
            fileReader.close();

            if (count > 1) {
                System.out.printf("%d entries imported.\n", count);
            } else if (count == 1) {
                System.out.println("1 entry imported.");
            } else {
                System.out.println("No entry imported.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please make sure the file exists and try again.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        // load all saved entries from the binary file Vocab.dat into your data structure
        // continue if the file is not found (which is possible if the app is run for the first time)
        try {
            // Create a File object representing the "vocab.dat" file
            File file = new File("vocab.dat");

            // Check if the file exists
            if (file.exists()) {
                // Create a FileInputStream to read from the file
                FileInputStream fileInputStream = new FileInputStream(file);

                // Create an ObjectInputStream to read objects from the file
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                // Read the HashMap object from the file
                vocab = (HashMap<String, String>) objectInputStream.readObject();

                // Close the streams
                objectInputStream.close();
                fileInputStream.close();

                if (vocab.size() > 1) {
                    System.out.printf("%d entries loaded.\n", vocab.size());
                } else if (vocab.size() == 1) {
                    System.out.println("1 entry loaded.");
                } else {
                    System.out.println("No entry loaded.");
                }
            } else {
                System.out.println("No existing data found. Starting with an empty vocabulary.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        }
    }

    public void save() {
        // Save all entries from your data structure into the binary file Vocab.dat
        try {
            // Create a FileOutputStream to write to the file
            FileOutputStream fileOutputStream = new FileOutputStream("vocab.dat");

            // Create an ObjectOutputStream to write objects to the file
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write the HashMap object to the file
            objectOutputStream.writeObject(vocab);

            // Close the streams
            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Records saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Vocab vb = new Vocab();

        vb.load(); //load previously saved entries from the file Vocab.dat (if any)
        vb.handleCommands(); //handle the commands from the user 
        vb.save(); //save all entries back into the file Vocab.dat before exiting

    }
}
