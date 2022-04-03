package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {

    public Ellipse (int x, int y, int w, int h, Color corBorda, Color corPreenchimento) {
        super (x, y,w,h,corBorda,corPreenchimento);
    }

    public Ellipse (int x, int y, int w, int h) {
        super(x, y, w, h, Color.BLACK, Color.WHITE);
    }


    public boolean colision(int mx, int my){
        return (this.x <= mx && mx<= this.x + this.w && this.y <= my && my <= this.y + this.h);
    }

    
    public void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(5)); 

        g2d.setColor(Figure.corBorda);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));

        g2d.setColor(Figure.corPreenchimento);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
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
