package Frontend;
import BusinessLayer.*;
import Callbacks.MessageCallBack;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player player;
    private ArrayList<Enemy> enemies;
    protected MessageCallBack messageCallBack;
    public Game(Board b)
    {
        this.board = b;
        this.player = b.getPlayer();
        this.enemies = b.getEnemies();
        this.messageCallBack = System.out::println;
    }
    public void init() {
        while (!enemies.isEmpty()) {
            board.MakeTiles();
            board.PrintTheBoard();
            messageCallBack.Send(player.Description());
            char c = getInput();
            Position playerWantedPosition = null;
            while(playerWantedPosition == null){
                playerWantedPosition = player.MoveTo(c);
            }
            if (playerWantedPosition.getX() == -1) {
                Enemy enemy = player.cast(enemies);
                if(enemy!=null) {
                    enemies.remove(enemy);
                }
            }
            else {
                Tile t = board.GetTile(playerWantedPosition);
                t.unitVisit(player);
            }
            for (Enemy enemy : enemies) {
                Position enemyMove = enemy.Move(player.getPosition());
                if (enemyMove.getX() == -1) {
                    enemy.visit(player);
                }
                else{
                    Tile t = board.GetTile(enemyMove);
                    t.unitVisit(enemy);

                }
            }
            board.TickAll();
        }
    }

    private char getInput() {
        char[] validChars = new char[] {'q', 'w', 'e', 'a', 's', 'd'};
        Scanner scanner = new Scanner(System.in);
        Character input = null;
        while (input == null) {
            try {
                String received = scanner.nextLine();
                if(received.length() == 1)
                    input = received.charAt(0);
                else {
                    System.out.println("wrong input, please choose a valid key");
                    throw new StringIndexOutOfBoundsException();
                }
                for (char c : validChars) {
                    if (input == c) {
                        return input;
                    }
                }
                input = null;
                System.out.println("wrong input, please choose a valid key");
            } catch (StringIndexOutOfBoundsException e) { input = null;}
        }
        return input;
    }
}