public class Card {

    private String suit;
    private String type;
    private Integer value;


    public Card(String new_suit,String new_type){
        setSuit(new_suit);
        setType(new_type);
    }


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
        if (this.type.equals("Jack") || this.type.equals("Queen") || this.type.equals("King")) {
            this.value = 10;
        } else if (this.type.equals("Ace")) {
            this.value = 11;
        }
        else {
            this.value = Integer.parseInt(this.type);
        }

    }

    public Integer getValue() {
        return value;
    }

    //public void lowerAce() {
    //    this.value = 1;
    //}
}
