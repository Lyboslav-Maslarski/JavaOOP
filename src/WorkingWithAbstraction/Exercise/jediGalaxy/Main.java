package WorkingWithAbstraction.Exercise.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = getPosition(scanner.nextLine());
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] galaxy = new int[rows][cols];

        fillInGalaxy(rows, cols, galaxy);

        long starsCollected = 0;
        String command = scanner.nextLine();
        while (!command.equals("Let the Force be with you")) {
            int[] jediPosition = getPosition(command);
            int[] evilPosition = getPosition(scanner.nextLine());
            int evilRow = evilPosition[0];
            int evilCol = evilPosition[1];

            while (evilRow >= 0 && evilCol >= 0) {
                if (isInBounds(galaxy, evilRow, evilCol)) {
                    galaxy[evilRow][evilCol] = 0;
                }
                evilRow--;
                evilCol--;
            }

            int jediRow = jediPosition[0];
            int jediCol = jediPosition[1];

            while (jediRow >= 0 && jediCol < galaxy[1].length) {
                if (isInBounds(galaxy, jediRow, jediCol)) {
                    starsCollected += galaxy[jediRow][jediCol];
                }

                jediCol++;
                jediRow--;
            }

            command = scanner.nextLine();
        }

        System.out.println(starsCollected);


    }

    private static boolean isInBounds(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static int[] getPosition(String input) {
        return Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void fillInGalaxy(int rows, int cols, int[][] galaxy) {
        int stars = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                galaxy[row][col] = stars++;
            }
        }
    }
}
