package BusinessLayer;

import java.util.ArrayList;

public abstract class Player extends Unit {
    protected int experience;
    protected int playerLevel;

    protected Player(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile, name, healthCapacity, attack, defense);
        experience = 0;
        playerLevel = 1;
    }
    @Override
    public void whenDies() {
        deathCallBack.Call();
    }

    public void unitVisit(Unit unit) { unit.visit(this); }
    @Override
    public void visit(Enemy e) {
        this.battle(e);
        if(e.isDead())
            onKill(e);
    }
    public void visit(Player p){}


    public abstract Enemy cast(ArrayList<Enemy> Enemies);


    protected void levelUp(){
        experience = experience - 50*playerLevel;
        playerLevel+=1;
        health = new Resource(health.getMax() +10*playerLevel,health.getMax()+10*playerLevel);
        attack += 4*playerLevel;
        defense += playerLevel;
        messageCallBack = System.out::println;
        messageCallBack.Send(GetName() +" reached level "  +getPlayerLevel() +": +" +10*playerLevel+ " Health Points, +"+(4 * playerLevel) + " Attack Points, +"+playerLevel +" Defence Points\n" );
    }

    protected String GetName(){
        return name;
    }

    public int getPlayerLevel(){
        return playerLevel;
    }


    public String Description(){
        return getName() + "\t  Health: " +getHealth().getCurrent() + "/" +getHealth().getMax()
                + "\t  Attack: " +getAttack()
                + "\t  Defense: " +getDefense()
                + "\t  Level: " +playerLevel
                + "\n  Experience: " + getExperience() + "\t" + getAbility();
    }

    protected abstract String getAbility();

    public void onKill(Enemy e){
        swapPositions(e);
        onAbilityKill(e);
    }

    public void onAbilityKill(Enemy e){
        messageCallBack.Send(e.getName() + " died. " + GetName() + " gained " + e.getExperienceValue() + " experience points" +"\n");
        experience+=e.getExperienceValue();
        while(experience >= playerLevel*50 ) {
            levelUp();
        }
        e.whenDies();
    }
    protected String GetAttackPoints() {
        return attack +"";
    }
    protected String GetDefensePoints() {
        return defense +"";
    }
    protected String getExperience() {
        return this.experience + "/"+this.playerLevel*50;
    }

}
