package figures;
import java.awt.*;

public class Linha extends Figure {


    public Linha (int x, int y, int w, int h) {
        super (x, y,w,h,corBorda, corBorda);
    }


    public boolean colision(int mx, int my){
        return (this.x <= mx && mx<= this.x + this.w && this.y <= my && my <= this.y + this.h);
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

    public void tamanho(int w, int h){
        if(this.w >= 30 && this.w <= 200){
            if(this.w == 200){
                w = -5;
                h = -5;
            }
            else if(this.w == 30){
                w = +5;
                h = +5;
            }
            
            this.w += w;
            this.h += h;
        }
    }

}
