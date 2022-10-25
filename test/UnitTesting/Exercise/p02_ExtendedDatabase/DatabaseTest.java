package UnitTesting.Exercise.p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {
    private Database database;
    private static final Person[] people = new Person[]{new Person(1, "Pesho"), new Person(2, "Gosho"), new Person(3, "Tosho")};

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.database = new Database(people);
    }

    @Test
    public void test_Constructor() throws OperationNotSupportedException {
        setUp();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorWithZeroElements() throws OperationNotSupportedException {
        new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorMoreThan16Elements() throws OperationNotSupportedException {
        new Database(new Person[17]);
    }

    @Test
    public void test_AddOperation() throws OperationNotSupportedException {
        int initialLength = database.getElements().length;

        database.add(new Person(4, "Misho"));

        assertEquals(initialLength + 1, database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_AddOperationWithNull() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_RemoveOperation() throws OperationNotSupportedException {
        int initialLength = database.getElements().length;

        database.remove();

        assertEquals(initialLength - 1, database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_RemoveOperationOnEmptyDB() throws OperationNotSupportedException {
        int lengthDB = database.getElements().length;

        for (int i = 0; i < lengthDB; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void test_FindByUsernameOperation() throws OperationNotSupportedException {
        String username = database.getElements()[0].getUsername();

        Person person = database.findByUsername(username);

        assertEquals(username, person.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsernameCaseSensitive() throws OperationNotSupportedException {
        database.findByUsername("pesho");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsernameOperationWithNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_CannotHaveTwoPeopleWithSameUsername() throws OperationNotSupportedException {
        Person person = database.getElements()[0];
        database.add(person);

        database.findByUsername(person.getUsername());
    }

    @Test
    public void test_FindByIDOperation() throws OperationNotSupportedException {
        int id = database.getElements()[0].getId();

        Person person = database.findById(id);

        assertEquals(id, person.getId());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByIDShouldThrowExceptionIfNotFoundOrNegativeID() throws OperationNotSupportedException {
        database.findById(5);
        database.findById(-1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_CannotHaveTwoPeopleWithSameID() throws OperationNotSupportedException {
        Person person = database.getElements()[0];
        database.add(person);

        database.findById(person.getId());
    }
}