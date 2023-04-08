package ExamPreparation.Exam08April2023.robotService.core;

import ExamPreparation.Exam08April2023.robotService.entities.robot.FemaleRobot;
import ExamPreparation.Exam08April2023.robotService.entities.robot.MaleRobot;
import ExamPreparation.Exam08April2023.robotService.entities.robot.Robot;
import ExamPreparation.Exam08April2023.robotService.entities.services.Service;
import ExamPreparation.Exam08April2023.robotService.entities.supplements.Supplement;
import ExamPreparation.Exam08April2023.robotService.entities.services.MainService;
import ExamPreparation.Exam08April2023.robotService.entities.services.SecondaryService;
import ExamPreparation.Exam08April2023.robotService.entities.supplements.MetalArmor;
import ExamPreparation.Exam08April2023.robotService.entities.supplements.PlasticArmor;
import ExamPreparation.Exam08April2023.robotService.repositories.SupplementRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ExamPreparation.Exam08April2023.robotService.common.ConstantMessages.*;
import static ExamPreparation.Exam08April2023.robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private SupplementRepository supplements;
    private Map<String, Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        switch (type) {
            case "MainService":
                service = new MainService(name);
                break;
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            default:
                throw new NullPointerException(INVALID_SERVICE_TYPE);
        }

        services.put(name, service);

        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        switch (type) {
            case "PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }

        supplements.addSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = supplements.findFirst(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        supplements.removeSupplement(supplement);
        Service service = services.get(serviceName);
        service.addSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        switch (robotType) {
            case "MaleRobot":
                robot = new MaleRobot(robotName, robotKind, price);
                break;
            case "FemaleRobot":
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }
        Service service = services.get(serviceName);
        boolean canAddRobot = false;
        if (service.getClass().getSimpleName().equals("MainService") && robotType.equals("MaleRobot")) {
            canAddRobot = true;
        } else if (service.getClass().getSimpleName().equals("SecondaryService") && robotType.equals("FemaleRobot")) {
            canAddRobot = true;
        }

        if (canAddRobot) {
            service.addRobot(robot);
            return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
        } else {
            return UNSUITABLE_SERVICE;
        }
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = services.get(serviceName);

        service.feeding();

        return String.format(FEEDING_ROBOT, service.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = services.get(serviceName);

        double value = service.getRobots().stream().mapToDouble(Robot::getPrice).sum() +
                       service.getSupplements().stream().mapToDouble(Supplement::getPrice).sum();

        return String.format(VALUE_SERVICE, serviceName, value);
    }

    @Override
    public String getStatistics() {
        return services.values().stream().map(Service::getStatistics).collect(Collectors.joining(System.lineSeparator()));
    }
}
