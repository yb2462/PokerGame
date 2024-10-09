
/* author: Yana Botvinnik
    public class represent the Card in Poker game.
   */

public class Card implements Comparable<Card> {

    private int suit;
    private int rank;

    public Card(int s, int r) {
        suit = s;
        rank = r;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int compareTo(Card c) {
        int check;
        check = this.rank - c.getRank();
        return check;
    }

    public boolean sameSuit(Card c) {
        return this.suit == c.getSuit();
    }

    public String toString() {// checking the rank
        String toString = "";
        switch (rank) {
            case 1:
                toString = "Ace";
                break;
            case 2:
                toString = "2";
                break;
            case 3:
                toString = "3";
                break;
            case 4:
                toString = "4";
                break;
            case 5:
                toString = "5";
                break;
            case 6:
                toString = "6";
                break;
            case 7:
                toString = "7";
                break;
            case 8:
                toString = "8";
                break;
            case 9:
                toString = "9";
                break;
            case 10:
                toString = "10";
                break;
            case 11:
                toString = "Jack";
                break;
            case 12:
                toString = "Queen";
                break;
            case 13:
                toString = "King";
                break;

        }

        switch (suit) {// checking the suit
            case 1:
                toString = toString + " of clubs";
                break;
            case 2:
                toString = toString + " of diamonds";
                break;
            case 3:
                toString = toString + " of hearts";
                break;
            case 4:
                toString = toString + " of spades";
                break;
        }
        return toString;

    }
}
