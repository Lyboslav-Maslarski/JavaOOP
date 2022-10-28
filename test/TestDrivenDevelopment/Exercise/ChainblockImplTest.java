package TestDrivenDevelopment.Exercise;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    private ChainblockImpl chainblock;
    private List<Transaction> transactions;

    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Gosho", 10.1);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.FAILED, "Pesho", "Tosho", 20.2);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.FAILED, "Pesho", "Kircho", 59.5);
        Transaction transaction4 = new TransactionImpl(4, TransactionStatus.UNAUTHORIZED, "Pesho", "Vasko", 32.5);
        Transaction transaction5 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Tosho", "Vasko", 42.4);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
    }

    @Test
    public void test_AddCommand() {
        chainblock.add(transactions.get(0));
        assertEquals(1, chainblock.getCount());
        chainblock.add(transactions.get(1));
        assertEquals(2, chainblock.getCount());
        assertTrue(chainblock.contains(transactions.get(0)));
        assertTrue(chainblock.contains(transactions.get(1)));
    }

    @Test
    public void test_AddCommand_ShouldAddOnlyUniqueByIdTransactions() {
        chainblock.add(transactions.get(0));
        assertEquals(1, chainblock.getCount());
        chainblock.add(transactions.get(0));
        assertEquals(1, chainblock.getCount());
        assertTrue(chainblock.contains(transactions.get(0)));
    }

    @Test
    public void test_ContainsWithTransaction_ShouldReturnTrue() {
        chainblock.add(transactions.get(0));
        assertTrue(chainblock.contains(transactions.get(0)));
    }

    @Test
    public void test_ContainsWithTransaction_ShouldReturnFalse() {
        chainblock.add(transactions.get(0));
        assertFalse(chainblock.contains(transactions.get(1)));
    }

    @Test
    public void test_ContainsWithID_ShouldReturnTrue() {
        chainblock.add(transactions.get(0));
        assertTrue(chainblock.contains(transactions.get(0).getId()));
    }

    @Test
    public void test_ContainsWithID_ShouldReturnFalse() {
        chainblock.add(transactions.get(0));
        assertFalse(chainblock.contains(transactions.get(1).getId()));
    }

    @Test
    public void test_ChangeTransactionStatus_ShouldChangeTransactionStatus() {
        chainblock.add(transactions.get(0));
        int id = transactions.get(0).getId();
        TransactionStatus newStatus = TransactionStatus.ABORTED;
        chainblock.changeTransactionStatus(id, newStatus);
        assertEquals(newStatus, chainblock.getById(id).getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ChangeTransactionStatus_ShouldThrowIfNoSuchTransactionPresent() {
        chainblock.add(transactions.get(0));
        int id = transactions.get(1).getId();
        TransactionStatus newStatus = TransactionStatus.ABORTED;
        chainblock.changeTransactionStatus(id, newStatus);
        assertEquals(newStatus, chainblock.getById(id).getStatus());
    }

    @Test
    public void test_RemoveTransactionById_ShouldRemoveTransaction() {
        fillChainblock();
        assertEquals(5, chainblock.getCount());
        chainblock.removeTransactionById(1);
        assertEquals(4, chainblock.getCount());
        assertFalse(chainblock.contains(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveTransactionById_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.removeTransactionById(10);
    }

    @Test
    public void test_GetById_ShouldReturnTransaction() {
        fillChainblock();
        assertEquals(transactions.get(0), chainblock.getById(transactions.get(0).getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetById_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.getById(10);
    }

    @Test
    public void test_GetByTransactionStatus_ShouldReturnTransactions() {
        fillChainblock();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetByTransactionStatus_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.getByTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void test_GetAllSendersWithTransactionStatus_ShouldReturnSenders() {
        fillChainblock();
        List<String> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
        Iterable<String> actual = chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetAllSendersWithTransactionStatus_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.getAllSendersWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void test_GetAllReceiversWithTransactionStatus_ShouldReturnReceivers() {
        fillChainblock();
        List<String> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getTo)
                .collect(Collectors.toList());
        Iterable<String> actual = chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetAllReceiversWithTransactionStatus_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void test_AllOrderedByAmountDescendingThenById_ShouldReturnTransactions() {
        fillChainblock();
        List<Transaction> expected = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getAllOrderedByAmountDescendingThenById();
        assertEquals(expected, actual);
    }

    @Test
    public void test_GetBySenderOrderedByAmountDescending_ShouldReturnTransactions() {
        fillChainblock();
        String sender = transactions.get(1).getFrom();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getFrom().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getBySenderOrderedByAmountDescending(sender);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetBySenderOrderedByAmountDescending_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.getBySenderOrderedByAmountDescending("Viktor");
    }

    @Test
    public void test_GetByReceiverOrderedByAmountThenById_ShouldReturnTransactions() {
        fillChainblock();
        String receiver = transactions.get(4).getTo();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getTo().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getByReceiverOrderedByAmountThenById(receiver);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetByReceiverOrderedByAmountThenById_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.getByReceiverOrderedByAmountThenById("Viktor");
    }

    @Test
    public void test_GetByTransactionStatusAndMaximumAmount_ShouldReturnTransactions() {
        fillChainblock();
        double amount = transactions.get(0).getAmount();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .filter(t -> t.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, amount);
        assertEquals(expected, actual);
    }

    @Test
    public void test_GetBySenderAndMinimumAmountDescending_ShouldReturnTransactions() {
        fillChainblock();
        double amount = transactions.get(1).getAmount();
        String sender = transactions.get(1).getTo();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getFrom().equals(sender))
                .filter(t -> t.getAmount() > amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getBySenderAndMinimumAmountDescending(sender, amount);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetBySenderAndMinimumAmountDescending_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.getBySenderAndMinimumAmountDescending("Viktor", 10);
    }

    @Test
    public void test_GetByReceiverAndAmountRange_ShouldReturnTransactions() {
        fillChainblock();
        double low = transactions.get(0).getAmount();
        double high = transactions.get(4).getAmount();
        String receiver = transactions.get(4).getTo();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getTo().equals(receiver))
                .filter(t -> t.getAmount() >= low && t.getAmount() < high)
                .sorted(Comparator.comparing(Transaction::getId).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getByReceiverAndAmountRange(receiver, low, high);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetByReceiverAndAmountRange_ShouldThrowIfNoSuchTransactionPresent() {
        fillChainblock();
        chainblock.getByReceiverAndAmountRange("Viktor", 10, 20);
    }

    @Test
    public void test_GetAllInAmountRange_ShouldReturnTransactions() {
        double low = transactions.get(0).getAmount();
        double high = transactions.get(4).getAmount();
        fillChainblock();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getAmount() >= low && t.getAmount() <= high)
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getAllInAmountRange(low, high);
        assertEquals(expected, actual);
    }

    @Test
    public void test_GetAllInAmountRange_ShouldReturnEmptyCollectionIfNoSuchTransactions() {
        fillChainblock();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getAmount() >= 123 && t.getAmount() <= 456)
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getAllInAmountRange(123, 456);
        assertEquals(expected, actual);
    }

    private void fillChainblock() {
        transactions.forEach(transaction -> chainblock.add(transaction));
    }
}