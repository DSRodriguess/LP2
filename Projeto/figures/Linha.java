package figures;


import java.awt.*;

public class Linha extends Figure {


    public Linha (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
        this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(Color.blue);
        g2d.setStroke(new BasicStroke (3));
        g2d.drawLine(x, y, w, h);
    }
}
