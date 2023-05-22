package BusinessLayer;

public class Position {
    protected int x;
    protected int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int compareTo(Position other) {
        if (getY() != other.getY())
            return getY() - other.getY();
        return getX() - other.getX();
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public double range(Position position){
        return Math.sqrt(Math.pow((getX() - position.getX()),2) + Math.pow((getY() - position.getY()),2));
    }
    public boolean equals(Position other) {
        return x == other.getX() && y == other.getY();
    }
    public String toString(){
        return "{"+getX()+","+getY()+"}";
    }
}
