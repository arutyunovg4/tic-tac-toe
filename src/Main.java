import java.util.Scanner;

public class Main {
    private static final int ROW_COUNT = 3;
    private static final int COL_COUNT = 3;
    private static final String CELL_STATE_EMPTY = " ";
    private static final String CELL_STATE_X = "X";
    private static final String CELL_STATE_0 = "0";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[][] board = createBoard();

        board[0][0] = CELL_STATE_0;

        inputCellCoordinates(board);

        System.out.println("Hi");
    }

    public static void startGameRound() {
        //create board
        //startGameLoop
        String[][] board = createBoard();
        startGameLoop(board);
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

    public static void startGameLoop(String[][] board) {
        //while (gameNotOver)
        // playerTun
        // botTurn
        // checkGameState
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
            }
            else{
                return new int[]{row, col};
            }

            }catch (RuntimeException e){
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

    public static void makeBotTurn() {
        // get random empty cell
        // place 0 on board

    }

    public static void checkGameState() {
        // x = 1, 0 - (-1), empty - 0
        // count sum for rows,columns and diagonals

        // if sum.contains(3) -> x won
        // if sum.contains(-3) -> 0 won
        // if all cells occupied -> DRAW
        // else game keep going
    }
}
