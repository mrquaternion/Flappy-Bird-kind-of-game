import character.Scores;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;

public class Controller {
    private final Model model;
    private final View view;

    public AnimationTimer timer;

    public Controller() {
        this.model = new Model();
        this.view = new View();
        setupGameComponents();
        setupTimer();
    }

    public View getView() {
        return view;
    }

    // -------------- Key Press Handling --------------
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE:
                model.togglePause();
                pauseGame();
                break;
            case W:
                model.toggleJump();
                break;
            case E:
                model.toggleShoot();
                break;
            default:
                break;
        }
    }

    // -------------- Setup --------------

    private void setupGameComponents() {
        view.setupGameComponents(model.getBackground(), model.getHeroes(), model.getCoins(), model.getEnemy());
    }

    private void setupTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (model.getEnemy().death()) {
                    view.toggleGameOver();
                    stopTimer();
                } else {
                    if (!model.isPaused) {
                        model.updateGameState(now);
                    }
                }
                // Update the view
                view.update(model.getCoinCount(), model.getEnemyHealthStatus());
            }
        };
        timer.start();
    }

    // -------------- Pause --------------
    private void pauseGame() {
        if (model.isPaused) {
            timer.stop();
        } else {
            timer.start();
        }
        view.updatePauseState();
    }

    // -------------- Stop Timer --------------
    public void stopTimer() {
        if (timer != null) {
            timer.stop();
            Scores.save(model.getEnemy().getAllCoin());
        }
    }
}



