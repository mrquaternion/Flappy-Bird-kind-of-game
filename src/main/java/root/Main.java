import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Controller controller = new Controller();
            Scene scene = new Scene(controller.getView().getRoot(),640, 440);

            scene.setOnKeyPressed(event -> controller.handleKeyPress(event));


            stage.setTitle("Flappy Game");
            stage.setScene(scene);
            //Stage.set(false);

            Stage.setOnCloseRequest(event -> handleClose(controller, event));


            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleClose(Controller controller, WindowEvent event) {
        controller.stopTimer();  // Méthode pour arrêter l'AnimationTimer dans le contrôleur
    }

    public static void main(String[] args) {
        launch(args);
    }
}
