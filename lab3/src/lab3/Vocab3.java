package lab3;

/**
 *
 * @author LAU Ka Pui (s226064)
 */
public class Vocab3 {

    public static void main(String[] args) {
        VocabView view = new VocabView();
        VocabModel model = VocabModel.getInstance();
        VocabController control = new VocabController();

        model.connectDB();
        
        view.setController(control);
        control.setModel(model);
        control.setView(view);
        model.setController(control);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setVisible(true);
            }
        });
        
//        model.closeDB();
    }
}
