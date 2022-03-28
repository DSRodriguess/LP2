package figures;


import java.awt.*;

public class Texto extends Figure {


    public Texto (int x, int y, int w, int h) {
        super (x, y,w,h);
    }

    public void print () {
        System.out.format("Texto (%d,%d) na posicao (%d,%d).\n",
            this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(Color.magenta);
        g2d.setFont(new Font("Ink Free",Font.BOLD,50));
        g2d.drawString("Teste de Texto", x, y);
    }
}
