/*
    Aufgabe 1) Zweidimensionale Arrays und Rekursion - Game "TicTacToe"
*/

import codedraw.*;

public class Aufgabe1 {
    public static void main(String[] args) {

        int size = 600;
        CodeDraw myDrawObj = new CodeDraw(size, size);
        myDrawObj.setTitle("Tic Tac Toe");
        EventScanner myEventSC = myDrawObj.getEventScanner();

        char[][] gameBoard = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        boolean twoPlayer = false; //true ... human vs. human, false ... human vs. computer
        boolean player = true; //(1Player) human = true, computer = false, (2Player) human1 = true, human2 = false
        int maxDepth = 3;
        boolean gameRunning = true;

        drawGameBoard(myDrawObj, gameBoard);

        while (!myDrawObj.isClosed() && gameRunning) {
            if (myEventSC.hasMouseClickEvent()) {
                // Convert click coordinates into indices
                MouseClickEvent mouseClickEvent = myEventSC.nextMouseClickEvent();
                int widthThird = myDrawObj.getWidth() / 3, heightThird = myDrawObj.getHeight() / 3;
                int i = mouseClickEvent.getY() / heightThird, j = mouseClickEvent.getX() / widthThird;

                // Set gameboard at indices to correct player symbol
                gameBoard[i][j] = player ? 'X' : 'O';

                // Change player
                player = !player;
            } else if (!twoPlayer && !player) {
                // Call minimax to get indices for best move
                int[] retArray = minimax(gameBoard, false, maxDepth);

                // Set gameboard at indices to computer symbol
                gameBoard[retArray[0]][retArray[1]] = 'O';

                // Change player
                player = !player;
            } else {
                myEventSC.nextEvent();
            }

            // TODO: Improve with win / checkmate message
            if (checkIfFull(gameBoard)) {
                System.out.println("Stalemate");
                gameRunning = false;
            } else if (checkIfWinner(gameBoard, player) || checkIfWinner(gameBoard, !player)) {
                System.out.println("Player " + (!player ? "X" : "O") + " has won.");
                gameRunning = false;
            }

            drawGameBoard(myDrawObj, gameBoard);
        }
    }

    private static int[] minimax(char[][] gameBoard, boolean player, int depth) {
        int[] retArray = new int[3];
        retArray[2] = player ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (Character.isWhitespace(gameBoard[i][j])) {
                    gameBoard[i][j] = player ? 'X' : 'O';

                    if (checkIfWinner(gameBoard, true)) {
                        retArray = new int[] {i, j, -1};
                    } else if (checkIfWinner(gameBoard, false)) {
                        retArray = new int[] {i, j, 1};
                    } else if (depth - 1 == 0 || checkIfFull(gameBoard)) {
                        retArray = new int[] {i, j, 0};
                    } else {
                        int[] tempArray = minimax(gameBoard, !player, depth - 1);
                        if (player) {
                            if (tempArray[2] < retArray[2]) {
                                retArray = new int[] {i, j, tempArray[2]};
                            }
                        } else {
                            if (tempArray[2] > retArray[2]) {
                                retArray = new int[] {i, j, tempArray[2]};
                            }
                        }
                    }
                    gameBoard[i][j] = ' ';
                }
            }
        }
        return retArray;
    }

    private static boolean checkIfFull(char[][] gameBoard) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        for (char[] row : gameBoard) {
            for (char entry : row) {
                if (entry == ' ') {
                    return false;
                }
            }
        }
        return true; //Zeile kann geändert oder entfernt werden.
    }

    private static boolean checkIfWinner(char[][] gameBoard, boolean player) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        char checkChar = player ? 'X' : 'O';

        for (int i = 0; i < gameBoard.length; i++) {
            int counter = 0;
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == checkChar) {
                    // Check horizontally
                    for (int k = j + 1; k < gameBoard[i].length; k++) {
                        if (gameBoard[i][k] == checkChar) {counter++;}
                    }
                    if (counter == 2) { return true; }
                    counter = 0;


                    // Check vertically
                    for (int k = i + 1; k < gameBoard.length; k++) {
                        if (gameBoard[k][j] == checkChar) {counter++;}
                    }
                    if (counter == 2) { return true; }
                    counter = 0;


                    // Check diagonally towards the right
                    for (int k = 1; k < gameBoard.length - Math.max(i, j); k++) {
                        if (gameBoard[i + k][j + k] == checkChar) {counter++;}
                    }
                    if (counter == 2) { return true; }
                    counter = 0;


                    // Check diagonally towards the left
                    for (int k = 1; k < Math.min(gameBoard.length - i, j + 1); k++) {
                        if (gameBoard[i + k][j - k] == checkChar) {counter++;}
                    }
                    if (counter == 2) { return true; }
                }
            }
        }

        return false; //Zeile kann geändert oder entfernt werden.
    }

    private static void drawGameBoard(CodeDraw myDrawObj, char[][] gameBoard) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        // Draw four lines
        int width = myDrawObj.getWidth(), height = myDrawObj.getHeight(), widthThird = width / 3, heightThird = width / 3;

        myDrawObj.drawLine(0, widthThird, width, widthThird);
        myDrawObj.drawLine(0, 2 * widthThird, width, 2 * widthThird);
        myDrawObj.drawLine(widthThird, 0, widthThird, height);
        myDrawObj.drawLine(2 * widthThird, 0, 2 * widthThird, height);


        // Draw entries of gameBoard
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                int minX = j * widthThird, maxX = (j + 1) * widthThird, minY = i * widthThird, maxY = (i + 1) * widthThird;

                // Draw cross
                if (gameBoard[i][j] == 'X') {
                    myDrawObj.drawLine(minX, minY, maxX, maxY);
                    myDrawObj.drawLine(minX, maxY, maxX, minY);
                }

                // Draw circle
                if (gameBoard[i][j] == 'O') {
                    myDrawObj.drawCircle((minX + maxX) / 2, (minY + maxY) / 2, widthThird / 2);
                }

            }
        }

        myDrawObj.show();
    }

}
