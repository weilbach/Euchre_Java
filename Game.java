import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    int team1Score = 0;
    int team2Score = 0;
    int dealer = 0;
    int winningPosition = 0;
    int winningTeam = 0;
    String trump;
    static String lead = "oof";
    Card winningCard = new Card("garbage", "garbage", "garbage");

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
    public void setWinningCard(Card inputCard) {
        winningCard = inputCard;
    }
    public Card getWinningCard() {
        return winningCard;
    }
    public void setWinningPosition(int inputPosition) {
        winningPosition = inputPosition;
    }
    public int getWinningPosition() {
        return winningPosition;
    }
    public void setWinningTeam(int inputTeam) {
        winningTeam = inputTeam % 2;
    }
    public int getWinningTeam() {
        return winningTeam; 
    }
    public void incrementTeamScore(int winner) {
        if (winner == 0) {
            team1Score++;
        }
        else {
            team2Score++;
        }
    }


    public void playGame(Deck inputDeck, List <Player> inputList) {
        while (team1Score < 10 || team2Score < 10) {
            inputDeck.shuffleDeck();
            inputDeck.dealCards(inputList.get(0), 
                inputList.get(1),
                inputList.get(2),
                inputList.get(3));   
            String trump = inputDeck.dealCard().getSuit();
            this.chooseTrump(inputList, trump);
            if (getTrump() == "not chosen") {
                chooseTrump2(inputList);
            }
            playHand(inputList);
            inputDeck.resetDeck();
        }
        if (team1Score >= 10) {
            String name1 = inputList.get(0).name;
            String name2 = inputList.get(2).name;
            System.out.println(name1 + " and " + name2 + " won the game!");
        }
        else {
            String name1 = inputList.get(1).name;
            String name2 = inputList.get(3).name;
            System.out.println(name1 + " and " + name2 + " won the game!");
        }
    }

    public void playHand(List <Player> inputList) {
        int starter = getDealer() + 1;
        String trump = getTrump(); 
        int winningTeam = -1;
        int position = 0;
        int team1tricks = 0;
        int team2tricks = 0;
        int winner = -1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                starter = starter % 4; 
                Player next = inputList.get(starter);
                Card curr = next.chooseCard(trump, lead, winningCard, winningTeam, position);
                if (j == 0) {
                    setLead(curr.suit);
                    setWinningPosition(starter);
                    setWinningTeam(starter);
                    setWinningCard(curr);
                }
                else {
                    if (curr.compareCard(getWinningCard(), trump, getLead())) {
                        setWinningPosition(starter);
                        setWinningTeam(starter);
                        setWinningCard(curr);
                    }
                }
                position++;
                starter++;
            }
            starter = getWinningPosition();
            if (getWinningTeam() == 0) {
                team1tricks++;  
            }
            else {
                team2tricks++;  
            }
        }
        //this is after all the cards were played 
        if (team1tricks > team2tricks) {
            winner = 0;
            System.out.println("Team 1 won the trick");
        }
        else {
            winner = 1;
            System.out.println("Team 2 won the trick");
        }
        incrementTeamScore(winner);
        
    }

    public void chooseTrump(List <Player> inputList, String trump) {
        int dealInd = getDealer();
        for (int i = 0; i < 4; i++) {
            int playerInd = (dealInd + i) % 4;
            Player curPlayer = inputList.get(playerInd);
            int trumpCards = curPlayer.countTrump(trump);
            if (trumpCards >= 2) {
                setTrump(trump);
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

        Game euchre = new Game(); 
        euchre.playGame(euchreDeck, inputList);

    }
}