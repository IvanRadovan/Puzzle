package puzzle;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private static final char PLAYER_SYMBOL = '€';
    private static final char ENEMY_SYMBOL = '!';

    private static final char GOAL_SYMBOL = '@';

    private static void gameOver() {
        System.out.println("You lose!"); System.exit(0);
    }

    private static void gameCompleted() {
        System.out.println("You won!");
        System.exit(0);

    }


    public static void printMatrix(char[][] matrix) {
        for (char[] array : matrix) {
            for (char element : array) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public static void enemyMovement(char[][] matrix) {
        try {
            int x = 0, y = 0;
            Random random = new Random();
            int randomNumber = random.nextInt(4) + 1;

            switch (randomNumber) {
                case 1 -> x = -1;
                case 2 -> y = -1;
                case 3 -> y = 1;
                case 4 -> x = 1;
            }

            for (int row = 0; row < matrix.length; row++) {
                for (int column = 0; column < matrix[row].length; column++) {
                    if (matrix[row][column] == ENEMY_SYMBOL) {
                        if (matrix[row + (x)][column + (y)] == GOAL_SYMBOL) {
                            continue;
                        }
                        if (matrix[row + (x)][column + (y)] ==  PLAYER_SYMBOL) {
                            gameOver();
                        }
                        char temp = matrix[row][column];
                        matrix[row][column] = matrix[row + (x)][column + (y)];
                        matrix[row + (x)][column + (y)] = temp;
                        return;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }

    }

    public static void playerMovement(char[][] matrix, int x, int y) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column] == PLAYER_SYMBOL) {
                    if (matrix[row + (x)][column + (y)] == ENEMY_SYMBOL) {
                        gameOver();
                    }
                    if (matrix[row + (x)][column + (y)] == GOAL_SYMBOL) {
                        gameCompleted();
                    } else {
                        char temp = matrix[row][column];
                        matrix[row][column] = matrix[row + (x)][column + (y)];
                        matrix[row + (x)][column + (y)] = temp;
                        enemyMovement(matrix);
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        char[][] innerMatrix = new char[][]
                {
                        {'€', '-', '-', '-',},
                        {'-', '-', '-', '-',},
                        {'-', '-', '-', '-',},
                        {'-', '-', '-', '!',},
                };

        printMatrix(innerMatrix);

        while (true) {

            String input = scanner.nextLine().toUpperCase();
            try {
                switch (input) {
                    case "W" -> playerMovement(innerMatrix, -1, 0);
                    case "A" -> playerMovement(innerMatrix, 0, -1);
                    case "D" -> playerMovement(innerMatrix, 0, 1);
                    case "S" -> playerMovement(innerMatrix, 1, 0);
                    case "0" -> System.exit(0);
                    default -> {
                        System.out.println("""
                                Use only:
                                W - Up
                                A - Left
                                D - Right
                                S - Down
                                0 - Exit""");
                        continue;
                    }
                }
                printMatrix(innerMatrix);
            } catch (IndexOutOfBoundsException e) {
                enemyMovement(innerMatrix);
                printMatrix(innerMatrix);
            }
        }
    }
}

