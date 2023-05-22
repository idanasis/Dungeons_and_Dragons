package Frontend;

import BusinessLayer.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    public List<Tile> tiles = new ArrayList<>();
    private TileFactory tileFactory = new TileFactory();
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int width;
    private int heigth;

    public Board(int heigth, int width){
        this.width = width;
        this.heigth = heigth;
    }

    public void buildTiles(String theBoardasChar, Player player)
    {
        this.player = player;
        int index = 0;
        for (char tile : theBoardasChar.toCharArray())
        {
            Position tilePos = new Position(index%width, index/width);
            switch (tile) {
                case '.' -> {
                    Empty empty = new Empty();
                    empty.initialize(tilePos);
                    tiles.add(empty);
                }
                case '#' -> {
                    Wall wall = new Wall();
                    wall.initialize(tilePos);
                    tiles.add(wall);
                }
                case '@' -> {
                    player.initialize(tilePos, this::playerDead, this::printToScreen);
                    tiles.add(player);
                }
                default -> {
                    Enemy enemy = tileFactory.CreateEnemy(tile);
                    enemy.initialize(tilePos, () -> remove(enemy), this::printToScreen);
                    if (enemy == null) {
                        Empty empty = new Empty();
                        empty.initialize(tilePos);
                        tiles.add(empty);
                    } else {
                        tiles.add(enemy);
                        enemies.add(enemy);
                    }
                }
            }
            index++;
        }
    }
    public void MakeTiles() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());}

    public void remove(Enemy e) {
        enemies.remove(e);
        tiles.remove(e);
        Empty empty = new Empty();
        tiles.add(empty);
        empty.initialize(e.getPosition());

    }

    private void playerDead(){
        player.setTileChar('X');
        PrintTheBoard();
        System.out.println("YOU LOST");
        System.exit(0);
    }



    public void TickAll(){
        tiles.forEach(Tile::gameTick);
    }
    private void printToScreen(String s){
        System.out.println(s);
    }
    public void PrintTheBoard()
    {
        StringBuilder board = new StringBuilder();
        for(Tile t: tiles){
            Position pos = t.getPosition();
            board.append(t.getTileChar());
            if(pos.getX() == width-1)
                board.append("\n");
        }
        System.out.println(board);
    }

    public Tile GetTile(Position p){
        for(Tile t: tiles){
            if(t.getPosition().equals(p))
                return t;
        }
        return tiles.get(width*p.getY() + p.getX()); // correct line
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public Player getPlayer() {
        return this.player;
    }
}