package tests;

import BusinessLayer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileTest {
    private Empty empty;
    private Tile wall;
    private Tile enemy;
    private Tile player;
    private char rndEnemy;
    private String rndPlayer;

    @BeforeEach
    public void initTest(){
        TileFactory factory = new TileFactory();
        empty = new Empty();
        wall = new Wall();
        char[] enemys = {'s','k','q','z','b','g','w','M','C','K','B','Q','D'};
        rndEnemy = enemys[new Random().nextInt(enemys.length)];
        enemy = factory.CreateEnemy(rndEnemy);
        String[] players = {"1","2","3","4","5","6"};
        rndPlayer = players[new Random().nextInt(players.length)];
        player = factory.CreatePlayer(rndPlayer);

        player.setPosition(new Position(1,1));
        enemy.setPosition(new Position(2,2));
        wall.setPosition(new Position(3,3));
        empty.setPosition(new Position(4,4));


    }

    @Test
    void getTileChar() {
        assertEquals('@', player.getTileChar());
        assertEquals(rndEnemy, enemy.getTileChar());
        assertEquals('#', wall.getTileChar());
        assertEquals('.', empty.getTileChar());
    }

    @Test
    void getPosition() {
        assertEquals(new Position(1,1),player.getPosition());
        assertEquals(new Position(2,2),enemy.getPosition());
        assertEquals(new Position(3,3),wall.getPosition());
        assertEquals(new Position(4,4),empty.getPosition());
    }

    @Test
    void compareTo(){
        assertEquals(2,wall.compareTo(player));
        assertEquals(-1,player.compareTo(enemy));
    }
}