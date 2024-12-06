/*
    Aufgabe 4) Zweidimensionale Arrays und Rekursion - Mini-Paint
*/

import codedraw.*;

import java.awt.*;

public class Aufgabe4 {

    private static final int squareSize = 50;

    private static void floodFill(CodeDraw myDrawObj, int[][] picArray, int sy, int sx) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        // Base case:
        if (sx < 0 || sx > 249 || sy < 0 || sy > 249 || picArray[sy][sx] == 1) {
            return;
        }

        // Recursive case:
        myDrawObj.setLineWidth(1);
        myDrawObj.drawPoint(sx, sy);
        picArray[sy][sx] = 1;

        floodFill(myDrawObj, picArray, sy, sx + 1);
        floodFill(myDrawObj, picArray, sy, sx - 1);
        floodFill(myDrawObj, picArray, sy + 1, sx);
        floodFill(myDrawObj, picArray, sy - 1, sx);
    }


    private static void paintLine(CodeDraw myDrawObj, int[][] picArray, int[] yClick, int[] xClick) {
        int x0 = xClick[0];
        int x1 = xClick[1];
        int y0 = yClick[0];
        int y1 = yClick[1];

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int xd = x0 < x1 ? 1 : -1;
        int yd = y0 < y1 ? 1 : -1;

        int e = dx - dy;
        int et;

        picArray[y0][x0] = 1;
        myDrawObj.drawPoint(x0, y0);
        System.out.println("Point: x:" + x0 + " y:" + y0);

        while (x0 != x1 || y0 != y1) {
            et = 2 * e;
            if (et > -dy) {
                e -= dy;
                x0 += xd;
            }
            if (et < dx) {
                e += dx;
                y0 += yd;
            }
            picArray[y0][x0] = 1;
            myDrawObj.drawPoint(x0, y0);
            System.out.println("Point: x:" + x0 + " y:" + y0);
        }
    }

    public static void main(String[] args) {
        int width = 300;
        int height = 250;
        CodeDraw myDrawObj = new CodeDraw(width, height);
        myDrawObj.setTitle("Mini-Paint");
        EventScanner myEventSC = myDrawObj.getEventScanner();

        // draw color buttons
        Color[] colors = new Color[]{Palette.RED, Palette.GREEN, Palette.BLUE, Palette.YELLOW, Palette.CYAN};
        for (int i = 0; i < colors.length; i++) {
            myDrawObj.setColor(colors[i]);
            myDrawObj.fillSquare(width - squareSize, i * squareSize, squareSize);
        }

        // draw black lines around buttons
        myDrawObj.setLineWidth(2);
        myDrawObj.setColor(Palette.BLACK);
        myDrawObj.drawLine(width - squareSize, 0, width - squareSize, height);
        for (int i = 1; i < myDrawObj.getHeight() / squareSize; i++) {
            myDrawObj.drawLine(width - squareSize, i * squareSize, width, i * squareSize);
        }
        myDrawObj.setLineWidth(1);
        myDrawObj.show();

        int[] yClick = new int[2];
        int[] xClick = new int[2];
        int[][] picArray = new int[height][width - squareSize];

        boolean colorChosen = false;

        while (!myDrawObj.isClosed()) {
            if (myEventSC.hasMouseClickEvent()) {
                MouseClickEvent currentClick = myEventSC.nextMouseClickEvent();
                System.out.println("Mouse Click: Y: " + currentClick.getY() + " X: " + currentClick.getX());

                int mouseY = currentClick.getY();
                int mouseX = currentClick.getX();

                int buttonCounter = 1;
                //Button RED
                if (mouseY >= 0 && mouseY < buttonCounter * (squareSize - 1) && mouseX >= width - squareSize && mouseX < width - 1) {
                    myDrawObj.setColor(Palette.RED);
                    colorChosen = true;
                }
                //Button GREEN
                else if (mouseY >= buttonCounter++ * squareSize && mouseY < buttonCounter * (squareSize - 1) && mouseX >= width - squareSize && mouseX < width - 1) {
                    myDrawObj.setColor(Palette.GREEN);
                    colorChosen = true;
                }
                //Button BLUE
                else if (mouseY >= buttonCounter++ * squareSize && mouseY < buttonCounter * (squareSize - 1) && mouseX >= width - squareSize && mouseX < width - 1) {
                    myDrawObj.setColor(Palette.BLUE);
                    colorChosen = true;
                }
                //Button YELLOW
                else if (mouseY >= buttonCounter++ * squareSize && mouseY < buttonCounter * (squareSize - 1) && mouseX >= width - squareSize && mouseX < width - 1) {
                    myDrawObj.setColor(Palette.YELLOW);
                    colorChosen = true;
                }
                //Button CYAN
                else if (mouseY >= buttonCounter++ * squareSize && mouseY < buttonCounter * (squareSize - 1) && mouseX >= width - squareSize && mouseX < width - 1) {
                    myDrawObj.setColor(Palette.CYAN);
                    colorChosen = true;
                }

                else {
                    if (colorChosen) {
                        //**********************************************
                        //TODO: Ergänzen Sie den fehlenden Code (Zustand: Fläche füllen)
                        //**********************************************
                        floodFill(myDrawObj, picArray, mouseY, mouseX);
                        myDrawObj.show();
                        colorChosen = false;
                    } else {
                        myDrawObj.setLineWidth(2);
                        myDrawObj.setColor(Palette.BLACK);

                        //**********************************************
                        //TODO: Ergänzen Sie den fehlenden Code (Zustand: Linien zeichnen)
                        //**********************************************
                        xClick[0] = xClick[1];
                        xClick[1] = mouseX;
                        yClick[0] = yClick[1];
                        yClick[1] = mouseY;
                        paintLine(myDrawObj, picArray, yClick, xClick);

                        myDrawObj.setLineWidth(1);
                        myDrawObj.show();
                    }
                }
            } else {
                myEventSC.nextEvent();
            }
        }
    }
}

