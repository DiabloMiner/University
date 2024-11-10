import codedraw.CodeDraw;
import codedraw.Palette;

public class RecursiveSquares {

    public static void drawSquares(CodeDraw gc, double x, double y, double length) {
        if (length > 1) {
            if (Double.compare(128, length) == 0) {
                gc.setColor(Palette.BURLY_WOOD);
            }
            gc.fillSquare(x, y, length);
            gc.show(1000);
            drawSquares(gc, x + length, y + length, length / 2.0);
        }
    }

    public static void main(String[] args) {
        int size = 512;
        CodeDraw figure = new CodeDraw(size, size);
        drawSquares(figure, 0, 0, size / 2.0);
        figure.show();
    }

}
