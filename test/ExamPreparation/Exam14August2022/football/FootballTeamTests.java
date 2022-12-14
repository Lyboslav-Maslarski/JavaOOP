package ExamPreparation.Exam14August2022.football;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FootballTeamTests {
    private FootballTeam footballTeam;
    private List<Footballer> footballers;

    @Before
    public void setUp() {
        footballTeam = new FootballTeam("Peshovci", 3);
        Footballer footballer1 = new Footballer("Pesho1");
        Footballer footballer2 = new Footballer("Pesho2");
        Footballer footballer3 = new Footballer("Pesho3");
        Footballer footballer4 = new Footballer("Pesho4");
        Footballer footballer5 = new Footballer("Pesho5");
        footballers = new ArrayList<>();
        footballers.add(footballer1);
        footballers.add(footballer2);
        footballers.add(footballer3);
        footballers.add(footballer4);
        footballers.add(footballer5);
    }

    @Test
    public void test_Constructor() {
        String name = "Batki";
        int vacantPositions = 10;
        FootballTeam footballTeam = new FootballTeam(name, vacantPositions);
        assertEquals(name, footballTeam.getName());
        assertEquals(vacantPositions, footballTeam.getVacantPositions());
    }

    @Test(expected = NullPointerException.class)
    public void test_ConstructorWithNullName() {
        new FootballTeam(null, 0);
    }

    @Test(expected = NullPointerException.class)
    public void test_ConstructorWithEmptyName() {
        new FootballTeam("  ", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ConstructorWithNegativeVacantPositions() {
        new FootballTeam("Batki", -1);
    }

    @Test
    public void test_addFootballer() {
        int count = 3;
        footballers.stream().limit(count).forEach(footballer -> footballTeam.addFootballer(footballer));
        assertEquals(count, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addFootballerWithMoreThanVacantPositionsFootballers() {
        footballers.forEach(footballer -> footballTeam.addFootballer(footballer));
    }

    @Test
    public void test_removeFootballer() {
        int count = 3;
        footballers.stream().limit(count).forEach(footballer -> footballTeam.addFootballer(footballer));
        assertEquals(count, footballTeam.getCount());
        footballTeam.removeFootballer(footballers.get(0).getName());
        assertEquals(count - 1, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeNoneExistingFootballer() {
        int count = 3;
        footballers.stream().limit(count).forEach(footballer -> footballTeam.addFootballer(footballer));
        assertEquals(count, footballTeam.getCount());
        footballTeam.removeFootballer(footballers.get(3).getName());
        assertEquals(count, footballTeam.getCount());

    }

    @Test
    public void test_footballerForSale() {
        int count = 3;
        footballers.stream().limit(count).forEach(footballer -> footballTeam.addFootballer(footballer));
        assertEquals(count, footballTeam.getCount());
        String name = footballers.get(0).getName();
        Footballer footballer = footballTeam.footballerForSale(name);
        assertFalse(footballer.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_footballerForSaleNoneExistingFootballer() {
        int count = 3;
        footballers.stream().limit(count).forEach(footballer -> footballTeam.addFootballer(footballer));
        assertEquals(count, footballTeam.getCount());
        footballTeam.footballerForSale(footballers.get(3).getName());
        assertEquals(count, footballTeam.getCount());

    }
    @Test
    public void test_getStatistics() {
        int count = 3;
        footballers.stream().limit(count).forEach(footballer -> footballTeam.addFootballer(footballer));
        String names = this.footballers.stream().limit(count).map(Footballer::getName).collect(Collectors.joining(", "));
        String expected = String.format("The footballer %s is in the team %s.", names, footballTeam.getName());
        assertEquals(expected,footballTeam.getStatistics());
    }
}
