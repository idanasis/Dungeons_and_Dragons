package BusinessLayer;
public class Empty extends Tile {

    public Empty() {
        super('.');
    }
        @Override
    public void gameTick() {
    }
    @Override
    public void unitVisit(Unit unit) {
        unit.visit(this);
    }

}
