package ExamPreparation.Exam16August2020.onlineShop.models.products.computers;

import ExamPreparation.Exam16August2020.onlineShop.models.products.components.Component;
import ExamPreparation.Exam16August2020.onlineShop.models.products.BaseProduct;
import ExamPreparation.Exam16August2020.onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ExamPreparation.Exam16August2020.onlineShop.common.constants.ExceptionMessages.*;
import static ExamPreparation.Exam16August2020.onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        }
        return super.getOverallPerformance() + components.stream().mapToDouble(Component::getOverallPerformance).average().orElse(0);
    }

    @Override
    public double getPrice() {
        return super.getPrice() +
               components.stream().mapToDouble(Component::getPrice).sum() +
               peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public void addComponent(Component component) {
        String type = component.getClass().getSimpleName();
        boolean anyMatch = components.stream().anyMatch(component1 -> component1.getClass().getSimpleName().equals(type));
        if (anyMatch) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, type, getClass().getSimpleName(), getId()));
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Optional<Component> component = components.stream().filter(c -> c.getClass().getSimpleName().equals(componentType)).findAny();
        if (component.isEmpty()) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType, getClass().getSimpleName(), getId()));
        }
        components.remove(component.get());
        return component.get();
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        String type = peripheral.getClass().getSimpleName();
        boolean anyMatch = peripherals.stream().anyMatch(p -> p.getClass().getSimpleName().equals(type));
        if (anyMatch) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, type, getClass().getSimpleName(), getId()));
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Optional<Peripheral> peripheral = peripherals.stream().filter(p -> p.getClass().getSimpleName().equals(peripheralType)).findAny();
        if (peripheral.isEmpty()) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType, getClass().getSimpleName(), getId()));
        }
        peripherals.remove(peripheral.get());
        return peripheral.get();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format(PRODUCT_TO_STRING,
                getOverallPerformance(), getPrice(), getClass().getSimpleName(), getManufacturer(), getModel(), getId()));
        sb.append(System.lineSeparator())
                .append(String.format(COMPUTER_COMPONENTS_TO_STRING, components.size()));
        components.forEach(c -> sb.append(System.lineSeparator()).append("  ").append(c));
        sb.append(System.lineSeparator())
                .append(String.format(COMPUTER_PERIPHERALS_TO_STRING,
                        peripherals.size(), peripherals.stream().mapToDouble(Peripheral::getOverallPerformance).average().orElse(0)));
        peripherals.forEach(p -> sb.append(System.lineSeparator()).append("  ").append(p));
        return sb.toString();
    }
}
