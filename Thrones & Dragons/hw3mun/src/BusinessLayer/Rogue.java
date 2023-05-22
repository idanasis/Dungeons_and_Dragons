package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class Rogue extends Player{
    private int cost;
    private Resource energy;

    protected Rogue(char tile, String name, int healthCapacity, int attack, int defense,
                    int cost) {
        super(tile, name, healthCapacity, attack, defense);
        this.cost = cost;
        this.energy = new Resource(100, 100);
    }
    @Override
    protected void levelUp() {
        energy.setCurrent(100);
        attack += 3*playerLevel;
        super.levelUp();
    }
    @Override
    public void gameTick() {
        energy.addToCurrent(10);
    }
    @Override
    public Enemy cast(ArrayList<Enemy> Enemies) {
        return cast(Enemies,0);
    }
    public Enemy cast(ArrayList<Enemy> Enemies, int seed) {
        //Special ability: Fan of Knives, hits everyone around the rogue for an amount equals to the
        //rogue’s attack points at the cost of energy.
        if(energy.getCurrent() >= cost){
            energy.reduceFromCurrent(cost);
            List<Enemy> enemiesInRange = Enemies.stream().filter(t -> t.getPosition().range(getPosition()) < 2).toList();
            for(Enemy theChosenOne: enemiesInRange){
                castAssist(this, theChosenOne, attack,"Fan of Knives", seed);
            }
        }
        return null;
        //For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
        //attack points (each enemy will try to defend itself).
    }
    public String getAbility() {
        return "  Ability: Fan of Knives   energy: " + energy.getCurrent()+"/"+energy.getMax() +"   ability cost: " +cost ;
    }
    public String Description() {
        return GetName() + "  Health: " +getHealth().getCurrent() + "/" +getHealth().getMax()
                + "  Attack: " +GetAttackPoints()
                + "  Defense: " +GetDefensePoints()
                + "  Level: " +playerLevel
                + "  Experience: " + getExperience() + getAbility();
    }
}