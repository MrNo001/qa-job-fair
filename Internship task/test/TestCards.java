package test;
import org.junit.Test;
import static org.junit.Assert.*;
import cards.*;

public class TestCards {

    @Test
    public void testAttackCardInitialization() {
        Card attackCard = new AttackCard(5); 
        assertEquals(5, attackCard.getNumber());
    }
     @Test
    public void testAttackCardInitializationNegative() {
        boolean thrown = false;

        try {
            Card attackCard = new AttackCard(-5);
            
        } catch (Exception e) {
            thrown = true;
        }
    
        assertTrue(thrown);
    }
     @Test
    public void testAttackCardInitializationOutOfBound() {
       boolean thrown = false;

        try {
            Card attackCard = new AttackCard(100);
            
        } catch (Exception e) {
            thrown = true;
        }
    
        assertTrue(thrown);
    }

    @Test
    public void testProtectCardInitialization() {
        Card protectCard = new ProtectCard();
        assertEquals(1, protectCard.getNumber());
    }

    @Test
    public void testBoostAttackCardInitialization() {
        Card boostAttackCard = new BoostAttackCard();
        assertEquals(2, boostAttackCard.getNumber());
    }
}
