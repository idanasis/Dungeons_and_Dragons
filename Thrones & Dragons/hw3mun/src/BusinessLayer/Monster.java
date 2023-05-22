package BusinessLayer;
import java.util.Random;

public class Monster extends Enemy {
    private int visionRange;
    public Monster(char tile, String name, int healthCapacity, int attack, int defense,int experienceValue,int visionrange){
        super(tile,name,healthCapacity,attack,defense,experienceValue);
        this.visionRange = visionrange;

    }
    @Override
    public void gameTick() {}
    @Override
    public Position Move(Position pos) {
        double range = position.range(pos);
        return ((range < visionRange) ? moveTowardsPlayer(pos) : randomMove());
    }
    private Position moveTowardsPlayer(Position playerPos) {
        int xDiff = position.getX() - playerPos.getX();
        int yDiff = position.getY() - playerPos.getY();
        char whereTo = ((Math.abs(xDiff) > Math.abs(yDiff)) ? ((xDiff > 0) ? 'a' : 'd') : ((yDiff > 0) ? 'w' : 's'));
        return MoveTo(whereTo);
    }

    private Position randomMove() {
        char[] moves = {'w', 'a', 's', 'd'};
        int rnd = new Random().nextInt(moves.length);
        return MoveTo(moves[rnd]);
    }

    public String Description() {
        return getName() + "  Health: " +getHealth().getCurrent() + "/" +getHealth().getMax()
                + "  Attack: " +GetAttackPoints()
                + "  Defense: " +GetDefensePoints()
                + "  Experience Value: " + GetExperience()
                + "  Vision Range: " + visionRange;
    }
    private String GetExperience() {
        return getExperienceValue()+"";
    }
    private String GetDefensePoints() {
        return defense +"";
    }
    private String GetAttackPoints() {
        return attack +"";
    }

}