
public class CardTests {
    public static void main(String[] args) {
        System.out.println("beginning tests for the card class");
        System.out.println();

        String ranks[]  =  new String[]{"9", "10", "jack", "queen", "king", "ace"}; 
        String suit[] = new String[]{"Spades", "Clubs", "Hearts", "Diamonds"};
        String trump = "Diamonds";
        String lead = "Spades";

        Card right = new Card("Diamonds", "right", "red");
        Card left = new Card("Hearts", "left", "red");
        left.updateBauer("left", trump);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                Card test = new Card(suit[j], ranks[i], "red");
                System.out.println(test); 
                assert test.getSuit() == suit[j] : "ERB";
                assert test.rank == ranks[i] : "ERB";
                assert right.compareCard(test, trump, lead) == true : "ERB";
                assert left.compareCard(test, trump, lead) == true : "ERB";
            }
        }

        Card test = new Card("Diamonds", "king", "red");

        assert test.compareCard(left, trump, lead) == false : "ERB";

        System.out.println("unit tests for the card class have passed");
    }
}