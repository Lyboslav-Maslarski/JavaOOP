package ExamPreparation.Exam16August2020.computers;

import ExamPreparation.Exam16August2020.computers.Computer;
import ExamPreparation.Exam16August2020.computers.ComputerManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ComputerManagerTests {
    private ComputerManager computerManager;
    private List<Computer> computers;

    @Before
    public void setUp() {
        computerManager = new ComputerManager();
        computers = new ArrayList<>();
        computers.add(new Computer("manufacturer1", "model1", 10));
        computers.add(new Computer("manufacturer1", "model2", 20));
        computers.add(new Computer("manufacturer2", "model3", 30));
        computers.add(new Computer("manufacturer2", "model4", 40));
        computers.add(new Computer("manufacturer3", "model5", 50));
    }

    @Test
    public void test_Constructor() {
        assertEquals(0, computerManager.getCount());
    }

    @Test
    public void test_AddComputer() {
        assertEquals(0, computerManager.getCount());
        computerManager.addComputer(computers.get(0));
        assertEquals(1, computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddComputerShouldThrowWithNull() {
        computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddComputerShouldThrowWithExistingComputer() {
        computerManager.addComputer(computers.get(0));
        computerManager.addComputer(computers.get(0));
    }

    @Test
    public void test_GetComputer() {
        assertEquals(0, computerManager.getCount());

        Computer expected = computers.get(0);
        computerManager.addComputer(expected);
        assertEquals(1, computerManager.getCount());

        Computer actual = computerManager.getComputer(expected.getManufacturer(), expected.getModel());

        assertEquals(expected.getManufacturer(), actual.getManufacturer());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getPrice(), actual.getPrice(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetComputerShouldThrowWithNullManufacturer() {
        Computer expected = computers.get(0);
        computerManager.addComputer(expected);
        computerManager.getComputer(null, expected.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetComputerShouldThrowWithNullModel() {
        Computer expected = computers.get(0);
        computerManager.addComputer(expected);
        computerManager.getComputer(expected.getManufacturer(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetComputerShouldThrowWithNonExistingComputer() {
        Computer expected = computers.get(0);
        computerManager.getComputer(expected.getManufacturer(), expected.getModel());
    }

    @Test
    public void test_RemoveComputer() {
        assertEquals(0, computerManager.getCount());

        Computer expected = computers.get(0);
        computerManager.addComputer(expected);
        assertEquals(1, computerManager.getCount());

        computerManager.removeComputer(expected.getManufacturer(), expected.getModel());
        assertEquals(0, computerManager.getCount());
    }

    @Test
    public void test_GetComputers() {
        List<Computer> expected = new ArrayList<>();
        expected.add(computers.get(0));
        expected.add(computers.get(1));
        expected.add(computers.get(2));
        computerManager.addComputer(computers.get(0));
        computerManager.addComputer(computers.get(1));
        computerManager.addComputer(computers.get(2));
        assertEquals(expected.size(), computerManager.getComputers().size());
        for (Computer computer : expected) {
            Computer actual = computerManager.getComputer(computer.getManufacturer(), computer.getModel());
            assertEquals(computer.getPrice(), actual.getPrice(), 0.01);
        }
    }

    @Test
    public void test_GetComputersByManufacturer() {
        List<Computer> list = new ArrayList<>();
        list.add(computers.get(0));
        list.add(computers.get(1));
        list.add(computers.get(2));
        computerManager.addComputer(computers.get(0));
        computerManager.addComputer(computers.get(1));
        computerManager.addComputer(computers.get(2));
        String manufacturer = "manufacturer1";
        List<Computer> expectedList = list.stream().filter(c -> c.getManufacturer().equals(manufacturer)).collect(Collectors.toList());
        List<Computer> actualList = computerManager.getComputersByManufacturer(manufacturer);
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            Computer expected = expectedList.get(i);
            Computer actual = actualList.get(i);
            assertEquals(expected.getPrice(), actual.getPrice(), 0.01);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetComputersByManufacturerWithNullShouldThrow() {
        computerManager.getComputersByManufacturer(null);
    }
}