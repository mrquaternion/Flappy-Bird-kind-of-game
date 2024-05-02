import character.Enemy;
import character.Hero;
import character.HeroGenerator;
import character.item.Coin;
import character.item.CoinGenerator;

public class Model {
    private Enemy enemy;
    private Hero[] heroes;

    private Coin[] coins;



public Model() {
    HeroGenerator heroGenerator = new HeroGenerator();
    CoinGenerator coinGenerator = new CoinGenerator();
    enemy = new Enemy();
    }
}
