
/*author:  Yana Botvinnik
*/
import java.util.*;

public class Player {

    private ArrayList<Card> hand; // the player's cards
    private double bankroll;
    private double bet;

    public Player() {
        hand = new ArrayList<Card>();
        bankroll = 100;
        bet = 0;
    }

    public void addCard(Card c) {
        if (hand.size() <= 5) {
            hand.add(c);
        }

    }

    public void removeCard(Card c) {
        hand.remove(c);
    }

    public void bets(double amt) {

        if (amt <= 5 && amt <= bankroll) {
            bet = amt;
            bankroll = bankroll - amt;
        } else
            bet = 0;
    }

    public double getBet() {
        return bet;
    }

    public void winnings(double odds) {
        bankroll = bankroll + bet * odds;
    }

    public double getBankroll() {
        return bankroll;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

}
