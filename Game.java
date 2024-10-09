
/*author: Yana Botvinnik;
  */
import java.util.*;
import java.lang.*;

public class Game {

    private Player p;
    private Deck cards;

    public Game(String[] testHand) {

        /*
         * You can use testhand to check the correctness of the game:
         * example: testhand = {s1, s13, s12, s11, s10} = royal flush
         * 
         * c = clubs
         * d = diamonds
         * h = hearts
         * s = spades
         * 1-13 correspond to ace-king
         * example: s1 = ace of spades
         */
        p = new Player();
        cards = new Deck();
        makeHand(testHand);
        checkHand(p.getHand());

    }

    public Game() {
        p = new Player();
        cards = new Deck();
    }

    public void play() {

        cards.shuffle();

        System.out.println("Welcome to the Poker Video Game!");

        System.out.println("You have " + p.getBankroll() + " tokens");
        System.out.println("How many tokens would you like to bet from 1-5?");
        Scanner input = new Scanner(System.in);
        double amt = input.nextDouble();
        p.bets(amt);
        if (p.getBet() == 0) {
            System.out.println("Chose another amount of tokens");
            amt = input.nextDouble();
            p.bets(amt);
        }

        System.out.println("Your five cards are: ");
        if (p.getHand().size() == 0) {
            for (int i = 0; i < 5; i++) {
                p.addCard(cards.deal());
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(p.getHand().get(i).toString());
        }

        System.out.print("How many cards do you want to remove? ");
        System.out.println("You can remove 0-5 cards");
        int num = input.nextInt();
        if (num != 0) {
            for (int i = 0; i < num; i++) {
                System.out.println("What is the number of card you want to remove 1-5?");
                int index = input.nextInt();
                p.removeCard(p.getHand().get(index - 1));
            }
            for (int i = 0; i < num; i++) {
                p.addCard(cards.deal());
            }
        }

        Collections.sort(p.getHand());
        System.out.println("Your final cards are :");
        for (int i = 0; i < 5; i++) {
            System.out.println(p.getHand().get(i).toString());
        }
        System.out.println(checkHand(p.getHand()));
        System.out.println("Now you have " + p.getBankroll());
    }

    public String checkHand(ArrayList<Card> hand) {
        if (royalFlush(hand)) {
            p.winnings(250);
            return "Royal Flush!";
        }
        if (straightFlush(hand)) {
            p.winnings(50);
            return "Straight Flush!";
        }
        if (fourOfaKind(hand)) {
            p.winnings(25);
            return "Four of a kind!";
        }
        if (fullHouse(hand)) {
            p.winnings(6);
            return "Full house!";
        }
        if (flush(hand)) {
            p.winnings(5);
            return "Flush!";
        }
        if (straight(hand)) {
            p.winnings(4);
            return "Straight";
        }
        if (threeOfaKind(hand)) {
            p.winnings(3);
            return "Three of a Kind!";
        }
        if (twoPairs(hand)) {
            p.winnings(2);
            return "Two Pairs!";
        }
        if (onePair(hand)) {
            p.winnings(1);
            return "One Pair!";
        }
        return "No Pair!";
    }

    public boolean onePair(ArrayList<Card> hand) {
        for (int i = 0; i < 4; i++) {
            if (hand.get(i).compareTo(hand.get(i + 1)) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean twoPairs(ArrayList<Card> hand) {
        int pairs = 0;
        for (int i = 0; i < 4; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                pairs++;
                i++;
            }
        }
        if (pairs == 2) {
            return true;
        }
        return false;
    }

    public boolean threeOfaKind(ArrayList<Card> hand) {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            if (count == 3) {
                return true;
            }
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                count++;
            } else
                count = 1;
        }
        return false;
    }

    public boolean straight(ArrayList<Card> hand) {
        if (onePair(hand) == false) {
            if (hand.get(4).compareTo(hand.get(0)) == 4) {
                return true;
            }
            if (hand.get(0).getRank() == 1) {
                if (hand.get(4).compareTo(hand.get(1)) == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean flush(ArrayList<Card> hand) {
        for (int i = 0; i < 4; i++) {
            if (hand.get(i).sameSuit(hand.get(i + 1)) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean fullHouse(ArrayList<Card> hand) {
        if ((hand.get(0).getRank() == (hand.get(1).getRank()))) {
            if (hand.get(3).getRank() == (hand.get(4).getRank())) {
                if (hand.get(1).getRank() == (hand.get(2).getRank())
                        || (hand.get(2).getRank()) == (hand.get(3).getRank())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fourOfaKind(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size() - 3; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                if (hand.get(i).getRank() == hand.get(i + 2).getRank()) {
                    if (hand.get(i).getRank() == hand.get(i + 3).getRank()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean straightFlush(ArrayList<Card> hand) {
        if (flush(hand) && straight(hand)) {
            return true;
        }
        return false;
    }

    public boolean royalFlush(ArrayList<Card> hand) {
        if (straightFlush(hand) && hand.get(0).getRank() == 1) {
            return true;
        }
        return false;
    }

    public void makeHand(String[] testHand) {
        Card card;
        char suitCharacter;
        int suit;
        int rank;
        String rankString;
        for (int i = 0; i < 5; i++) {
            suitCharacter = testHand[i].charAt(0);
            rankString = testHand[i].substring(1, testHand[i].length());
            rank = Integer.parseInt(rankString);

            if (suitCharacter == 'c') {
                suit = 1;
            } else if (suitCharacter == 'd') {
                suit = 2;
            } else if (suitCharacter == 'h') {
                suit = 3;
            } else {
                suit = 4;
            }
            card = new Card(suit, rank);
            p.addCard(card);
        }
    }
}
