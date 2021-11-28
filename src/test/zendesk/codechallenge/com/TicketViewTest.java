package test.zendesk.codechallenge.com;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import zendesk.codechallenge.com.TicketView;

/**
 * TicketView Tester.
 *
 * @author Xinlei Bian
 * @version 1.0
 * @Date Nov 26, 2021
 */
public class TicketViewTest {

  /**
   * Method: isInt(String num)
   */
  @Test
  public void testIsInt() {
    assertTrue(TicketView.isInt("2312421"));
    assertTrue(TicketView.isInt("2"));
    assertTrue(TicketView.isInt("3"));
    assertFalse(TicketView.isInt("!kjsf"));
    assertFalse(TicketView.isInt("12111111111111111111111"));
    assertFalse(TicketView.isInt(""));
  }

} 
