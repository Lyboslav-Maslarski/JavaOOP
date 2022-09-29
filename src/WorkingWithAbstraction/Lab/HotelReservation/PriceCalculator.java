package WorkingWithAbstraction.Lab.HotelReservation;

public class PriceCalculator {
    private final double pricePerDay;
    private final int days;
    private final Season season;
    private final DiscountType discountType;

    public PriceCalculator(double pricePerDay, int days, Season season, DiscountType discountType) {
        this.pricePerDay = pricePerDay;
        this.days = days;
        this.season = season;
        this.discountType = discountType;
    }

    public double calculatePrice() {
        return pricePerDay*days*season.getMultiplier()*discountType.getDiscount();
    }
}
