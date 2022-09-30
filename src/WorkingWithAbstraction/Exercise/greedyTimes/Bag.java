package WorkingWithAbstraction.Exercise.greedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private Map<String, Map<String, Long>> loot;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.loot = new LinkedHashMap<>();
    }

    public void lootMaterial(String material, long amount) {
        String currentLootName = "";

        if (material.length() == 3) {
            currentLootName = "Cash";
        } else if (material.toLowerCase().endsWith("gem")) {
            currentLootName = "Gem";
        } else if (material.equalsIgnoreCase("gold")) {
            currentLootName = "Gold";
        }

        if (currentLootName.equals("")) {
            return;
        } else if (bagIsFull(amount)) {
            return;
        }

        switch (currentLootName) {
            case "Gem":
                if (!this.loot.containsKey(currentLootName)) {
                    if (this.loot.containsKey("Gold")) {
                        if (amount > getSum("Gold")) {
                            return;
                        }
                    } else {
                        return;
                    }
                } else if (getSum(currentLootName) + amount > getSum("Gold")) {
                    return;
                }
                break;
            case "Cash":
                if (!this.loot.containsKey(currentLootName)) {
                    if (this.loot.containsKey("Gem")) {
                        if (amount > getSum("Gold")) {
                            return;
                        }
                    } else {
                        return;
                    }
                } else if (getSum(currentLootName) + amount > getSum("Gem")) {
                    return;
                }
                break;
        }

        this.loot.putIfAbsent((currentLootName), new LinkedHashMap<>());

        this.loot.get(currentLootName).putIfAbsent(material, 0L);

        this.loot.get(currentLootName).put(material, this.loot.get(currentLootName).get(material) + amount);

    }

    private long getSum(String currentMaterial) {
        return this.loot.get(currentMaterial).values().stream().mapToLong(e -> e).sum();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var material : loot.entrySet()) {
            Long sumValues = material.getValue().values().stream().mapToLong(l -> l).sum();

            stringBuilder.append(String.format("<%s> $%s%n", material.getKey(), sumValues));

            material.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                    .forEach(entry -> stringBuilder.append(String.format("##%s - %d%n", entry.getKey(), entry.getValue())));

        }
        return stringBuilder.toString();
    }

    private boolean bagIsFull(long amount) {
        return this.capacity < this.loot.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + amount;
    }
}
