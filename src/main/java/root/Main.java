import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Controller controller = new Controller();
            Scene scene = new Scene(controller.getView().getRoot(),640, 440);

            scene.setOnKeyPressed(controller::handleKeyPress);

            stage.setTitle("Flappy Game");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            scene.getRoot().requestFocus();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
