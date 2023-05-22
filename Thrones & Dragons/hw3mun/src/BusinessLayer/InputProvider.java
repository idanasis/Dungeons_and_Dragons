package BusinessLayer;

public class InputProvider {
    public InputProvider() { }

    public Position newPosition(Position currPos, char input)
    {
        return switch (input) {
            case 'w' -> new Position(currPos.getX(), currPos.getY() - 1);
            case 'a' -> new Position(currPos.getX() - 1, currPos.getY());
            case 's' -> new Position(currPos.getX(), currPos.getY() + 1);
            case 'd' -> new Position(currPos.getX() + 1, currPos.getY());
            case 'e' -> new Position(-1, -1); // cast special ability
            case 'q' -> currPos;
            default -> null;
        };
    }
}