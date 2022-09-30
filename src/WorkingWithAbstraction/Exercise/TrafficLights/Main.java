package WorkingWithAbstraction.Exercise.TrafficLights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> startingColors = Arrays.stream(scan.nextLine().split("\\s+")).collect(Collectors.toList());
        List<TrafficLight> trafficLights = new ArrayList<>();
        for (String color : startingColors) {
            Color color1 = Color.valueOf(color);
            TrafficLight trafficLight = new TrafficLight(color1);
            trafficLights.add(trafficLight);
        }
        int n =Integer.parseInt(scan.nextLine());
        for (int i = 0; i < n; i++) {
            for (TrafficLight trafficLight : trafficLights) {
                trafficLight.changeColor();
                System.out.print(trafficLight.getColor()+" ");
            }
            System.out.println();
        }
    }
}
