/*
    Aufgabe 6) Kreise => Rekursiv
*/

import codedraw.*;

import java.awt.*;

public class Aufgabe6 {

    private static void drawCirclesRec(CodeDraw myDrawObj, int y, int radius, int num) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        // Base case
        if (num < 0) {
            return;
        }

        // Recursive case: Draw a circle and go into recursion
        myDrawObj.setColor(new Color(255 - num * 30, 255 - num * 30, 0));
        myDrawObj.fillCircle(myDrawObj.getWidth() / 2.0, y, radius);

        drawCirclesRec(myDrawObj, y + radius / 2, radius / 2, (num - 1));
        drawCirclesRec(myDrawObj, y - radius / 2, radius / 2, (num - 1));
    }

    public static void main(String[] args) {
        int pixelWidth = 512;
        int pixelHeight = 512;
        CodeDraw myDrawObj = new CodeDraw(pixelWidth, pixelHeight);
        drawCirclesRec(myDrawObj, pixelHeight / 2, pixelWidth / 2, 5);
        myDrawObj.show();
    }
}



