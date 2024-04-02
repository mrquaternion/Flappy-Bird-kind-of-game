package character;

public class Hero implements Character {
    @Override
    public void attack(Character target) {
        System.out.println("The hero attacks the target.");
    }
}
