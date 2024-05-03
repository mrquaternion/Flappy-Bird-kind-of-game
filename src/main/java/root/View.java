import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import character.item.Bullet;
import java.util.List;
import character.physics.Background;
import javafx.scene.Node;
import character.Enemy;
import character.Hero;
import character.item.Coin;

import java.util.function.Consumer;

public class View {
    private BorderPane root;
    private Pane gamePane;

    private Text playerLife;
    private Text nbOfCoin;

    private Button pauseButton;

    public View() {
        createContent();
    }

    public Pane getRoot() { return root; }

    private void createContent() {
        root = new BorderPane();
        gamePane = new Pane();
        root.setCenter(gamePane);
        setupStatusBar(root);
    }

    public void setupGameComponents(Background background, List<Hero> heroes, List<Coin> coins, Enemy enemy) {
        gamePane.getChildren().addAll(background.getImageViewBackground_1(), background.getImageViewBackground_2());
        coins.forEach(coin -> gamePane.getChildren().add(coin.getImageView()));
        heroes.forEach(hero -> gamePane.getChildren().add(hero.getImageView()));
        gamePane.getChildren().add(enemy.getImageView());
        System.out.println(gamePane.getChildren());
    }

    public void setupStatusBar(BorderPane root) {
        HBox statusBar = new HBox();
        statusBar.setAlignment(Pos.CENTER);
        statusBar.setPadding(new Insets(0, 0, 7, 0));
        statusBar.setSpacing(10);

        pauseButton = new Button("Pause");
        playerLife = new Text("Life: " );
        nbOfCoin = new Text("Coins: ");
        Separator separator1 = new Separator(Orientation.VERTICAL);
        Separator separator2 = new Separator(Orientation.VERTICAL);

        statusBar.getChildren().addAll(pauseButton, separator1, separator2, playerLife, nbOfCoin);
        root.setBottom(statusBar);
    }

    public void updateStatusBar(int coin, int health) {
        playerLife.setText("Life: " + health);
        nbOfCoin.setText("Coins: " + coin);
    }

    public void updatePauseState() {
        if (pauseButton.getText().equals("Pause")) {
            pauseButton.setText("Resume");
        } else {
            pauseButton.setText("Pause");
        }
    }

    public void update(int coin, int health, List<Bullet> bullets) {
        updateStatusBar(coin, health);
        updateBullets(bullets);
    }

    public void updateBullets(List<Bullet> bullets) {

        for (Bullet bullet : bullets) {
            if (!root.getChildren().contains(bullet.getImageView())) {
                root.getChildren().add(bullet.getImageView());
            }
        }
    }
}
