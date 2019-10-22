import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

public class Deck {
    List<Card> cards = new ArrayList<Card>();
    int size = 24; 

    Deck() {
        String ranks[]  =  new String[]{"9", "10", "jack", "queen", "king", "ace"}; 
        String suit[] = new String[]{"Spades", "Clubs", "Hearts", "Diamonds"}; 
        int ind = 0;
        int ind2 = 0;
        String color;

        for (int i = 0; i < 24; i++) {
            ind = i % 6;
            if (i > 0 && i % 6 == 0) {
                ind2++;
            }
            if (ind2 <= 1) {
                color = "black";
            }
            else {
                color = "red";
            }
            Card toAdd = new Card(suit[ind2], ranks[ind], color);
            cards.add(toAdd); 
        }
    }

    public void printDeck() {
        for (int i = 0; i < 24; i++) {
            System.out.println(cards.get(i).rank + " of " + cards.get(i).suit);
        }
    }

    public int deckSize() {
        return cards.size();
    }

    public void shuffleDeck() {

        Random rand = new Random();

        for (int i = 0; i < cards.size(); i++) {

            int r = i + rand.nextInt(cards.size() - i);
            Card temp = cards.get(r);
            Card temp2 = cards.get(i);
            cards.set(r, temp2);
            cards.set(i, temp); 
        }
    }

    public Card dealCard() {
        Card toReturn = cards.get(-1);
        cards.remove(-1);
        return toReturn;
    }

    public void dealCards(Player p1, Player p2, Player p3, Player p4) {
        for (int i = 0; i < 20; i++) {
            int val = i % 4;
            if (val == 0){
                p1.hand.add(dealCard());
            }
            if (val == 1) {
                p2.hand.add(dealCard());
            }
            if (val == 2) {
                p3.hand.add(dealCard());
            }
            if (val == 3) {
                p4.hand.add(dealCard()); 
            }   
        }   
    }

    public static void main(String[] args) {
        Deck myDeck = new Deck();
        myDeck.printDeck();
        myDeck.shuffleDeck();
        System.out.println("new cards");
        myDeck.printDeck();
    
    }
}