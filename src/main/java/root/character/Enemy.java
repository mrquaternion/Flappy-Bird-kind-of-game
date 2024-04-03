package character;

public class Enemy implements Character {
    protected boolean healthStatus = true;
    @Override
    public void attack(Character target) {

        System.out.println("The enemy attacks the target.");
    }
}
