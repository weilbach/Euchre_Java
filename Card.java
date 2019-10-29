import java.util.HashMap;
import java.util.Map;

public class Card {
    String suit;
    String rank;
    String color;
    HashMap <String, Integer> powers = new HashMap<>();

    Card(String inputSuit, String inputRank, String inputColor) {
        suit = inputSuit;
        rank = inputRank;
        color = inputColor;
        powers.put("9", 1);
        powers.put("10", 2);
        powers.put("jack", 3);
        powers.put("queen", 4);
        powers.put("king", 5);
        powers.put("ace", 6);
        powers.put("left", 7);
        powers.put("right", 8); 
    }

    public String toString() {
        return rank + " of " + suit;
    }

    public String getSuit() {
        return suit; 
    }

    public void updateBauer(String bauer, String trump) {
        rank = bauer;
        suit = trump;
    }

    public boolean compareCard(Card inputCard, String trump, String lead) {
        if (suit == trump && inputCard.suit != trump) {
            return true; 
        }
        else if (suit != trump && inputCard.suit == trump) {
            return false;
        }

        else if (suit == lead && inputCard.suit != lead) {
            return true;
        }

        else if (suit != lead && inputCard.suit == lead) {
            return false;
        }

        else {
            return powers.get(rank) > powers.get(inputCard.rank); 
        }

    }
}