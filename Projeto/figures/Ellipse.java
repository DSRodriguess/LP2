package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {

    Color corPreenchimento;
    Color corBorda;

    public Ellipse (int x, int y, int w, int h) {
        super (x, y,w,h);
    }

    public void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5)); 
        g2d.setColor(this.corBorda);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(this.corPreenchimento);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}
