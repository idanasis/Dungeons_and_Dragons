package BusinessLayer;

import Callbacks.DeathCallBack;
import Callbacks.MessageCallBack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Unit extends Tile {

    protected DeathCallBack deathCallBack;
    protected MessageCallBack messageCallBack;
    protected String name;
    protected Resource health;
    protected int attack;
    protected int defense;
    private InputProvider inputProvider = new InputProvider();

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile);
        this.name = name;
        this.health = new Resource(healthCapacity, healthCapacity);
        this.attack = attack;
        this.defense = defense;
    }
    public void initialize(Position position, DeathCallBack deathCallBack, MessageCallBack messageCallback){
        this.deathCallBack = deathCallBack;
        this.messageCallBack = messageCallback;
        super.initialize(position);
    }
    public Position MoveTo(char whereTo){
        return inputProvider.newPosition(getPosition(), whereTo);
    }
    public abstract void whenDies();
    protected void battle(Unit unit){
        battle(unit,0);
    }
    protected void battle(Unit unit, int seed)
    {
        Random randomGen;
        if(seed != 0){
            randomGen = new Random(seed);
        }
        else randomGen = new Random();
        messageCallBack.Send("\n" + this.getName() + " engaged in combat with " + unit.getName());
        messageCallBack.Send(Description());
        messageCallBack.Send(unit.Description());
        int randAttaack = randomGen.nextInt( attack + 1);
        int randDefence = randomGen.nextInt(unit.getDefense() + 1);
        messageCallBack.Send(this.getName() + " rolled " + randAttaack + " attack points ");
        messageCallBack.Send(unit.getName() + " rolled " + randDefence + " defense points ");
        int damage = Math.max((randAttaack-randDefence),0); // not negative
        messageCallBack.Send(this.getName() + " took "  +damage+ " health points from " + unit.getName() + "\n");
        unit.health.reduceFromCurrent(damage);
    }
    protected void castAssist(Player player, Enemy enemy, int damage, String abilityName){
            castAssist(player,enemy,damage,abilityName,0);
    }
    protected void castAssist(Player player, Enemy enemy, int damage, String abilityName, int seed){
        Random randomGen;
        if(seed != 0){
            randomGen = new Random(seed);
        }
        else randomGen = new Random();
        messageCallBack.Send(String.format("\n%s used the %s on %s", getName(), abilityName, enemy.getName()));
        messageCallBack.Send(enemy.Description());
        int randBlocked = randomGen.nextInt(enemy.getDefense() + 1);
        messageCallBack.Send(String.format("%s's damage is %s attack points", abilityName, damage));
        messageCallBack.Send(String.format("%s rolled %d defense points", enemy.getName(), randBlocked));
        int finalDamage = Math.max((damage-randBlocked ),0); // not negative
        messageCallBack.Send(String.format("%s took %d health points from %s\n", getName(), finalDamage, enemy.getName()));
        enemy.health.reduceFromCurrent(finalDamage);
        if(enemy.isDead())
            player.onAbilityKill(enemy);
    }
    public void visit(Empty  empty){
        swapPositions(empty);
    }
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);
    public void visit(Wall wall){ }

    protected void swapPositions(Tile t){
        Position pos = t.getPosition();
        t.setPosition(getPosition());
        setPosition(pos);
    }
    public boolean isDead() { return health.getCurrent() <= 0; }
    // getters for all the info
    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public Resource getHealth(){
        return health;
    }
    public String getName() {
        return name;
    }

    public abstract String Description();

}

