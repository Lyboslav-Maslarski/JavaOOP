package ExamPreparation.Exam22August2022.archeologicalExcavations;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ExcavationTests {
    private Excavation excavation;
    private List<Archaeologist> archaeologists;

    @Before
    public void setUp() {
        excavation = new Excavation("TukTam", 3);
        Archaeologist archaeologist1 = new Archaeologist("Pesho1", 10);
        Archaeologist archaeologist2 = new Archaeologist("Pesho2", 100);
        Archaeologist archaeologist3 = new Archaeologist("Pesho3", 10);
        Archaeologist archaeologist4 = new Archaeologist("Pesho4", 100);
        Archaeologist archaeologist5 = new Archaeologist("Pesho5", 10);
        archaeologists = new ArrayList<>();
        archaeologists.add(archaeologist1);
        archaeologists.add(archaeologist2);
        archaeologists.add(archaeologist3);
        archaeologists.add(archaeologist4);
        archaeologists.add(archaeologist5);
    }

    @Test
    public void test_Constructor() {
        Excavation excavation = new Excavation("TukTam", 3);
        assertEquals("TukTam", excavation.getName());
        assertEquals(3, excavation.getCapacity());
        assertEquals(0, excavation.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_ConstructorWithWhiteSpaceNameShouldThrow() {
        Excavation excavation = new Excavation("      ", 3);
    }

    @Test(expected = NullPointerException.class)
    public void test_ConstructorWithNullNameShouldThrow() {
        Excavation excavation = new Excavation(null, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ConstructorWithNegativeCapacityShouldThrow() {
        Excavation excavation = new Excavation("TukTam", -3);
    }

    @Test
    public void test_AddShouldAddTheCorrectEntity() {
        int count = 3;
        archaeologists.stream().limit(count).forEach(archaeologist -> excavation.addArchaeologist(archaeologist));
        assertEquals("TukTam", excavation.getName());
        assertEquals(count, excavation.getCapacity());
        assertEquals(count, excavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddWithMoreThanCapacityShouldThrow() {
        int count = 3;
        archaeologists.stream().limit(count + 1).forEach(archaeologist -> excavation.addArchaeologist(archaeologist));

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddWithSameEntityShouldThrow() {
        int count = 3;
        archaeologists.stream().limit(count - 1).forEach(archaeologist -> excavation.addArchaeologist(archaeologist));
        excavation.addArchaeologist(archaeologists.get(0));
    }

    @Test
    public void test_RemoveArchaeologistShouldReturnTrueWhenFoundAndFalseOtherwise() {
        int count = 3;
        archaeologists.stream().limit(count).forEach(archaeologist -> excavation.addArchaeologist(archaeologist));
        assertEquals("TukTam", excavation.getName());
        assertEquals(count, excavation.getCapacity());
        assertEquals(count, excavation.getCount());
        assertTrue(excavation.removeArchaeologist(archaeologists.get(0).getName()));
        assertFalse(excavation.removeArchaeologist(archaeologists.get(3).getName()));
    }
}
