package tests;

import BusinessLayer.*;
import Callbacks.MessageCallBack;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Mage mage;
    private Rogue rouge;
    private Warrior warrior;
    private Monster monster;
    private Trap trap;
    private TileFactory factory = new TileFactory();
    protected MessageCallBack messageCallBack;

    @BeforeEach
    public void initTest() {
        String[] warriors = {"1","2"};
        String[] mages = {"3","4"};
        String[] rogues = {"5","6"};
        warrior = (Warrior) factory.CreatePlayer(warriors[new Random().nextInt(2)]);
        mage = (Mage) factory.CreatePlayer(mages[new Random().nextInt(2)]);
        rouge = (Rogue) factory.CreatePlayer(rogues[new Random().nextInt(2)]);


        char[] enemys = {'s','k','q','z','b','g','w','M','C','K'};
        char rndMonster = enemys[new Random().nextInt(enemys.length)];
        monster = (Monster) factory.CreateEnemy(rndMonster);


        char[] traps = {'B','Q','D'};
        char rndTrap = traps[new Random().nextInt(traps.length)];
        trap = (Trap) factory.CreateEnemy(rndTrap);



        warrior.initialize(new Position(1,1), this::nothing,this::nothing);
        mage.initialize(new Position(1,1), this::nothing,this::nothing);
        rouge.initialize(new Position(1,1), this::nothing,this::nothing);
        monster.initialize(new Position(2,1), this::nothing,this::nothing);
        trap.initialize(new Position(1,2), this::nothing,this::nothing);
    }
    private void nothing(String s) {}
    private void nothing(){}

    @Test
    void cast() {
        Player[] players = {mage,warrior,rouge};
        for(Player p: players) {

            char[] enemys = {'s', 'k', 'q', 'z', 'b', 'g', 'w', 'M', 'C', 'K'};
            char rndMonster = enemys[new Random().nextInt(enemys.length)];
            Monster farMonster = (Monster) factory.CreateEnemy(rndMonster);
            farMonster.initialize(new Position(10, 0), this::nothing, this::nothing);

            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            enemies.add(farMonster);
            enemies.add(monster);
            int monsterBefore = monster.getHealth().getCurrent();
            int farMonsterBefore = farMonster.getHealth().getCurrent();

            p.cast(enemies);
            Assert.assertTrue(monster.getHealth().getCurrent() <= monsterBefore);
            Assert.assertTrue(farMonster.getHealth().getCurrent() == farMonsterBefore);
        }

    }

    @Test
    void levelUp() {
        Player[] players = {mage,warrior,rouge};
        for(Player p: players) {
            int attackBefore = p.getAttack();
            Enemy monsterToLvlUp = new Monster('P', "EasyEXP", 1, 0, 0, 96, 100);
            monsterToLvlUp.initialize(new Position(6, 9), this::nothing, this::nothing);
            while(!monsterToLvlUp.isDead())
                p.visit(monsterToLvlUp);
            assertTrue(attackBefore < p.getAttack());
            assertEquals(2, p.getPlayerLevel());
        }
    }

    @Test
    void onKill() {
        Player[] players = {mage,warrior,rouge};
        for(Player p: players) {
            trap.initialize(new Position(1,2), this::nothing,this::nothing);
            p.onKill(trap);
            assertTrue(new Position(1,2).equals(p.getPosition()));
        }
    }
}