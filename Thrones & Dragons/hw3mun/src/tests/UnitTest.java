package tests;

import BusinessLayer.*;
import Callbacks.DeathCallBack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UnitTest {
    private Unit player;
    private Unit monster;
    private Unit trap;
    private TileFactory factory = new TileFactory();

    @BeforeEach
    public void initTest(){
        String[] players = {"1","2","3","4","5","6"};
        String rndPlayer = players[new Random().nextInt(players.length)];
        player = factory.CreatePlayer(rndPlayer);

        char[] enemys = {'s','k','q','z','b','g','w','M','C','K'};
        char rndMonster = enemys[new Random().nextInt(enemys.length)];
        monster = factory.CreateEnemy(rndMonster);

        char[] traps = {'B','Q','D'};
        char rndTrap = traps[new Random().nextInt(traps.length)];
        trap = factory.CreateEnemy(rndTrap);

        player.setPosition(new Position(1,1));
        monster.setPosition(new Position(2,2));
        trap.setPosition(new Position(3,3));
    }

    @Test
    void moveTo() {
        Assertions.assertTrue(new Position(1,0).equals(player.MoveTo('w')));
        Assertions.assertTrue(new Position(2,3).equals(monster.MoveTo('s')));
        Assertions.assertTrue(new Position(3,2).equals(monster.MoveTo('d')));
        Assertions.assertTrue(new Position(0,1).equals(player.MoveTo('a')));
        Assertions.assertTrue(new Position(-1,-1).equals(player.MoveTo('e')));
        Assertions.assertTrue(new Position(3,3).equals(trap.MoveTo('q')));
    }

    @Test
    void isDead() {
        assertFalse(monster.isDead());
        Unit deadMonster = new Monster('P',"DeadMonster",0,999,999,0,3);
        Assertions.assertTrue(deadMonster.isDead());
        assertEquals(player.isDead(),trap.isDead());
    }

    @Test
    void getAttack() {
        int attackTrap = 0;
        switch (trap.getTileChar()){
            case('B'):
            attackTrap = 1;
            case('Q'):
                attackTrap = 50;
            case('D'):
                attackTrap = 100;
        }
        assertEquals(attackTrap,trap.getAttack());
        assertTrue(player.getAttack() >=5 && player.getAttack() <= 40);
    }

    @Test
    void description() {
        Unit newMonster = factory.CreateEnemy(monster.getTileChar());
        assertEquals(newMonster.Description(), monster.Description());
    }
}