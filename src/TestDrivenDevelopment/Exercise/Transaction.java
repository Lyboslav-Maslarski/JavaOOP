package TestDrivenDevelopment.Exercise;

public interface Transaction {
    int getId();
    TransactionStatus getStatus();
    String getFrom();
    String getTo();
    double getAmount();
}
