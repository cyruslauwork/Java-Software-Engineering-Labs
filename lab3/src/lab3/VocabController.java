package lab3;

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

    public void addToModel(VocabData vocabdata) {
        if (isStringValid(vocabdata.entry) && isStringValid(vocabdata.meaning) && isStringValid(vocabdata.wordClass)) {
            boolean isOK = model.add(vocabdata);
            if (isOK) {
                view.showMessage("Record added/replaced", true);
            } else {
                view.showMessage("Internal database error", true);
            }
        } else {
            view.showMessage("Invalid input", false);
        }
    }

    public void deleteFromModel(String entry) {
        if (isStringValid(entry)) {
            boolean isOK = model.remove(entry.trim());
            if (isOK) {
                view.showMessage("Record removed", true);
            } else {
                view.showMessage("Record not found", false);
            }
        } else {
            view.showMessage("Invalid input", false);
        }
    }

    public void lookupFromModel(String entry) {
        if (isStringValid(entry)) {
            String[] res = model.lookup(entry.trim());
            if (res == null) {
                view.showMessage("'" + entry + "' is not found", false);
            } else {
                view.showRes(res[0], res[1]);
            }
        } else {
            view.showMessage("Invalid input", false);
        }
    }

    public boolean isStringValid(String input) {
        return input.matches("[a-zA-Z]+");
    }
}
