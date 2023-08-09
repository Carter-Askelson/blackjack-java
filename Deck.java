import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> deck = new ArrayList<>();
    public Deck() {
        ArrayList <String> suits = new ArrayList<>();
        suits.add("Clubs");
        suits.add("Spades");
        suits.add("Hearts");
        suits.add("Diamonds");
        ArrayList <String> types = new ArrayList<>();
        types.add("2");
        types.add("3");
        types.add("4");
        types.add("5");
        types.add("6");
        types.add("7");
        types.add("8");
        types.add("9");
        types.add("10");
        types.add("Jack");
        types.add("Queen");
        types.add("King");
        types.add("Ace");
        var suits_iterator = suits.listIterator();
        var types_iterator = types.listIterator();
        for(String i: suits){
            for(String j: types){
                this.deck.add(new Card(i, j));
            }
        }
        //while (suits_iterator.hasNext()) {
        //    String current_suit = suits_iterator.next();
        //    types_iterator = types.listIterator();
        //    while (types_iterator.hasNext()){
        //        String current_type = types_iterator.next();
        //        this.deck.add(new Card(i, j));
        //   }
        //}
        Collections.shuffle(this.deck);
        // Additional initialization tasks
    }






    public void addCard(Card card) {
        this.deck.add(card);
    }

    public Card drawCard() {
        Card newCard = this.deck.get(deck.size() - 1);
        this.deck.remove(deck.size() - 1);
        return newCard;
    }
}
