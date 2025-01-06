/*
    Aufgabe 1) Zweidimensionale Arrays und Rekursion - Game "TicTacToe"
*/

import codedraw.*;

public class Aufgabe1 {

    public static int size, maxDepth;
    public static CodeDraw myDrawObj;

    public static void main(String[] args) {
        size = 600;
        myDrawObj = new CodeDraw(size, size);
        myDrawObj.setTitle("Tic Tac Toe");
        EventScanner myEventSC = myDrawObj.getEventScanner();

        char[][] gameBoard = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        boolean twoPlayer = false; //true ... human vs. human, false ... human vs. computer
        boolean player = true; //(1Player) human = true, computer = false, (2Player) human1 = true, human2 = false
        maxDepth = 3;
        boolean gameRunning = true;
        boolean showMinimax = false;

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
                int[] retArray;
                if (showMinimax) {
                    retArray = minimax(gameBoard, false, maxDepth, 0, size, 0, size);
                    myDrawObj.show();
                } else {
                    retArray = minimax(gameBoard, false, maxDepth);
                }

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

            myDrawObj.clear();
            drawGameBoard(myDrawObj, gameBoard);
            myDrawObj.show();
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

    private static int[] minimax(char[][] gameBoard, boolean player, int depth, double lastMinX, double lastMaxX, double lastMinY, double lastMaxY) {
        int[] retArray = new int[3];
        retArray[2] = player ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (Character.isWhitespace(gameBoard[i][j])) {
                    gameBoard[i][j] = player ? 'X' : 'O';

                    // Compute minX, maxX, minY, maxY
                    double width = lastMaxX - lastMinX, height = lastMaxY - lastMinY;
                    double widthThird = width / 3, heightThird = height / 3;
                    double minX = lastMinX + widthThird * j, maxX = lastMinX + widthThird * (j + 1), minY = lastMinY + heightThird * i, maxY = lastMinY + heightThird * (i + 1);

                    if (checkIfWinner(gameBoard, true)) {
                        retArray = new int[] {i, j, -1};
                    } else if (checkIfWinner(gameBoard, false)) {
                        retArray = new int[] {i, j, 1};
                    } else if (depth - 1 == 0 || checkIfFull(gameBoard)) {
                        retArray = new int[] {i, j, 0};
                    } else {
                        int[] tempArray = minimax(gameBoard, !player, depth - 1, minX, maxX, minY, maxY);
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

                    drawGameBoard(myDrawObj, gameBoard, minX, maxX, minY, maxY);

                    gameBoard[i][j] = ' ';
                }
            }
        }
        return retArray;
    }

    private static boolean checkIfFull(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char entry : row) {
                if (entry == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkIfWinner(char[][] gameBoard, boolean player) {
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

        return false;
    }

    private static void drawGameBoard(CodeDraw myDrawObj, char[][] gameBoard) {
        // Draw four lines
        int width = size, height = size, widthThird = width / 3, heightThird = width / 3;

        myDrawObj.drawLine(0, heightThird, width, heightThird);
        myDrawObj.drawLine(0, 2 * heightThird, width, 2 * heightThird);
        myDrawObj.drawLine(widthThird, 0, widthThird, height);
        myDrawObj.drawLine(2 * widthThird, 0, 2 * widthThird, height);

        // Draw entries of gameBoard
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                int minX = j * widthThird, maxX = (j + 1) * widthThird, minY = i * heightThird, maxY = (i + 1) * heightThird;

                // Draw X
                if (gameBoard[i][j] == 'X') {
                    myDrawObj.drawLine(minX, minY, maxX, maxY);
                    myDrawObj.drawLine(minX, maxY, maxX, minY);
                }

                // Draw circle
                if (gameBoard[i][j] == 'O') {
                    myDrawObj.drawCircle((double) (minX + maxX) / 2, (double) (minY + maxY) / 2, (double) widthThird / 2);
                }

            }
        }
    }

    private static void drawGameBoard(CodeDraw myDrawObj, char[][] gameBoard, double minX, double maxX, double minY, double maxY) {
        // Draw four lines
        double width = maxX - minX, height = maxY - minY;
        double widthThird = width / 3, heightThird = height / 3;

        myDrawObj.drawLine(minX, minY + heightThird, maxX, minY + heightThird);
        myDrawObj.drawLine(minX, minY + 2 * heightThird, maxX, minY + 2 * heightThird);
        myDrawObj.drawLine(minX + widthThird, minY, minX + widthThird, maxY);
        myDrawObj.drawLine(minX + 2 * widthThird, minY, minX + 2 * widthThird, maxY);


        // Draw entries of gameBoard
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                double minX2 = minX + j * widthThird, maxX2 = minX + (j + 1) * widthThird, minY2 = minY + i * heightThird, maxY2 = minY + (i + 1) * heightThird;

                // Draw cross
                if (gameBoard[i][j] == 'X') {
                    myDrawObj.drawLine(minX2, minY2, maxX2, maxY2);
                    myDrawObj.drawLine(minX2, maxY2, maxX2, minY2);
                }

                // Draw circle
                if (gameBoard[i][j] == 'O') {
                    myDrawObj.drawCircle((minX2 + maxX2) / 2, (minY2 + maxY2) / 2, widthThird / 2);
                }

            }
        }
    }

}
