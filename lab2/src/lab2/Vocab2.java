package lab2;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class Vocab2 {

    public static void main(String[] args) {
        VocabView view = new VocabView();
        VocabModel model = new VocabModel();
        VocabController control = new VocabController();

        model.connectDB();
        
        view.setController(control);
        control.setModel(model);
        control.setView(view);
        model.setController(control);

        view.processCommand();
        
        model.closeDB();
    }
}
