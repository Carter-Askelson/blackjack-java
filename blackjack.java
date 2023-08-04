import java.util.*;

public class Blackjack {

    private static Integer chips = 1000;
    private static Integer buy_in = 100;

    public static void main(String[] args) {
        // Your code goes here
        System.out.println("Run Program");
        

        Random rng = new Random();
        rules();
        start_round();
    }

    private static void start_round() {
        buy_in = 100;
        System.out.println("");
        if (chips > 99) {
            chips -= buy_in;
            System.out.println("Buy-in is 100 chips.");
            System.out.println("You're current Chip total is: " + chips + ".\n");
        }
        else {
            System.out.println("Looks like Lady Luck wasn't with you tonight. You're don't have enough chips for the buy-in. Game Over.");
            System.exit(0);
        }

        ArrayList <Card> deck = new ArrayList<>();
        deck = makeDeck();
        ArrayList <Card> player_hand = new ArrayList<>();
        ArrayList <Card> opponent_hand = new ArrayList<>();
        //deal 2 cards to player and 1 card to dealer
        player_hand.add(deck.get(deck.size() -1));
        deck.remove(deck.size() -1);
        player_hand.add(deck.get(deck.size() -1));
        deck.remove(deck.size() -1);
        opponent_hand.add(deck.get(deck.size() -1));
        deck.remove(deck.size() -1);
        play_round(player_hand, opponent_hand , deck);
    }

    private static void play_round(ArrayList player_hand, ArrayList opponent_hand, ArrayList deck) {
        ListIterator <Card> player_iterator = player_hand.listIterator();
        ListIterator <Card> opponent_iterator = opponent_hand.listIterator();
        System.out.println("You have:");
        while (player_iterator.hasNext()) {
            Card current_card = player_iterator.next();
            System.out.println(current_card.getType() + " of " + current_card.getSuit());
        }
        System.out.println("Opponent has:");
        while (opponent_iterator.hasNext()) {
            Card current_card = opponent_iterator.next();
            System.out.println(current_card.getType() + " of " + current_card.getSuit());
        }
        playerMove(player_hand, opponent_hand, deck);


    }

