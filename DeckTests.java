
public class DeckTests {

    public static void main(String[] args) {
        System.out.println("Welcome to unit testing for the deck class");
        System.out.println();

        Deck test = new Deck();
        assert test.deckSize() == 24 : "ERB";

        
    }
}