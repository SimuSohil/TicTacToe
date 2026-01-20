**Tic-Tac-Toe (Java Terminal Edition)**

Welcome to my first terminal-based game! This is a classic Tic-Tac-Toe (XO) game built from scratch using Java. 
It utilizes a 2D array to represent the game board and features a computer opponent that makes moves based on random generation.

**How to Play?**
1. Choose your symbol: At the start, pick between X or O.
2. Make your move: Enter the Row (1-3) and Column (1-3) when prompted.
3. The Goal: Align three of your symbols horizontally, vertically, or diagonally to win!
4. The Opponent: After every move you make, the computer will automatically place its symbol in a random empty space.

Learnings
1. This project served as a great practical application of Java fundamentals. Based on the development process and the Javadoc comments in my code, here are the key takeaways:
2. **2D Array Visualization**: I learned to use a char[3][3] matrix to represent the board, where positions[i][j] maps directly to rows and columns.
3. **Initialization & Alignment**: Initially, I discovered that char arrays default to \u0000 (null). I learned to "reset" the board with ' ' (space) characters to ensure the board prints with proper alignment.
4. **Data Type Handling**: I learned why we can't directly typecast a String to a char. Instead, I used .toUpperCase().charAt(0) to sanitize user input and extract the character needed.
5. **Random Bound Logic**: A crucial learning point was understanding random.nextInt(origin, bound). I learned that the bound is exclusive, so to get numbers 0, 1, and 2, the parameters must be (0, 3).
6. **Game Loop Logic**: Implementing the do-while loop helped me understand how to keep a game running until a specific condition (a win or a tie) is met.
