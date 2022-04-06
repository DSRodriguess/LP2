package figures;
import java.awt.*;

public class Linha extends Figure {
    protected int [] cor2;

    public Linha (int [] cor, int x, int y, int [] cor2) {
        super (cor, x, y);
        this.cor2 = cor2;
    }
    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
        this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(5));
    
        g2d.setColor(new Color(this.cor[0], this.cor[1], this.cor[2]));
        g2d.drawLine(this.x, this.y, this.w, this.h);
    }

}
