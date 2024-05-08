package lab2;

import java.util.*;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class VocabView {

    private VocabController controller;
    private Scanner scanner = new Scanner(System.in);

    public void setController(VocabController c) {
        this.controller = c;
    }

    public void processCommand() {
        boolean end = false;
        while (!end) {
            this.showMessage("Please enter your command ('/' + 'add/delete/exit') or an entry to lookup");
            System.out.print(">");
            String command = scanner.next();
            if (controller.isStringValid(command) && command.startsWith("/")) {
                switch (command) {
                    case "/add":
                        doAdd();
                        break;
                    case "/delete":
                        doDelete();
                        break;
                    case "/exit":
                        end = true;
                        break;
                    default:
                        this.showMessage("Invalid command");
                }
            } else if (controller.isStringValid(command)) {
                doLookup(command);
            } else {
                this.showMessage("Do enter a word or phrase");
            }
        }
    }

    public void doAdd() {
        System.out.print("Enter a word or phrase to be added: ");
        String entry = scanner.next();
        System.out.print("Enter its meaning: ");
        String meaning = scanner.next();
        controller.addToModel(entry, meaning);
    }

    public void doDelete() {
        System.out.print("Enter a word or phrase to be deleted: ");
        String entry = scanner.next();
        controller.deleteFromModel(entry);
    }

    public void doLookup(String entry) {
        controller.lookupFromModel(entry);
    }

    public void showMessage(String s) {
        System.out.println(s);
    }
}
