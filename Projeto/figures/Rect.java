package figures;
import java.awt.*;

public class Rect extends Figure {
    protected int [] cor2;
    

    public Rect (int [] cor,int x, int y,int [] cor2) {
        super (cor, x, y);
        this.cor2 = cor2;
    }


    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setStroke(new BasicStroke(10)); 
        
        g2d.setColor(new Color(this.cor2[1], this.cor2[1], this.cor2[1]));
        g2d.drawRect(this.x,this.y, this.w,this.h);

        g2d.setColor (new Color(this.cor[0], this.cor[1], this.cor[2]));
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }

}