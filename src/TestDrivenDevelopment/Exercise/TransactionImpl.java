package TestDrivenDevelopment.Exercise;

public class TransactionImpl implements Comparable<TransactionImpl>, Transaction {

    private final int id;
    private TransactionStatus status;
    private final String from;
    private final String to;
    private final double amount;

    public TransactionImpl(int id, TransactionStatus status, String from, String to, double amount) {
        this.id = id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public int compareTo(TransactionImpl o) {
        return this.id - o.id;
    }
}
