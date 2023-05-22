package BusinessLayer;
public abstract class Enemy extends Unit {
    private final int experienceValue;

    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue) {
        super(tile, name, healthCapacity, attack, defense);
        this.experienceValue=experienceValue;
    }
    @Override
    public void visit(Player p) {
        battle(p);
        if (p.isDead())
            p.whenDies();
    }
    @Override
    public void visit(Enemy e) {
    }
    public void unitVisit(Unit unit) {
        unit.visit(this);
    }
    public abstract Position Move(Position pos);
    @Override
    public void whenDies() {
        deathCallBack.Call();
    }
    public abstract String Description();
    public int getExperienceValue(){
        return experienceValue;
    }
}
