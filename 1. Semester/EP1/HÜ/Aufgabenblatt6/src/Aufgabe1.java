/*
    Aufgabe 1) Zweidimensionale Arrays und Rekursion - Game "TicTacToe"
*/

import codedraw.*;

import java.util.Scanner;

public class Aufgabe1 {

    public static int size, maxDepth;
    public static CodeDraw myDrawObj;

    public enum ConfigType {
        INTEGER,
        BOOLEAN,
        NONE;
    }

    public enum GameEnd {
        PLAYER1WIN,
        PLAYER2WIN,
        STALEMATE;
    }

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

        String[] endTexts = new String[] {"Player 1 (Human) playing as X has won!\nPlayer 2 (%s) playing as O has lost!", "Player 2 (%s) playing as O has won!\nPlayer 1 (Human) playing as X has lost!", "Stalemate!\nNo one has won or lost."};
        String[] configData = startWindow(new String[] {"Do you want to play against a computer? (Enter a boolean) \nOtherwise you play against a second human player.\nPlease enter your answer in the console.", "Should the visualization of the prediction of the minimax algorithm be shown? (Enter a boolean)\nPlease enter your answer in the console.", "What should the depth of the minimax algorithm be? (Enter a integer)\nPlease enter your answer in the console.", "Player 1 (Human) will be X and Player 2 (%s) will be O.%s\nPlease enter something in the console to proceed."}, new ConfigType[]{ConfigType.BOOLEAN, ConfigType.BOOLEAN, ConfigType.INTEGER, ConfigType.NONE});

        boolean twoPlayer = !Boolean.parseBoolean(configData[0]); //true ... human vs. human, false ... human vs. computer
        boolean player = true; //(1Player) human = true, computer = false, (2Player) human1 = true, human2 = false
        maxDepth = Math.max(Integer.parseInt(configData[2]), 0);
        boolean gameRunning = true;
        boolean showMinimax = Boolean.parseBoolean(configData[1]);
        boolean showingMinimax = false;

        drawGameBoard(myDrawObj, gameBoard);

        while (!myDrawObj.isClosed() && gameRunning) {
            if (myEventSC.hasMouseClickEvent() && !showingMinimax) {
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
                    showingMinimax = true;
                    myDrawObj.show();

                    System.out.println("To abort showing the minimax visualization please press a key or click in the window.");
                } else {
                    retArray = minimax(gameBoard, false, maxDepth);
                }

                // Set gameboard at indices to computer symbol
                gameBoard[retArray[0]][retArray[1]] = 'O';

                // Change player
                player = !player;
            } else if (showingMinimax && (myEventSC.hasKeyPressEvent() || myEventSC.hasMouseClickEvent())) {
                showingMinimax = false;
                myEventSC.nextEvent();
            } else {
                myEventSC.nextEvent();
            }

            // Draw gameboard
            if (!showingMinimax) {
                myDrawObj.clear();
                drawGameBoard(myDrawObj, gameBoard);
                myDrawObj.show();
            }

            // Check win/stalemate conditions
            if (!showingMinimax && checkIfFull(gameBoard)) {
                System.out.println("Stalemate");
                gameRunning = false;
                endWindow(GameEnd.STALEMATE, twoPlayer, endTexts);
            } else if (!showingMinimax && (checkIfWinner(gameBoard, player) || checkIfWinner(gameBoard, !player))) {
                System.out.println("Player " + (!player ? "X" : "O") + " has won.");
                gameRunning = false;
                endWindow(!player ? GameEnd.PLAYER1WIN : GameEnd.PLAYER2WIN, twoPlayer, endTexts);
            }
        }
    }

    private static String[] startWindow(String[] prompts, ConfigType[] types) {
        CodeDraw cd = new CodeDraw(800, 100);
        cd.setTitle("Configuration");
        cd.setAlwaysOnTop(true);
        cd.setWindowPositionX(myDrawObj.getWindowPositionX() + myDrawObj.getWidth() / 2 - cd.getWidth() / 2);
        cd.setWindowPositionY(myDrawObj.getWindowPositionY() + myDrawObj.getHeight() / 2 - cd.getHeight() / 2);
        cd.setTextFormat(new TextFormat().setTextOrigin(TextOrigin.CENTER));
        cd.show();

        Scanner scanner = new Scanner(System.in);
        String[] answers = new String[prompts.length];
        int i = 0;

        while (!cd.isClosed()) {
            System.out.println(String.format(prompts[i], Boolean.parseBoolean(answers[0]) ? "Computer" : "Human", Boolean.parseBoolean(answers[1]) ? "\nTo abort showing the minimax visualization please press a key or click in the window." : ""));
            cd.clear();
            cd.drawText(cd.getWidth() / 2.0, cd.getHeight() / 2.0, String.format(prompts[i], Boolean.parseBoolean(answers[0]) ? "Computer" : "Human", Boolean.parseBoolean(answers[1]) ? "\nTo abort showing the minimax visualization please press a key or click in the window." : ""));
            cd.show();

            if (scanner.hasNext()) {
                if (types[i] == ConfigType.BOOLEAN) {
                    answers[i++] = String.valueOf(scanner.nextBoolean());
                } else if (types[i] == ConfigType.INTEGER) {
                    answers[i++] = String.valueOf(scanner.nextInt());
                } else if (types[i] == ConfigType.NONE) {
                    answers[i++] = "";
                }
                System.out.println();

                if (i == prompts.length) {
                    cd.close();
                }
            }
        }

        return answers;
    }

    private static void endWindow(GameEnd end, boolean twoPlayers, String[] endTexts) {
        String endText = (end == GameEnd.PLAYER1WIN) ? endTexts[0] : ((end == GameEnd.PLAYER2WIN) ?  endTexts[1] : endTexts[2]);

        CodeDraw cd = new CodeDraw(800, 200);
        cd.setTitle("Game end");
        cd.setWindowPositionX(myDrawObj.getWindowPositionX() + myDrawObj.getWidth() / 2 - cd.getWidth() / 2);
        cd.setWindowPositionY(myDrawObj.getWindowPositionY() + myDrawObj.getHeight() / 2 - cd.getHeight() / 2);
        cd.setTextFormat(new TextFormat().setTextOrigin(TextOrigin.CENTER).setBold(true).setFontSize(30));
        cd.drawText(cd.getWidth() / 2.0, cd.getHeight() / 2.0, String.format(endText, twoPlayers ? "Human" : "Computer") + "\nPress a key to end the application.");
        cd.show();

        EventScanner es = cd.getEventScanner();

        while (!cd.isClosed()) {
            if (es.hasKeyPressEvent()) {
                System.out.println("Game is now ending.");
                cd.close();
            } else {
                es.nextEvent();
            }
        }

        myDrawObj.close();
    }

    private static int[] minimax(char[][] gameBoard, boolean player, int depth) {
        return minimax(gameBoard, player, depth, 0, size, 0, size);
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
        drawGameBoard(myDrawObj, gameBoard, 0, size, 0, size);
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
