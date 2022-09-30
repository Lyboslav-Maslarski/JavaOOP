package WorkingWithAbstraction.Exercise.CardRank;

public enum CardRank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;


    public String getInfo() {
        return String.format("Ordinal value: %d; Name value: %s", ordinal(), name());
    }
}
