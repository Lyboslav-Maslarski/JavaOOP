package TestDrivenDevelopment.Exercise;

import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private final Map<Integer, Transaction> transactions;

    ChainblockImpl() {
        this.transactions = new LinkedHashMap<>();
    }

    public int getCount() {
        return transactions.size();
    }

    public void add(Transaction transaction) {
        transactions.putIfAbsent(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return transactions.containsKey(transaction.getId());
    }

    public boolean contains(int id) {
        return transactions.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!transactions.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        transactions.get(id).setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        if (!transactions.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        transactions.remove(id);
    }

    public Transaction getById(int id) {
        if (!transactions.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return transactions.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> list = transactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> list = transactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> list = transactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getTo)
                .collect(Collectors.toList());
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return transactions.values().stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> list = transactions.values().stream()
                .filter(t -> t.getFrom().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> list = transactions.values().stream()
                .filter(t -> t.getTo().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return transactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .filter(t -> t.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> list = transactions.values().stream()
                .filter(t -> t.getFrom().equals(sender))
                .filter(t -> t.getAmount() > amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double low, double high) {
        List<Transaction> list = transactions.values().stream()
                .filter(t -> t.getTo().equals(receiver))
                .filter(t -> t.getAmount() >= low && t.getAmount() < high)
                .collect(Collectors.toList());
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }
        return list;

    }

    public Iterable<Transaction> getAllInAmountRange(double low, double high) {
        return transactions.values().stream()
                .filter(t -> t.getAmount() >= low && t.getAmount() <= high)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < transactions.size();
            }

            @Override
            public Transaction next() {
                while (!transactions.containsKey(index)) {
                    index++;
                }
                return transactions.get(index);
            }
        };
    }
}
