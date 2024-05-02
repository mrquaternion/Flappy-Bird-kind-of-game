import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class View {
    private Pane root;
    private Model model;

    public View(Model model) {
        this.model = model;

    }

    private void createContent() {
        root = new Pane();

    }

    public Pane getRoot() {
        return root;
    }
}
