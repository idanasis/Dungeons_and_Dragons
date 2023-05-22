package BusinessLayer;
public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    protected Trap(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue,int invisibilityTime,int visibilityTime) {
        super(tile, name, healthCapacity, attack, defense, experienceValue);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        ticksCount=0;
        visible = true;
    }

    public void gameTick(){
    }
    @Override
    public Position Move(Position pos) {
        visible = (ticksCount < visibilityTime);
        if (ticksCount == (visibilityTime + invisibilityTime))
            ticksCount=0;
        else
            ticksCount = ticksCount + 1;

        if(this.position.range(pos) < 2){ // in range
            return new Position(-1,-1);
        }
        return getPosition();
    }

    @Override
    public String Description() {
        return getName() + "  Health:" +getHealth().getCurrent() + "/" +getHealth().getMax()
                + "  Attack:" +getAttack()
                + "  Defense:" +getDefense()
                + "  Experience Value:" + getExperienceValue();
    }
    public char getTileChar(){
        return visible ? 'B' : '.';
    }
}
