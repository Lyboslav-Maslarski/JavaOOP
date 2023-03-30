package ExamPreparation.Exam18April2021.heroRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class HeroRepositoryTests {
    private List<Hero> heroes;
    private HeroRepository heroRepository;

    @Before
    public void setUp() {
        heroes = new ArrayList<>();
        heroes.add(new Hero("name1", 1));
        heroes.add(new Hero("name2", 2));
        heroes.add(new Hero("name3", 2));
        heroes.add(new Hero("name4", 3));
        heroes.add(new Hero("name5", 3));
        heroRepository = new HeroRepository();
    }

    @Test
    public void test_createHero() {
        heroRepository.create(heroes.get(0));
        assertEquals(1, heroRepository.getCount());
        heroRepository.create(heroes.get(1));
        assertEquals(2, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_createHeroWithNullShouldThrow() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createHeroWithExistingShouldThrow() {
        heroRepository.create(heroes.get(0));
        heroRepository.create(heroes.get(0));
    }

    @Test
    public void test_removeHero() {
        heroRepository.create(heroes.get(0));
        assertEquals(1, heroRepository.getCount());
        assertFalse(heroRepository.remove(heroes.get(1).getName()));
        assertTrue(heroRepository.remove(heroes.get(0).getName()));
        assertEquals(0, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_removeHeroShouldThrowWithNull() {
        heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_removeHeroShouldThrowWithEmptyString() {
        heroRepository.remove("  ");
    }

    @Test
    public void test_getHeroWithHighestLevel() {
        Hero expected = this.heroes.stream()
                .max(Comparator.comparingInt(Hero::getLevel))
                .orElse(null);

        assertNull(heroRepository.getHeroWithHighestLevel());

        heroRepository.create(heroes.get(0));
        heroRepository.create(heroes.get(1));
        heroRepository.create(heroes.get(2));
        heroRepository.create(heroes.get(3));
        heroRepository.create(heroes.get(4));

        Hero actual = heroRepository.getHeroWithHighestLevel();

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLevel(), actual.getLevel());
    }

    @Test
    public void test_getHero() {
        String name = heroes.get(0).getName();

        Hero expected = this.heroes
                .stream()
                .filter(h -> h.getName().equals(name))
                .findFirst()
                .orElse(null);

        assertNull(heroRepository.getHero(""));

        heroRepository.create(heroes.get(0));
        heroRepository.create(heroes.get(1));
        heroRepository.create(heroes.get(2));
        heroRepository.create(heroes.get(3));
        heroRepository.create(heroes.get(4));

        Hero actual = heroRepository.getHero(name);

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLevel(), actual.getLevel());
    }

    @Test
    public void test_getHeroes() {
        heroRepository.create(heroes.get(0));
        heroRepository.create(heroes.get(1));
        heroRepository.create(heroes.get(2));
        heroRepository.create(heroes.get(3));
        heroRepository.create(heroes.get(4));
        assertEquals(heroes.size(),heroRepository.getHeroes().size());
    }
}
