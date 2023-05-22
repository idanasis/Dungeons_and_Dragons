package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player{
    private Resource mana;
    private int cost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    protected Mage(char tile, String name, int healthCapacity, int attack, int defense,
                   int manaPool, int cost, int spellPower, int hitsCount, int abilityRange) {
        super(tile, name, healthCapacity, attack, defense);
        this.mana = new Resource(manaPool, manaPool/4);
        this.cost = cost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }
    @Override
    protected void levelUp() {
        mana.addToMax(25*playerLevel);
        mana.addToCurrent(mana.getMax()/4.0);
        spellPower += 10*playerLevel;
        super.levelUp();
    }
    @Override
    public void gameTick() {
        mana.addToCurrent(playerLevel);
    }
    @Override
    public Enemy cast(ArrayList<Enemy> Enemies) {
        return cast(Enemies,0);
    }
    public Enemy cast(ArrayList<Enemy> Enemies, int seed) {
        //Special ability: Blizzard, randomly hit enemies within range for an amount equals to the mage’s
        //spell power at the cost of mana.
        //The mage cannot cast the ability if current mana < cost.
        if(mana.getCurrent() >= cost){
            mana.reduceFromCurrent(cost);
            int hits = 0;
            List<Enemy> enemiesInRange = Enemies.stream().filter(t -> (t.getPosition().range(getPosition()) < abilityRange)).toList();
            //select random enemy in range
            //deal damage
            while((hits < hitsCount) & !enemiesInRange.isEmpty()){
                int index = new Random().nextInt(enemiesInRange.size());
                Enemy theChosenOne = enemiesInRange.get(index);
                if(theChosenOne != null)
                    castAssist(this,theChosenOne,spellPower,"Blizzard", seed);
                enemiesInRange = Enemies.stream().filter(t -> (t.getPosition().range(getPosition()) < abilityRange)).toList(); // upadte the enemies
                hits++;
            }
        }
        return null;
    }
    @Override
    protected String getAbility() {
       return "  Ability: Blizzard   mana pool: " +mana.getCurrent()+"/"+mana.getMax() + "   mana cost: " + cost
                + "   spell power:" + spellPower + "   hits count: " + hitsCount + "   ability range: " + abilityRange ;
    }
    public String Description() {
        return GetName() + "  Health: " +getHealth().getCurrent() + "/" +getHealth().getMax()
                + "  Attack: " +GetAttackPoints()
                + "  Defense: " +GetDefensePoints()
                + "  Level: " +playerLevel
                + "  Experience: " + getExperience() + getAbility();
    }
}