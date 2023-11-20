package test;

import org.junit.Test;
import cards.*;
import utility.Utility;
import player.Player;
import utility.Utility;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

public class TestPlayer {

@Test
public void testTakeDamage(){//Done

    Player player = new Player(10, Utility.generateCards());
    player.takeDamage(1);
    assertEquals(9, player.getHealth());
}


@Test
    public void testPlayerInitialization() {//Done
       
       Player player = new Player(20, Utility.generateCards());

        assertNotNull(player);
        assertEquals(20, player.getHealth());
        assertEquals(0, player.getNumberOfCardsInHand());
        assertEquals(25, player.getNumberOfCardsInDeck());
        assertFalse(player.getAttackingStatus());
    }

    @Test
    public void testDrawingCards() {//Done

        List<Card> player1Deck = Utility.generateCards();
        Player player = new Player(20, player1Deck);

        player.drawCard();

        assertEquals(1, player.getNumberOfCardsInHand());
        assertEquals(24, player.getNumberOfCardsInDeck());
    }
     @Test
    public void testDrawingCardsEmpty() {//Done


        boolean thrown=false;

        try
        {
            List<Card> player1Deck = new ArrayList<Card>();
            Player player = new Player(20, player1Deck);
            player.drawCard();
        }
         catch (Exception e) {
            thrown=true;
        }

        
        assertFalse(thrown);
    }

    @Test
    public void testDrawingInitial() {//Done
        
        List<Card> player1Deck = Utility.generateCards();
        Player player = new Player(20, player1Deck);

        player.drawInitialCards();

        assertEquals(player.getInitialNumberOfCards(), player.getNumberOfCardsInHand());
        assertEquals(19, player.getNumberOfCardsInDeck());
    }

    @Test
    public void testPlayingCardsAttack() {
       
        Player player = new Player(20, Utility.generateCards());
        Card card = new AttackCard(5); 
        player.getHand().add(card);
        player.playCard(card.getNumber());


        assertEquals(0, player.getHand().size());
        assertEquals(0, player.getNumberOfCardsInHand());
        assertEquals(true, player.getAttackingStatus());
        assertEquals(5, player.getDamage());
        
    }
        @Test
    public void testPlayingCardsBoost() {//Done
       
        Player player = new Player(20, Utility.generateCards());
        Card card = new BoostAttackCard(); 
        player.getHand().add(card);
        player.playCard(card.getNumber());

        assertEquals(0, player.getNumberOfCardsInHand());
      
    }
    
    @Test
    public void testPlayingCardsProtect() {
       
        Player player = new Player(20, Utility.generateCards());
        Card card = new ProtectCard(); 
        player.getHand().add(card);
        player.playCard(card.getNumber());

        assertEquals(0, player.getNumberOfCardsInHand());
        // Add more assertions based on expected effects
    }

    @Test
    public void testCheckForProtectionPossibilitiesInHandProtect() {
       
        Player player = new Player(20, Utility.generateCards());
        Card cardProtect = new ProtectCard(); 
        Card cardAttack = new AttackCard(5);
        player.getHand().add(cardProtect);
       
        assertTrue( player.checkForProtectionPossibilitiesInHand(cardAttack));
        
    }
     @Test
    public void testCheckForProtectionPossibilitiesInHandAttack() {
       
        Player player = new Player(20, Utility.generateCards());
        
        Card cardAttack1 = new AttackCard(5);
        Card cardAttack2 = new AttackCard(5);
        player.getHand().add(cardAttack2);
       
        assertTrue( player.checkForProtectionPossibilitiesInHand(cardAttack1));
        
    }
    @Test
    public void testCheckForProtectionPossibilitiesInHandFalse() {
       
        Player player = new Player(20, Utility.generateCards());
        
        Card cardAttack1 = new AttackCard(5);
       
        assertFalse( player.checkForProtectionPossibilitiesInHand(cardAttack1));
        
    }
    @Test
    public void testCheckForProtectionPossibilitiesInHandAttackWrongNumber() {
       
        Player player = new Player(20, Utility.generateCards());
        
        Card cardAttack1 = new AttackCard(5);
        Card cardAttack2 = new AttackCard(4);
        player.getHand().add(cardAttack2);
       
        assertFalse( player.checkForProtectionPossibilitiesInHand(cardAttack1));
        
    }
   

    

    
}
