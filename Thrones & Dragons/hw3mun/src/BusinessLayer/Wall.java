package BusinessLayer;
public class Wall extends Tile{

    public Wall() {
        super('#');
    }
    @Override
    public void gameTick() {
    }
    @Override
    public void unitVisit(Unit unit) {
        unit.visit(this);
    }
}