    public static void playerMove(ArrayList player_hand, ArrayList opponent_hand, ArrayList deck) {
        ArrayList <Integer> scores = checkScore(player_hand, opponent_hand);
        if (scores.get(0) > 21) {
            loseRound(scores.get(0), scores.get(1));
        } else if (scores.get(1) > 21) {
            winRound(scores.get(0), scores.get(1));
        }
        else {
            System.out.println("Would you like to Stand (S) or Hit (H)?");
            Scanner scanner = new Scanner(System.in);
            boolean playerHit = false;
            while (true) {
                String input = scanner.next();
                if (input.equalsIgnoreCase("H")) {
                    playerHit = true;
                    break;
                } else if (input.equalsIgnoreCase("S")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }
            if (playerHit) {
                player_hand.add(deck.get(deck.size() - 1));
                deck.remove(deck.size() - 1);
                play_round(player_hand, opponent_hand, deck);
            } else {
                end_game(player_hand, opponent_hand, deck);
            }
        }
    }

    private static void end_game(ArrayList player_hand, ArrayList opponent_hand, ArrayList deck){
        opponent_hand.add(deck.get(deck.size() -1));
        deck.remove(deck.size() -1);
        Integer last_index = opponent_hand.size() - 1;
        Card last_card = (Card) opponent_hand.get(last_index);

        // Call the method on the last object
        String last_type = last_card.getType();
        String last_suit = last_card.getSuit();
        if (opponent_hand.size() == 2){
            System.out.println("The Opponent flipped up their face down card and got a " + last_suit + " of " + last_type + ".");
        }
        else{
            System.out.println("The Opponent drew a card and got a " + last_suit + " of " + last_type + ".");
        }
        ArrayList <Integer> scores = checkScore(player_hand, opponent_hand);
        if (scores.get(1) > 21) {
            System.out.println("Looks like the dealer busted with " + scores.get(1) + ".");
            winRound(scores.get(0), scores.get(1));
        }
        else if (scores.get(1) == 21) {
            System.out.println("Sorry, looks like the dealer got Blackjack.");
            loseRound(scores.get(0), scores.get(1));;
        }
        else if (scores.get(1) < 17){
            end_game(player_hand, opponent_hand, deck);
        }
        else {
            if (scores.get(0) > scores.get(1)) {
                winRound(scores.get(0), scores.get(1));
            }
            else {
                loseRound(scores.get(0), scores.get(1));
            }
        }
    }

    public static ArrayList checkScore(ArrayList player_hand, ArrayList opponent_hand) {
        ListIterator <Card> player_iterator = player_hand.listIterator();
        ListIterator <Card> opponent_iterator = opponent_hand.listIterator();
        Integer player_sum = 0;
        Integer player_aces = 0;
        Integer opponent_sum = 0;
        Integer opponent_aces = 0;
        while (player_iterator.hasNext()) {
            Card current_card = player_iterator.next();
            player_sum += current_card.getValue();
            if (current_card.type == "Ace") {
                player_aces += 1;
            }
        }

        while (opponent_iterator.hasNext()) {
            Card current_card = opponent_iterator.next();
            opponent_sum += current_card.getValue();
            if (current_card.type == "Ace") {
                opponent_aces += 1;
            };
        }

        if (player_sum < 21) {
            if (player_aces > 0) {
                player_sum -= 10;
                player_aces -= 1;
            }
        }

        if (opponent_sum < 21) {
            if (opponent_aces > 0) {
                opponent_sum -= 10;
                opponent_aces -= 1;
            }
        }
        ArrayList <Integer> sums = new ArrayList<>();
        sums.add(player_sum);
        sums.add(opponent_sum);
        return sums;


    }


    public static void loseRound(Integer player_sum, Integer opponent_sum) {
        System.out.println("Sorry the Dealer had" + opponent_sum + ". While you had " + player_sum);
        System.out.println("You Lost");
        playAgain();
    }

    public static void winRound(Integer player_sum, Integer opponent_sum) {
        System.out.println("Congratulations! The Dealer had" + opponent_sum + ". While you had " + player_sum);
        if (player_sum == 21) {
            System.out.println("Blackjack!");
            chips += buy_in * 2 + 50;
            System.out.println("You won " + ((buy_in * 2) +50) + " chips!");
        }
        else {
            chips += buy_in * 2;
            System.out.println("You won " + buy_in * 2 + " chips!");
        }
        playAgain();
    }

    public static void playAgain() {
        System.out.println("");
        System.out.println("Your current Chip total is " + chips + " chips!");
        System.out.println("Would you like to play again? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        boolean playerContinue = false;
        while (true) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("Y")) {
                playerContinue = true;
                break;
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println("Thanks for Playing!");
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
        if (playerContinue) {
            start_round();
        }
        else {
            System.exit(0);
        }
    }

    // Instructions to the User of how to play the game
    public static void rules(){
        System.out.println("Welcome to a game of Blackjack!");
        System.out.println("The moves you can make are:");
        System.out.println("h = hit. Take another card from the Dealer");
        System.out.println("If you get 21 and the House does not, you win!  Careful! If your hand goes over 21 you lose!");
        System.out.println("s = stand. Stop taking cards");
        System.out.println("You are betting that your hand will be closer to 21 before the House's hand busts, (goes over 21) or stands");
        System.out.println("You start with 1,000 Chips. Playing a round costs 100 Chips, winning a round gains you 200 chips");
        
    }

    private static Card makeCard(String new_suit, String new_type){
        Card new_card = new Card();
        new_card.setSuit(new_suit);
        new_card.setType(new_type);

        return new_card;
    }



    private static ArrayList makeDeck(){
        ArrayList <Card> new_deck = new ArrayList<>();
        ArrayList <String> suits = new ArrayList<>();
        suits.add("clubs");
        suits.add("spades");
        suits.add("hearts");
        suits.add("diamonds");
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
        while (suits_iterator.hasNext()) {
            String current_suit = suits_iterator.next();
            types_iterator = types.listIterator();
            while (types_iterator.hasNext()){
                String current_type = types_iterator.next();
                new_deck.add(makeCard(current_suit, current_type));
            }
        }
        Collections.shuffle(new_deck);
        return new_deck;

    }
}

