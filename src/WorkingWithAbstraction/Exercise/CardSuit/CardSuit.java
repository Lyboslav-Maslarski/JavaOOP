package WorkingWithAbstraction.Exercise.CardSuit;

public enum CardSuit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    public String getInfo() {
        return String.format("Ordinal value: %d; Name value: %s", ordinal(), name());
    }
}
