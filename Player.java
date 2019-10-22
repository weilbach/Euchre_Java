import java.util.ArrayList;
import java.util.List;


public class Player {
    List<Card> hand = new ArrayList<Card>();
    String name;
    int team;

    Player(String inputName, int inputTeam) {
        name = inputName; 
        team = inputTeam;
        System.out.println("Welcome " + name + " to Euchre!"); 
    }

    public void addCard(Card inputCard) {
        hand.add(inputCard);
    }

    public int handSize() {
        return hand.size();
    }

    public int getTeam() {
        return team;
    }

    public int countTrump(String trump) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (hand.get(i).suit == trump) {
                count++;
            }
        }
        return count;
    }

    public int countPlurality() {
        int spades = 0;
        int clubs = 0;
        int hearts = 0;
        int diamonds = 0;
        int max = 0;

        for (int i = 0; i < 5; i++) {
            String suit = hand.get(i).suit;
            if (suit == "clubs") {
                clubs++;
                if (clubs > max) {
                    max = clubs;
                }
            }
            else if (suit == "diamonds") {
                diamonds++;
                if (diamonds > max) {
                    max = diamonds;
                }
            }
            else if (suit == "spades") {
                spades++;
                if (spades > max) {
                    max = spades;
                }
            }
            else {
                hearts++;
                if (hearts > max) {
                    max = hearts;
                }
            }
        }
        return max;
    }

    public String findSuit(int suitCount) {
        int diamonds = 0;
        int clubs = 0;
        int spades = 0;
        int hearts = 0;
        int handsize = handSize();
        for (int i = 0; i < handsize; i++) {
            String suit = hand.get(i).suit;
            if (suit == "clubs") {
                clubs++;
                if (clubs == suitCount) {
                    return "clubs";
                }
            }
            else if (suit == "diamonds") {
                diamonds++;
                if (diamonds == suitCount) {
                    return "diamonds";
                }
            }
            else if (suit == "spades") {
                spades++;
                if (spades == suitCount) {
                    return "spades";
                }
            }
            else {
                hearts++;
                if (hearts == suitCount) {
                    return "hearts";
                }
            }
        }
        System.out.println("you fucked up justin");
        return "this messed up if it got here lol"; 
    }

    public int worstCard(String trump) {
        int handSize = handSize();
        int minIndex = 0;
        Card minCard = hand.get(0);
        int minRank = minCard.powers.get(minCard.rank);
        for (int i = 1; i < handSize; i++) {
            Card compCard = hand.get(i);
            int compRank = compCard.powers.get(compCard.rank);

            if (compCard.suit != trump && minCard.suit == trump) {
                minCard = compCard;
                minRank = compRank;
                minIndex = i;
            }
            else if (compCard.suit != trump && minCard.suit != trump) {
                if (compRank < minRank) {
                    minCard = compCard;
                    minRank = compRank;
                    minIndex = i;
                }
            }
            else {
                if (compRank < minRank) {
                    minCard = compCard;
                    minRank = compRank;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    public int bestCard(String trump) {
        int handsize = handSize();
        int maxInd = 0;
        Card maxCard = hand.get(0);
        int maxRank = maxCard.powers.get(maxCard.rank);
        for (int i = 1; i < handsize; i++) {
            Card compCard = hand.get(i);
            int compRank = compCard.powers.get(compCard.rank);

            if (compCard.suit != trump && maxCard.suit == trump) {
                continue;
            }
            else if (compCard.suit != trump && maxCard.suit != trump) {
                if (maxRank < compRank) {
                    maxCard = compCard;
                    maxRank = compRank;
                    maxInd = i;
                }
            }
            else {
                if (maxRank < compRank) {
                    maxCard = compCard;
                    maxRank = compRank;
                    maxInd = i;
                }
            }
        }
        return maxInd;
    }

    public void exchangeCard(Card inputCard, String trump) {
        int minInd = -1;
        Card minCard = hand.get(0);
        int minRank = minCard.powers.get(minCard.rank);
        for (int i = 1; i < 5; i++) {
            Card compCard = hand.get(i);
            int compRank = compCard.powers.get(compCard.rank);
            if (compCard.suit != trump && compRank < minRank) {
                minCard = compCard;
                minRank = compRank;
                minInd = i;
            }
        }

        if (minCard.suit == trump && minCard.powers.get(minCard.rank) > minCard.powers.get(inputCard.rank)) {
            return;
        }
        else {
            hand.remove(minInd);
            addCard(inputCard);
        }
    }

    public Card playCard(int cardIndex) {
        Card toReturn = hand.get(cardIndex);
        hand.remove(cardIndex);
        System.out.println(name + " played a " + toReturn.rank + " of " + toReturn.suit);
        return toReturn; 
    }

    public boolean hasLead(String lead) {
        //function to determine whether or not the player
        //has a card of the lead suit in their hand
        int handsize = handSize();
        for (int i = 0; i < handsize; i++) {
            if (hand.get(i).suit == lead) {
                return true; 
            }
        }
        return false;
    }

    public Card chooseCard(String trump, String lead, Card winningCard, int winningTeam, int position) {
        int bestInd = bestCard(trump);
        int worstInd = worstCard(trump);
        Card bestCard = hand.get(bestInd);
        // String lead = game.getLead();
        
        if (position == 1) {
            return playCard(bestInd);
            // game.setLead(bestCard.getSuit());
        }

        if (position == 2) {
            if (hasLead(lead))
            if (bestCard.compareCard(winningCard, trump, lead)) {
                return playCard(bestInd);
            }
            else {
                return playCard(worstInd); 
            }
        //this isn't finished
        }

        if (position == 3) {
            if (winningCard.suit != trump) {
                if (bestCard.compareCard(winningCard, trump, lead)) {
                    return playCard(bestInd);
                }
                else {
                    return playCard(worstInd); 
                }
            }
            else if (winningCard.suit == trump && winningCard.powers.get(winningCard.rank) <= 2) {
                if (bestCard.compareCard(winningCard, trump, lead)) {
                    return playCard(bestInd);
                }
                else {
                    return playCard(worstInd); 
                }
            }
            else if (winningTeam == getTeam()) {
                return playCard(worstInd); 
            }
        }

        if (position == 4) {
            if (hasLead(lead)) {
                System.out.println("under development");
            }
            else {
                if (winningTeam == getTeam()) {
                    return playCard(worstInd);
                }
                else if (bestCard.compareCard(winningCard, trump, lead)) {
                    return playCard(bestInd);
                }
                else {
                    return playCard(worstInd);
                }
            }
        }
        return playCard(bestInd); 
    }
}