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

import java.util.function.Consumer;

public class View {
    private BorderPane root;
    private Pane gamePane;

    private Text playerLife;
    private Text nbOfCoin;
    private Button pauseButton;

    private ImageView background1;
    private ImageView background2;



    public View() {
    }

    private void createContent() {
        root = new BorderPane();
        gamePane = new Pane();
        root.setCenter(gamePane);
        setupStatusBar(root);
        gamePane.getChildren().addAll(background.getImageViewBackground_1(), background.getImageViewBackground_2());
    }

    public void setupStatusBar(BorderPane root) {
        HBox statusBar = new HBox();
        statusBar.setAlignment(Pos.CENTER);
        statusBar.setPadding(new Insets(0, 0, 7, 0));
        statusBar.setSpacing(10);

        Button pauseButton = new Button("Pause");
        playerLife = new Text("Life: " );
        nbOfCoin = new Text("Coins: ");
        Separator separator1 = new Separator(Orientation.VERTICAL);
        Separator separator2 = new Separator(Orientation.VERTICAL);

        //pauseButton.setOnAction(event -> togglePause(pauseButton));

        statusBar.getChildren().addAll(pauseButton, separator1,  playerLife, separator2, nbOfCoin);
        root.setBottom(statusBar);
    }

    public void updateStatusBar(int coin, int health) {
        playerLife.setText("Life: " + health);
        nbOfCoin.setText("Coins: " + coin);
    }

    public void update(int coin, int health) {
        updateStatusBar(coin, health);

    }

    public Pane getRoot() {
        return root;
    }
}
