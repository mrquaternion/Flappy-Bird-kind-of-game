import character.Scores;
import character.item.Bullet;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;

import java.util.List;

public class Controller {
    private Model model;
    private View view;

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
                model.shootVerification();
                break;
            default:
                break;
        }
    }

    private void setupGameComponents() {
        System.out.println("Setting up game components");
        view.setupGameComponents(model.getBackground(), model.getHeroes(), model.getCoins(), model.getEnemy());
    }

    private void setupTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (model.getEnemy().death()) {
                    stopTimer();
                } else {
                    if (!model.isPaused) {
                        model.updateGameState(now);
                    }
                }

                view.update(model.getCoinCount(), model.getEnemyHealthStatus(), model.getEnemy().getBullet());
            }
        };
        timer.start();
    }

    private void pauseGame() {
        if (model.isPaused) {
            timer.stop();
            model.getEnemy().gravityBlock();
        } else {
            timer.start();
        }
        view.updatePauseState();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
            Scores.save(model.getEnemy().getAllCoin());
        }
    }
}



