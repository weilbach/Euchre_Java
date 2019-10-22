import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Game {
    int team1Score = 0;
    int team2Score = 0;
    int dealer = 0;
    String trump;
    static String lead;

    Game() {
    }
    
    public void incrementDealer() {
        dealer++;
        if (dealer == 4) {
            dealer = 0;
        }
    }

    public int getDealer() {
        return dealer;
    }

    public void setTrump(String inputTrump) {
        trump = inputTrump;
    }
    public String getTrump() {
        return trump;
    }
    public static void setLead(String inputLead) {
        lead = inputLead;
    }
    public static String getLead() {
        return lead;
    }


    public void playGame(Deck inputDeck, Player p1, Player p2, Player p3, Player p4) {
        while (team1Score < 10 || team2Score < 10) {
            inputDeck.shuffleDeck();
            inputDeck.dealCards(p1, p2, p3, p4);
            List<Player> inputList = new ArrayList<>(Arrays.asList(p1,p2,p3,p4));   
            String trump = inputDeck.dealCard().getSuit();
            this.chooseTrump(inputList, trump);
            if (getTrump() == "not chosen") {
                chooseTrump2(inputList);
            }

        }
    }

    public void chooseTrump(List <Player> inputList, String trump) {
        int dealInd = getDealer();
        for (int i = 0; i < 4; i++) {
            int playerInd = (dealInd + i) % 4;
            Player curPlayer = inputList.get(playerInd);
            int trumpCards = curPlayer.countTrump(trump);
            if (trumpCards >= 2) {
                this.setTrump(trump);
                return;
            }
        }
        setTrump("not chosen"); 
    }

    public void chooseTrump2(List <Player> inputList) {
        int dealInd = getDealer();
        for (int i = 0; i < 4; i++) {
            int playerInd = (dealInd + i) % 4;
            Player curPlayer = inputList.get(playerInd);
            int number = curPlayer.countPlurality();
            if (number >= 3 || i == 3) {
                String suitChoice = curPlayer.findSuit(number);
                setTrump(suitChoice);
            }
        }
    }

    public static void main(String[] args) {
        Player p1 = new Player("Justin", 1);
        Player p2 = new Player("Sarah", 2); 
        Player p3 = new Player("Jourdan", 1); 
        Player p4 = new Player("Gus", 2);
        List<Player> inputList = new ArrayList<>(Arrays.asList(p1,p2,p3,p4));
        Deck euchreDeck = new Deck();
  
    }
}