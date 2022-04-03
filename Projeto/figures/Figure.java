package figures;
import java.awt.*;

public abstract class Figure {
    protected int x, y;
    protected int w, h;
    protected static Color corBorda;
    protected static Color corPreenchimento;


    public Figure (int x, int y, int w, int h, Color corBorda, Color corPreenchimento) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        Figure.corBorda = corBorda;
        Figure.corPreenchimento = corPreenchimento;
    }

    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }


    public abstract boolean colision(int mx, int my);
    public abstract void paint (Graphics g);
    public abstract void tamanho(int w, int h);
}