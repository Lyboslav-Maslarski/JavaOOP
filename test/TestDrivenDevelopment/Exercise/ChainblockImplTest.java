package TestDrivenDevelopment.Exercise;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.ABORTED, "Pesho", "Kircho", 59.5);
        Transaction transaction4 = new TransactionImpl(4, TransactionStatus.UNAUTHORIZED, "Pesho", "Vasko", 32.5);
        Transaction transaction5 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Pesho", "Vanko", 42.4);
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

}