import java.util.Random;
import java.util.Scanner;

public class Game {
    char player = ' ';
    char computer = ' ';
    char winner = ' ';

    Scanner input = new Scanner(System.in);

    Random random = new Random();

    char[][] positions = new char[3][3];
    char[][] occupied = new char[3][3];

    /**
     * This method 'populateBoard()' is used to initialize the 2D array 'positions' with ' ' values.
     * This is performed to align the board properly.
     */
    public void populateBoard() {
        for (int i = 0; i < positions.length; i += 1) {
            for (int j = 0; j < positions[i].length; j += 1) {
                positions[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        if (checkFreeSpaces() == 9) {
            populateBoard();
        }

        System.out.printf(" %c | %c | %c ", positions[0][0], positions[0][1], positions[0][2]);
        System.out.print("\n---|---|---\n");
        System.out.printf(" %c | %c | %c ", positions[1][0], positions[1][1], positions[1][2]);
        System.out.print("\n---|---|---\n");
        System.out.printf(" %c | %c | %c ", positions[2][0], positions[2][1], positions[2][2]);
        System.err.println();
    }

    /**
     * Here, The 'checkFreeSpaces()' method returns the no. of free spaces in the array.
     * Here, We can observe that we are comparing the values of the array to '0'. This is because the default value of char
     * arrays is denoted as '\u0000' which is equivalent to '0'.
     * So, empty values can be calculated by taking the count of '0' in the array.
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

    public void chooseCharacter() {
        System.out.print("Choose the Character (X or O): ");

        // Why can't we type cast a 'String' to 'char'?

        player = input.next().toUpperCase().charAt(0); // Players always don't enter the uppercase letter. So we use the '.toUpperCase()' method.

        if (player == 'X') {
            computer = 'O';
        } else {
            computer = 'X';
        }

        System.out.println("Player 1: " + player);
        System.out.println("Player 2: " + computer);
    }

    // As of now, will not handle invalid moves. We will assume that the player is aware about the positions.
    public void playerMove() {
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
        printBoard();
        System.out.print("No. of free spaces: " + checkFreeSpaces() + "\n");
    }

    public void computerMove() {
        if (checkFreeSpaces() > 0) {
            System.out.println("COMPUTER'S MOVE: " + computer);
    
            int row;
            int col;
    
            do {
                row = random.nextInt(2);
                col = random.nextInt(2);
            } while (positions[row][col] == ' ');
    
            positions[row][col] = computer;
            printBoard();
            System.out.println("No. of free spaces: " + checkFreeSpaces() + "\n");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        System.out.println("Welcome to TIC-TAC-TOE: XO ");
        game.populateBoard();
        game.printBoard();
        game.chooseCharacter();

        while (game.checkFreeSpaces() > 0) {
            game.playerMove();
            game.computerMove();
        }
    }
}
