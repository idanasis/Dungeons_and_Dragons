package BusinessLayer;
public abstract class Tile implements Comparable<Tile> {
    protected char tile;
    protected Position position;
    // constructor get the char that represent the tile type
    protected Tile(char tile){
        this.tile = tile;
    }
    // set the initial postion of the tile
    public void initialize(Position position){
        this.position = position;
    }
    // get and set of the tile
    public char getTileChar() {return tile;}
    public void setTileChar(char tile) { this.tile = tile;}
    // get and set of the tile postion
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position getPosition) {
        this.position = getPosition;
    }
    // compare tiles base on the postion
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }
    // one turn of the game
    public abstract void gameTick();
    // interact by units
    public abstract void unitVisit(Unit u);



}
