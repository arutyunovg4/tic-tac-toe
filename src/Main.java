import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.zip.ZipFile;


public class Main {
    private static final int ROW_COUNT = 3;
    private static final int COL_COUNT = 3;
    private static final String CELL_STATE_EMPTY = " ";
    private static final String CELL_STATE_X = "X";
    private static final String CELL_STATE_0 = "0";
    private static final String GAME_STATE_X_WON = " X победили!";
    private static final String GAME_STATE_0_WON = " 0 победили!";
    private static final String GAME_STATE_DRAW = " Ничья!";
    private static final String GAME_STATE_IN_PROGRESS = " Игра не закончена!";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
//        String[][] board = createBoard();
//
//        board[0][0] = CELL_STATE_0;
//
//        inputCellCoordinates(board);

        startGameRound();
    }

    public static void startGameRound() {
        //create board
        //startGameLoop
        String[][] board = createBoard();
        startGameLoop(board);
    }

    public static void startGameLoop(String[][] board) {
        do {
            makePlayerTurn(board);
            printBoard(board);

            System.out.println();

            makeBotTurn(board);
            printBoard(board);

            String gameState = checkGameState(board);
            if (!Objects.equals(gameState, GAME_STATE_IN_PROGRESS)) {
                System.out.println(gameState);
                return;
            }
        } while (true);

        //while (gameNotOver)
        // playerTun
        // botTurn
        // checkGameState
    }
    private static int calculateNumValue(String cellState) {
        if (Objects.equals(cellState, CELL_STATE_X))
            return 1;
        else if (Objects.equals(cellState, CELL_STATE_0))
            return -1;
        else
            return 0;

    }

    public static boolean areAllCellsTaken(String[][] board) {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                if (board[row][col].equals(CELL_STATE_EMPTY)) {
                    return false;
                }
            }

        }
        return true;
    }

    public static void printBoard(String[][] board) {
        for (int row = 0; row < ROW_COUNT; row++) {
            String line = "| ";
            for (int col = 0; col < COL_COUNT; col++) {
                line += board[row][col] + " ";
            }
            line += "|";
            System.out.println(line);

        }
    }

    public static String[][] createBoard() {
        String[][] board = new String[ROW_COUNT][COL_COUNT];

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                board[row][col] = CELL_STATE_EMPTY;
            }
        }
        return board;
    }

    public static int[] inputCellCoordinates(String[][] board) {
        System.out.println("Введите координаты через пробел от 0 до 2");


        do {
            try {
                String[] input = scanner.nextLine().split(" ");

                int row = Integer.parseInt(input[0]);
                int col = Integer.parseInt(input[1]);
                if (row >= ROW_COUNT || row < 0 || col >= COL_COUNT || col < 0) {
                    System.out.println("Значения должны быть от 0 до 2");
                } else if (!board[row][col].equals(CELL_STATE_EMPTY)) {
                    System.out.println("Клетка занята");
                } else {
                    return new int[]{row, col};
                }

            } catch (RuntimeException e) {
                System.out.println("Значения должны быть от 0 до 2 и написаны через пробел");
            }

        } while (true);
    }

    public static void makePlayerTurn(String[][] board) {
        // get input
        int[] coordinates = inputCellCoordinates(board);
        // place X on board
        board[coordinates[0]][coordinates[1]] = CELL_STATE_X;

    }

    public static void makeBotTurn(String[][] board) {
        System.out.println("Ход бота");
        int[] coordinates = getEmptyRandomCellCoordinates(board);
        board[coordinates[0]][coordinates[1]] = CELL_STATE_0;
    }

    public static int[] getEmptyRandomCellCoordinates(String[][] board) {
        do {
            int row = random.nextInt(ROW_COUNT);
            int col = random.nextInt(COL_COUNT);

            if (board[row][col].equals(CELL_STATE_EMPTY)) {
                return new int[]{row, col};
            }
        } while (true);
        // get random empty cell
        // place 0 on board

    }

    public static String checkGameState(String[][] board) {
        ArrayList<Integer> sums = new ArrayList<>();

        for (int row = 0; row < ROW_COUNT; row++) {
            int rowSum = 0;
            for (int col = 0; col < COL_COUNT; col++) {
                rowSum += calculateNumValue(board[row][col]);
            }
            sums.add(rowSum);
        }
        for (int col = 0; col < COL_COUNT; col++) {
            int colSum = 0;
            for (int row = 0; row < ROW_COUNT; row++) {
                colSum += calculateNumValue(board[row][col]);
            }
            sums.add(colSum);
        }
        int leftDiagonal = 0;
        for (int row = 0; row < ROW_COUNT; row++) {
             leftDiagonal +=calculateNumValue(board[row][row]);
        }
        sums.add(leftDiagonal);
        int rightDiagonal = 0;
        for (int row = 0; row < ROW_COUNT; row++) {
            rightDiagonal +=calculateNumValue(board[row][(ROW_COUNT-1)-row]);
        }
        sums.add(rightDiagonal);

        if (sums.contains(3))
            return  GAME_STATE_X_WON;
        else if (sums.contains(-3))
            return  GAME_STATE_0_WON;
        else if (areAllCellsTaken(board))
            return GAME_STATE_DRAW;
        else return GAME_STATE_IN_PROGRESS;

    // x = 1, 0 - (-1), empty - 0
    // count sum for rows,columns and diagonals

    // if sum.contains(3) -> x won
    // if sum.contains(-3) -> 0 won
    // if all cells occupied -> DRAW
    // else game keep going

}
}
