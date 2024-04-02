package character;

public class Enemy implements Character {
    @Override
    public void attack(Character target) {
        System.out.println("The enemy attacks the target.");
    }
}
