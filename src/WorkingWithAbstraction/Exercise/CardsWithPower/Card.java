package WorkingWithAbstraction.Exercise.CardsWithPower;

public class Card {
    private CardRank cardRank;
    private CardSuit cardSuit;

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public int calcPower() {
        return cardRank.getRankPower() + cardSuit.getSuitPower();
    }
}
