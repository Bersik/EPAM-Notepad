import controller.Controller;
import model.Notebook;
import view.View;

/**
 * Created on 2:19 09.05.2016
 *
 * @author Bersik (Serhii Kisilchuk)
 */
public class Main {
    public static void main(String[] args) {
        Notebook notebook = new Notebook();
        View view = new View();
        Controller controller = new Controller(notebook, view);
        // Run
        controller.processUser();
    }
}
