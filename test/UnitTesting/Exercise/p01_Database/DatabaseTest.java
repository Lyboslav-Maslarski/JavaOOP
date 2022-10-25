package UnitTesting.Exercise.p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {
    private Database database;
    private static final Integer[] NUMBERS = {13, 42, 73};

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(NUMBERS);
    }

    @Test
    public void test_CreateDatabase() throws OperationNotSupportedException {
        assertEquals(NUMBERS.length, database.getElements().length);

        for (int i = 0; i < database.getElements().length; i++) {
            assertEquals(NUMBERS[i], database.getElements()[i]);
        }
        assertArrayEquals(NUMBERS, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_CreateEmptyDatabase() throws OperationNotSupportedException {
        Database database = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_CreateDatabaseWithMoreThan16Elements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
        Database database = new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_AddNullToDatabase() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_AddElementToDatabase() throws OperationNotSupportedException {
        Integer toAdd = 2;
        int initialSize = database.getElements().length;

        database.add(toAdd);
        Integer lastElement = database.getElements()[database.getElements().length - 1];

        assertEquals(toAdd, lastElement);
        assertEquals(initialSize + 1, database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_RemoveFromEmptyDB() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void test_RemoveShouldReturnLastElement() throws OperationNotSupportedException {
        int initialSize = database.getElements().length;
        Integer preLastElement = database.getElements()[database.getElements().length - 2];

        database.remove();

        assertEquals(preLastElement, database.getElements()[database.getElements().length - 1]);
        assertEquals(initialSize - 1, database.getElements().length);

    }

    @Test
    public void test_FetchMethodReturnsElementsAsAnArray() {
        assertEquals(database.getElements().getClass(), Integer[].class);
    }
}