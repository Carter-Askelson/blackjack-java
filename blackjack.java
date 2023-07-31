public class Blackjack {
    public static void main(String[] args) {
        // Your code goes here
        System.out.println("Run Program");
        
        // String testvar = "lame";
        
        rules();
    }

     // Instructions to the User of how to play the game
    public static void rules(){
        // System.out.println(variable);
        System.out.println("Welcome to a game of Blackjack!");
        System.out.println("The moves you can make are:");
        System.out.println("h = hit. Take another card from the Dealer");
        System.out.println("If you get 21 and the House does not, you win!  Careful! If your hand goes over 21 you lose!");
        System.out.println("s = stand. Stop taking cards");
        System.out.println("You are betting that your hand will be closer to 21 before the House's hand busts, (goes over 21) or stands");
        System.out.println("You start with 1,000 Chips. Playing a round costs 100 Chips, winning a round gains you 200 chips");
        
    }

    public static void makeCard(){
        System.out.println("");
    }

    public static void makeDeck(){
        System.out.println("");
    }
}
