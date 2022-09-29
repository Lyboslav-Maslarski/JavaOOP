package WorkingWithAbstraction.Lab.PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] coordinate = getCoordinate(scan);

        Point pointA = new Point(coordinate[0], coordinate[1]);
        Point pointC = new Point(coordinate[2], coordinate[3]);

        Rectangle rectangle = new Rectangle(pointA, pointC);

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0) {

            int[] coordinates = getCoordinate(scan);
            Point pointX = new Point(coordinates[0], coordinates[1]);
            System.out.println(rectangle.contains(pointX));
        }
    }

    private static int[] getCoordinate(Scanner scan) {
        return Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }
}
