package Frontend;
import BusinessLayer.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Random;
import java.util.Scanner; // Import the Scanner class to read text files


public class GameManager {

    public static void main(String[] args) {
        startMenu();
        String chosenNumber = choosePlayer();
        TileFactory tileFactory = new TileFactory();
        Player player = tileFactory.CreatePlayer(chosenNumber);
        int levelNumber = 1;
        while (true)
        {
            Object [] level = readLevelFromTxt(levelNumber);
            if (level[0].equals("noMoreLevels")) {
                winScreen(chosenNumber);
                return;
            }
            else
            {
                levelNumber++;
                Board b = buildLevel(level,player);
                Game game = new Game(b);
                game.init();
                System.out.println("\n All Enemies are dead. Level up! \n");
            }
        }
    }
    private static String choosePlayer(){
        Scanner scanner = new Scanner(System.in);
        String chosenPlayer;
        String choice = null;
        while(choice == null){
            try
            {
                chosenPlayer = scanner.nextLine();
                int numberChosen = Integer.parseInt(chosenPlayer);
                if(numberChosen < 0 || numberChosen > 6)
                    System.out.println("You entered wrong input, please choose a number between 1 to 6");
                else
                    choice = chosenPlayer;
            }
            catch (NumberFormatException ignored){ }
        }
        return choice;
    }
    public static Object[] readLevelFromTxt(int levelNumber)
    {
        int height = 0, width = 0;
        String wantedLevel = "levels_dir/level"+levelNumber+".txt";
        System.out.printf("Level %d %n", levelNumber);
        StringBuilder board = new StringBuilder();
        try {
            File myObj = new File(wantedLevel);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                while(data.charAt(data.length()-1) == ' ')
                    data = data.substring(0, data.length()-1);
                width = data.length();
                height++;
                board.append(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No more levels found");
            board = new StringBuilder("noMoreLevels");
        }
        Object[] returnedValue = new Object[3];
        returnedValue[0] = board.toString();
        returnedValue[1] = height;
        returnedValue[2] = width;
        return returnedValue;
    }
    public static Board buildLevel(Object[] level, Player player)
    {
        Board board = new Board((int) level[1], (int) level[2]);
        board.buildTiles((String) level[0],player);
        return board;
    }
    public static void startMenu()
    {
        System.out.println( " -------------------------------------------------------------------------------------- " + "\n" +
                "|                                 Please choose a character:                           |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|                                          Warriors:                                   |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|      Name     |  Health  |  Attack  |  Defense  |  Cooldown |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|1.  Jon Snow   |    300   |    30    |     4     |     3     |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|2.  The Hound  |    400   |    20    |     6     |     5     |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|                                           Mages:                                     |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|      Name     |Health|Attack|Defense|Mana Pool|Mana Cost|Spell Power|Hit Count |Range|" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|3. Melisandre  |  100 |   5  |   1   |   300   |    30   |     15    |     5    |  6  |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|4.Thoros of Myr|  400 |  20  |   6   |   150   |    20   |     20    |     3    |  4  |" +"\n" +
                " ---------------------------------------------------------------------------------------" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|                                           Rogues:                                    |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|      Name     |  Health  |  Attack  |  Defense  |    cost   |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|5. Arya Stark  |    150   |    40    |     2     |     20    |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "|6.    Bronn    |    250   |    35    |     3     |     50    |" +"\n" +
                " -------------------------------------------------------------------------------------- " +"\n" +
                "" +"\n" +
                "" +"\n" +
                "" +"\n" +
                "                  To Choose a character please enter the character's number:                  " +"\n" +
                "                 ------------------------------------------------------------           ");
    }

    public static void winScreen(String heroNumber){
        String title = heroNumber.equals("3") | heroNumber.equals("5") ? "Queen" : "King";
        System.out.printf("You winter is ended! You are now the rightful %s of the realm%n", title);
        System.out.printf("Long live the %s!%n", title);
        System.out.printf("Long live the %s!%n", title);
        System.out.printf("Long live the %s!%n", title);
    }
}
