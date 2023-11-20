package test;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import game.Game;
import player.Player;
import utility.Utility;
import cards.AttackCard;
import cards.BoostAttackCard;
import cards.Card;
import cards.ProtectCard;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class TestGame {

    public static ArrayList<Card> deck1;
    public static ArrayList<Card> deck2;
    public static ArrayList<Card> deckBoost;
    
    @BeforeClass
    public static void SetUp()
    {
        deck1=new ArrayList<Card>(); 
        deck1.add(new AttackCard(5));
        deck1.add(new AttackCard(3));
        deck1.add(new AttackCard(7));
        deck1.add(new BoostAttackCard());
        deck1.add(new BoostAttackCard());
        deck1.add(new BoostAttackCard());
        deck1.add(new ProtectCard());

        deck2=new ArrayList<Card>(); 
        deck2.add(new AttackCard(5));
        deck2.add(new AttackCard(3));
        deck2.add(new AttackCard(7));
        deck2.add(new BoostAttackCard());
        deck2.add(new BoostAttackCard());
        deck2.add(new BoostAttackCard());
        deck2.add(new BoostAttackCard());

        deckBoost=new ArrayList<Card>();
        for(int i=0;i<7;i++)
        {
            deckBoost.add(new BoostAttackCard());
        }
        
    }

    @Test
    public void testGameInitialization() {
        
        Player player1 = new Player(20, Utility.generateCards());
        Player player2 = new Player(20, Utility.generateCards());

        Game game = new Game(player1, player2);

        assertNotNull(game);
        assertFalse(game.getGameEnded());

    }

    @Test
    public void  testAttackPlayer1()
    {
          String moves = "2\n2\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck1);
            Player player2 = new Player(20, deck2);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(4, player1.getDamage());

    }

     @Test
    public void  testAttackedPlayerBlocksProtect()
    {
          String moves = "2\n2\n3\nend\n1\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(20, player2.getHealth());

    }
    @Test
    public void  testAttackedPlayerBlocksAttackOk()
    {
          String moves = "2\n3\nend\n5\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(20, player2.getHealth());

    }
    /* 
    @Test
    public void  testAttackedPlayerBlocksAttackNotOk()
    {
          String moves = "2\n3\nend\n7\take\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(15, player2.getHealth());

    }*/

    @Test
    public void  testAttackedPlayerTakesDamage()
    {
          String moves = "2\n3\nend\ntake\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(15, player2.getHealth());

    }
    @Test
    public void  testResetAttackStatus()
    {
          String moves = "2\n3\nend\ntake\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(false, player1.getAttackingStatus());

    }

    @Test
    public void  testAttackStatusBoost()
    {
          String moves = "2\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(false, player1.getAttackingStatus());

    }

    @Test
    public void  testResetDamage()
    {
          String moves = "2\n3\nend\ntake\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(0, player1.getDamage());

    }

        @Test
    public void  testDefenseWithWrongCard()
    {
          String moves = "2\n3\nend\n2\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {

        }
        assertEquals(15, player2.getHealth());

    }

      @Test
    public void  testDrawingDead()
    {
            boolean thrown=false;
            String moves = "end\nend\nend\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deck2);
            Player player2 = new Player(20, deck1);
        try
        {
            Game game = new Game(player1, player2);
            game.startGame();

        }
        catch(Exception e)
        {
            thrown=(e instanceof ArrayIndexOutOfBoundsException);

        }
        assertFalse(thrown);

    }

    @Test
    public void  testGameEndNoOptionsStartTurn()
    {
            boolean thrown=false;
            String moves = "2\n2\n2\n2\n2\n2\n2\nend\ntake\nend\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deckBoost);
            Player player2 = new Player(20, deck2);
            Game game = new Game(player1, player2);
        try
        {
            
            game.startGame();

        }
        catch(Exception e)
        {
            thrown=(e instanceof ArrayIndexOutOfBoundsException);

        }
        assertTrue(game.getGameEnded());

    }

    @Test
    public void  testGameEndNoOptionsEndTurn()
    {
            boolean thrown=false;
            String moves = "2\n2\n2\n2\n2\n2\n2\nend";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(20, deckBoost);
            Player player2 = new Player(20, deck2);
            Game game = new Game(player1, player2);
        try
        {
            
            game.startGame();

        }
        catch(Exception e)
        {
            thrown=(e instanceof ArrayIndexOutOfBoundsException);

        }
        assertTrue(game.getGameEnded());

    }
@Test
    public void  testGameEndNoHealth()
    {
            boolean thrown=false;
            String moves = "end";
            System.setIn(new ByteArrayInputStream(moves.getBytes()));
            Player player1 = new Player(0, deckBoost);
            Player player2 = new Player(20, deck2);
            Game game = new Game(player1, player2);
        try
        {
            
            game.startGame();

        }
        catch(Exception e)
        {
            thrown=(e instanceof ArrayIndexOutOfBoundsException);

        }
        assertTrue(game.getGameEnded());

    }

  



    
    

}
