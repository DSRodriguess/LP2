package figures;
import java.awt.*;

public class Rect extends Figure {
    

    public Rect (int x, int y, int w, int h, Color corBorda, Color corPreenchimento) {
        super (x, y,w,h,corBorda,corPreenchimento);
    }

    public Rect(int x, int y, int w, int h) {
        super(x, y, w, h, Color.BLACK, Color.WHITE);
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
        
        g2d.setStroke(new BasicStroke(5)); 
        
        g2d.setColor(Figure.corBorda);
        g2d.drawRect(this.x,this.y, this.w,this.h);

        g2d.setColor(Figure.corPreenchimento);
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }

}