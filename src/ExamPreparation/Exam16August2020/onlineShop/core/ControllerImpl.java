package ExamPreparation.Exam16August2020.onlineShop.core;

import ExamPreparation.Exam16August2020.onlineShop.core.interfaces.Controller;
import ExamPreparation.Exam16August2020.onlineShop.models.products.components.*;
import ExamPreparation.Exam16August2020.onlineShop.models.products.computers.Computer;
import ExamPreparation.Exam16August2020.onlineShop.models.products.computers.DesktopComputer;
import ExamPreparation.Exam16August2020.onlineShop.models.products.computers.Laptop;
import ExamPreparation.Exam16August2020.onlineShop.models.products.peripherals.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ExamPreparation.Exam16August2020.onlineShop.common.constants.ExceptionMessages.*;
import static ExamPreparation.Exam16August2020.onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private final Map<Integer, Computer> computers;
    private final Map<Integer, Component> components;
    private final Map<Integer, Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new HashMap<>();
        this.components = new HashMap<>();
        this.peripherals = new HashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer = computers.get(id);
        if (computer != null) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        switch (computerType) {
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        computers.put(id, computer);

        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Computer computer = computers.get(computerId);
        if (computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Peripheral peripheral = peripherals.get(id);
        if (peripheral != null) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }

        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }

        computer.getPeripherals().add(peripheral);
        peripherals.put(id, peripheral);

        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = computers.get(computerId);
        if (computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Peripheral peripheral = computer.getPeripherals().stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheralType))
                .findAny().orElse(null);
        if (peripheral == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType, computer.getClass().getSimpleName(), computerId));
        }

        peripherals.remove(peripheral.getId());
        computer.getPeripherals().remove(peripheral);

        return String.format(REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Computer computer = computers.get(computerId);
        if (computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Component component = components.get(id);
        if (component != null) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }

        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        computer.getComponents().add(component);
        components.put(id, component);

        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = computers.get(computerId);
        if (computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Component component = computer.getComponents().stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findAny().orElse(null);
        if (component == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType, computer.getClass().getSimpleName(), computerId));
        }
        peripherals.remove(component.getId());
        computer.getComponents().remove(component);

        return String.format(REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = computers.remove(id);
        if (computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> computerList = computers.values().stream().filter(c -> c.getPrice() <= budget).collect(Collectors.toList());
        if (computerList.isEmpty()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }

        Computer computer = computerList.stream().max(Comparator.comparingDouble(Computer::getOverallPerformance)).get();
        computers.remove(computer.getId());

        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = computers.get(id);
        if (computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        return computer.toString();
    }
}
