package figures;
import java.awt.*;

public abstract class Figure {
    protected int x, y;
    protected int w, h;
    protected Color corBorda;
    protected Color corPreenchimento;

    public Figure (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void paint (Graphics g);
}