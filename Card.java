public class Card {
    String suit;
    String type;
    Integer value;

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if (this.type == "Jack" || this.type == "Queen"|| this.type == "King") {
            this.value = 10;
        } else if (this.type == "Ace") {
            this.value = 11;
        }
        else {
            this.value = Integer.parseInt(this.type);
        }

    }

    public Integer getValue() {
        return value;
    }

    public void lowerAce() {
        this.value = 1;
    }
}
