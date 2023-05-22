package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player{
    private Resource cooldown;

    protected Warrior(char tile, String name, int healthCapacity, int attack, int defense, int abilityCooldown) {
        super(tile, name, healthCapacity, attack, defense);
        this.cooldown = new Resource(abilityCooldown, 0);
    }
    protected void levelUp(){
        cooldown.setCurrent(0);
        health.addToMax(5*playerLevel);
        attack += 2*playerLevel;
        defense += playerLevel;
        super.levelUp();
    }
    @Override
    public void gameTick(){
        cooldown.reduceFromCurrent(1);
    }
    @Override
    public Enemy cast(ArrayList<Enemy> Enemies) {
        return cast(Enemies,0);
    }
    public Enemy cast(ArrayList<Enemy> Enemies, int seed) {
        //Avenger’s Shield, randomly hits one enemy withing range < 3 for an amount
        //equals to 10% of the warrior’s max health and heals the warrior for amount equals to (10×defense)
        //(but will not exceed the total amount of health pool).
        if(cooldown.getCurrent() == 0){
            cooldown.setCurrent(cooldown.getMax());
            health.addToCurrent(getDefense() * 10);
            List<Enemy> enemiesInRange = Enemies.stream().filter(t -> t.getPosition().range(getPosition()) < 3).toList();
            if (enemiesInRange.size() == 0) return null;
            int index = new Random().nextInt(enemiesInRange.size());
            Enemy theChosenOne = enemiesInRange.get(index);
            int damage = health.getMax() / 10;
            castAssist(this, theChosenOne, damage, "Avenger's Shield",0);
            if (theChosenOne.isDead()){
                onAbilityKill(theChosenOne);
            }
        }
        return null;
        // random hit 1 enemy in range of < 3 of 10% of health pool
    }
    @Override
    protected String getAbility() {
        return " Avengers Shield: cooldown "+cooldown.getCurrent()+"/"+cooldown.getMax();
    }
    @Override
    public String Description() {
        return GetName() + "  Health: " +getHealth().getCurrent() + "/" +getHealth().getMax()
                + "  Attack: " +GetAttackPoints()
                + "  Defense: " +GetDefensePoints()
                + "  Level: " +playerLevel
                + "  Experience: " + getExperience() + getAbility();
    }
}
