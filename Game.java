import java.util.Random;
import java.util.Scanner;

public class Game {
    // Global declaration of variables
    char player = ' ';
    char computer = ' ';
    char winner = ' ';

    Scanner input = new Scanner(System.in); // Object created for 'Scanner' class to access the methods defined in it for handling inputs from the user.

    Random random = new Random(); // Object created for 'Random' class to access the methods defined in it for handling random value generation.

    char[][] positions = new char[3][3]; // 2-D array for storing the symbols.

    /**
     * This method 'resetBoard()' is used to initialize the 2D array 'positions' with ' ' values.
     * This is performed to align the board properly.
     */
    public void resetBoard() {
        for (int i = 0; i < positions.length; i += 1) {
            for (int j = 0; j < positions[i].length; j += 1) {
                positions[i][j] = ' ';
            }
        }
    }

    /**
     * The 'printBoard()' prints the 3 x 3 board, along with the symbols, on the terminal screen.
     * Basically, I visualized the 3 x 3 matrix, hence used a 2D array. (where, positions[i][j], i => rows, j => columns).
     * Since, I am printing the board at the starting of the game, I am resetting the board with ' ' (empty) values using the 'resetBoard()' method. 
     * By doing this the symbols are equally arranged. 
     */
    public void printBoard() {
        // This condition turned out to be useless!!! 
        // if (checkFreeSpaces() == 9) {
        //     resetBoard();
        // }

        System.out.printf(" %c | %c | %c ", positions[0][0], positions[0][1], positions[0][2]);
        System.out.print("\n---|---|---\n");
        System.out.printf(" %c | %c | %c ", positions[1][0], positions[1][1], positions[1][2]);
        System.out.print("\n---|---|---\n");
        System.out.printf(" %c | %c | %c ", positions[2][0], positions[2][1], positions[2][2]);
        System.err.println();
    }

    /**
     * Here, The 'checkFreeSpaces()' method returns the no. of free spaces in the array.
     * 
     * previous approach:
     * Here, We can observe that we are comparing the values of the array to '0'. This is because the default value of 'char' arrays is denoted as '\u0000' which is equivalent to '0'.
     * So, empty values can be calculated by taking the count of '0' in the array.
     * 
     * modified approach:
     * Comparing the array values with ' ', since we reset the board with ' ' or empty values.
     * 
     * @return
     */
    public int checkFreeSpaces() {
        int freeSpaces = 0;

        for (int i = 0; i < positions.length; i += 1) {
            for(int j = 0; j < positions[i].length; j += 1) {
                if (positions[i][j] == ' ') { 
                    freeSpaces += 1;
                }
            }
        }

        return freeSpaces;
    }

    // 'chooseCharacter()' is a simple method to make the user choose the symbol that they want to play with.
    public void chooseCharacter() {
        System.out.println();
        System.out.print("Choose your Symbol (X or O): ");

        // Why can't we type cast a 'String' to 'char'?

        player = input.next().toUpperCase().charAt(0); // Players always don't enter the uppercase letter. So we use the '.toUpperCase()' method.

        if (player == 'X') {
            computer = 'O';
        } else {
            computer = 'X';
        }

        System.out.println("Player: " + player);
        System.out.println("Computer: " + computer);
    }

    /**
     * 'playerMove()' method contains the logic to place the player's symbol in the array.
     * Decerement the user value by 1 to match with the positions of the array. (Since, array starts with the index 1).
     * 
     * As of now, will not handle invalid moves. We will assume that the player is aware about the positions that they are entering.
     */
    public void playerMove() {
        System.out.println();
        System.out.println("PLAYER'S MOVE: " + player );

        int row = 0;
        int col = 0;

        System.out.print("Enter the row (1 - 3): ");
        row = input.nextInt();

        System.out.print("Enter the column (1 - 3): ");
        col = input.nextInt();

        System.out.println();

        row -= 1;
        col -= 1;

        positions[row][col] = player;
        checkWinner();
        printBoard();
        System.out.print("No. of free spaces: " + checkFreeSpaces() + "\n");
        System.out.println();
        System.out.println("----------------------");
    }

    /**
     * 'computerMove()' method generates the random integer values ranging between 0 and 3.
     * The .nextInt() method has two parameters - origin, bound. How it works? The numbers generated are => [origin to (bound - 1)], including the origin.
     * So, (0, 3) means, 0,1,2 numbers are generated.
     * A do-while loop is used in the logic. The while loop is executed only when the generated numbers doesn't have a ' ', rather a symbol is already present.
     */
    public void computerMove() {
        if (checkFreeSpaces() > 0) {
            System.out.println();
            System.out.println("COMPUTER'S MOVE: " + computer);
    
            int row;
            int col;
    
            do {
                row = random.nextInt(0,3);
                col = random.nextInt(0,3);
            } while (positions[row][col] != ' ');
    
            positions[row][col] = computer;
            checkWinner();
            printBoard();
            System.out.println("No. of free spaces: " + checkFreeSpaces());
            System.out.println();
            System.out.println("----------------------");
        }
    }

    /**
     * The 'checkWinner()' method, initially checks the straight line order in rows, then in columns, and then in diagonals.
     * The logic is executed below. 
     */
    public void checkWinner() {
        // This loop checks row wise.
        for (int i = 0; i < positions.length; i += 1) {
            if (positions[i][0] == positions[i][1] && positions[i][0] == positions[i][2]) {
                winner = positions[i][0];
                break;
            }
        }

        // This loop checks column wise.
        for (int i = 0; i < positions.length; i += 1) {
            if (positions[0][i] == positions[1][i] && positions[0][i] == positions[2][i]) {
                winner = positions[0][i];
                break;
            }
        }

        // Condition to check in diagonals - both directions.
        if (positions[0][0] == positions[1][1] && positions[0][0] == positions[2][2]) {
            winner = positions[0][0];
        }

        if (positions[0][2] == positions[1][1] && positions[0][2] == positions[2][0]) {
            winner = positions[0][2];
        }
    }

    /**
     * The 'printWinner()' method prints the winner of the game.
     * This method uses a simple 'if-else' ladder. 
     */
    public void printWinner() {
        if (winner == player) {
            System.out.println("WINNER: " + player);
            System.out.printf("CONGRAGULATIONS ('%c') for winning the Game ;) \n", player);
        } else if (winner == computer) {
            System.out.println("WINNER: " + computer);
            System.out.printf("COMPUTER ('%c') WON ;) \n", computer);
            System.out.println("UH OH :( TRY NEXT TIME.");
        } else {
            System.out.println("TIE. TRY AGAIN :(");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        System.out.println("Welcome to TIC-TAC-TOE: \n");

        game.resetBoard();
        game.printBoard();

        System.out.println();
        System.out.println("Rules: ");
        System.out.print(" - You should choose either 'X' or 'O' to play this game. \n");
        System.out.print(" - You win, if you arrange your symbol in a straight line (either horizontally, vertically, or diagonally) \n");
        System.out.print(" - You lose, if the computer arranges it's symbol in a straight line. \n");
        System.out.print(" - If you and the computer fail to arrange the symbols in a straight line, The game is considered a TIE. \n");
        System.out.print(" - Rule for the player: Enter the row you want to place the symbol in and click on 'Enter'. Similarly for column. \n");
        System.out.println();
        System.out.println("Hope you understood the rules...Now choose your symbol to play the game.");
        
        game.chooseCharacter();

        do {
            game.playerMove();
            game.computerMove();
        } while (game.winner == ' ' && game.checkFreeSpaces() > 0);

        System.out.println();
        game.printWinner();
        System.out.println();
    }
}
