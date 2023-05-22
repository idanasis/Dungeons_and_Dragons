package BusinessLayer;

public class TileFactory {
    public Player CreatePlayer(String input) {
        return switch (input) {
            case "1" -> new Warrior('@', "Jon Snow", 300, 30, 4, 3);
            case "2" -> new Warrior('@', "The Hound", 400, 20, 6, 5);
            case "3" -> new Mage('@', "Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
            case "4" -> new Mage('@', "Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4);
            case "5" -> new Rogue('@', "Arya Stark", 150, 40, 2, 20);
            case "6" -> new Rogue('@', "Bronn", 250, 35, 3, 50);
            default -> null;
        };
    }

    public Enemy CreateEnemy(char input)  {
        return switch (input) {
            case 's' -> new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
            case 'k' -> new Monster('k', "Lannister Knight", 200, 14, 8, 50, 4);
            case 'q' -> new Monster('q', "Queen's Guard", 400, 20, 15, 100, 5);
            case 'z' -> new Monster('z', "Wright", 600, 30, 15, 100, 3);
            case 'b' -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250, 4);
            case 'g' -> new Monster('g', "Giant-Wright", 1500, 100, 40, 500, 5);
            case 'w' -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6);
            case 'M' -> new Monster('M', "The Mountain", 1000, 60, 25, 500, 6);
            case 'C' -> new Monster('C', "Queen Cersei", 100, 10, 10, 1000, 1);
            case 'K' -> new Monster('K', "Night’s King", 5000, 300, 150, 5000, 8);
            case 'B' -> new Trap('B', "Bonus Trap", 1, 1, 1, 250, 1, 5);
            case 'Q' -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 7);
            case 'D' -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10);
            default -> null;
        };
    }
}