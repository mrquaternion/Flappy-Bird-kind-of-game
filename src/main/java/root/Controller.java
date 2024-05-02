import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
public class Controller {
    private Model model;
    private View view;

    public AnimationTimer timer;

    public Controller() {
        this.model = new Model();
        this.view = new View();
        setupTimer();
    }

    public View getView() {
        return view;
    }

    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE:
                model.togglePause();  // Implémenter la fonction de saut dans le modèle
                break;
            case W:
                model.toggleJump();  // Implémenter la fonction de saut dans le modèle
                break;
            case E:
                model.toggleShoot();  // Implémenter la fonction de tir dans le modèle
                break;
            default:
                break;
        }
        view.update(model.getCoinCount(), model.getEnemyHealthStatus()); // Mettre à jour la vue si nécessaire
    }

    private void setupTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //model.updateGameState();  // Méthode pour mettre à jour l'état du jeu
                view.update(model.getCoinCount(), model.getEnemyHealth());  // Mettre à jour la vue avec les nouvelles données
            }
        };
        timer.start();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

}



