package lab2;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class VocabController {

    public VocabView view;
    private VocabModel model;

    public void setView(VocabView v) {
        this.view = v;
    }

    public void setModel(VocabModel m) {
        this.model = m;
    }

    public void addToModel(String entry, String meaning) {
        if (isStringValid(entry) && isStringValid(meaning)) {
            boolean isOK = model.add(entry, meaning);
            if (isOK) {
                view.showMessage("Record replaced with new record");
            } else {
                view.showMessage("Record added");
            }
        } else {
            view.showMessage("Invalid input");
        }
    }

    public void deleteFromModel(String entry) {
        if (isStringValid(entry)) {
            boolean isOK = model.remove(entry);
            if (isOK) {
                view.showMessage("Record removed");
            } else {
                view.showMessage("Record not found");
            }
        } else {
            view.showMessage("Invalid input");
        }
    }

    public void lookupFromModel(String entry) {
        String meaning = model.lookup(entry);
        if (meaning == null) {
            view.showMessage("'" + entry + "' is not found");
        } else {
            view.showMessage("'" + entry + "' means " + meaning);
        }
    }

    public boolean isStringValid(String input) {
        return input != null && !input.trim().isEmpty();
    }
}
