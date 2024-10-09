
/* Yana Botvinnik;
   UNI yb2462*/
import java.util.Random;

public class Deck {

    private Card[] cards;
    private int top;
    private int numSuit;
    private int numRanks;
    private int length;

    public Deck() {
        length = 52;
        top = 0;
        numSuit = 4;
        numRanks = 13;
        cards = new Card[length];
        int count = 0;
        for (int i = 1; i <= numSuit; i++) {
            for (int j = 1; j <= numRanks; j++) {
                cards[count] = new Card(i, j);
                count++;
            }
        }

    }

    public void shuffle() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int index1 = r.nextInt(52);
            int index2 = r.nextInt(52);
            Card card1 = cards[index1];
            Card card2 = cards[index2];
            cards[index1] = card2;
            cards[index2] = card1;
        }

    }

    public Card deal() {
        if (top == 51) {
            top = 0;
            return cards[51];
        }
        top++;
        return cards[top - 1];
    }

}
