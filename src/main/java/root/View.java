import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.List;
import character.physics.Background;
import character.Enemy;
import character.Hero;
import character.item.Coin;

public class View {
    private BorderPane root;
    private Pane gamePane;

    private Text playerLife;
    private Text nbOfCoin;

    private Button pauseButton;

    private ImageView gameOverImageView;
    private Text merciRobinText;

    public View() {
        root = new BorderPane();
        gamePane = new Pane();
        root.setCenter(gamePane);
        setupStatusBar(root);
        setGameOverImageView();
        setMerciRobinText();
    }

    public Pane getRoot() { return root; }


    public void setupGameComponents(Background background, List<Hero> heroes, List<Coin> coins, Enemy enemy) {
        gamePane.getChildren().addAll(background.getImageViewBackground_1(), background.getImageViewBackground_2(), gameOverImageView, merciRobinText, enemy.getBullet().getImageView());
        coins.forEach(coin -> gamePane.getChildren().add(coin.getImageView()));
        heroes.forEach(hero -> gamePane.getChildren().add(hero.getImageView()));
        gamePane.getChildren().add(enemy.getImageView());
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

        statusBar.getChildren().addAll(pauseButton, separator1, playerLife, separator2,  nbOfCoin);
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

    public void update(int coin, int health) {
        updateStatusBar(coin, health);
    }

    public void toggleGameOver() {
        gameOverImageView.setVisible(true);
        merciRobinText.setVisible(true);
    }

    public void setGameOverImageView() {
        this.gameOverImageView = new ImageView("file:src/main/resources/gameOver.png");
        this.gameOverImageView.setFitWidth(200);
        this.gameOverImageView.setFitHeight(200);
        this.gameOverImageView.setX(200);
        this.gameOverImageView.setY(100);
        gameOverImageView.setVisible(false);
    }

    public void setMerciRobinText() {
        this.merciRobinText = new Text("Merci Robin!");
        merciRobinText.setX(200);
        merciRobinText.setY(100);
        merciRobinText.setFont(Font.font("Brush Script MT", 30));
        merciRobinText.setVisible(false);
    }
}
