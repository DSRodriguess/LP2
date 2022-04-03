package figures;
import java.awt.*;

public class Texto extends Figure {
    protected String texto;

    public Texto (int x, int y, int w, int h) {
        super (x, y,w ,h, corBorda, corBorda);
    }

    public void print () {
        System.out.format("Texto (%d,%d) na posicao (%d,%d).\n",
            this.x, this.y);
    }
    
    public boolean colision(int mx, int my){
        return (this.x <= mx && mx<= this.x + this.w && this.y <= my && my <= this.y + this.h);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.magenta);
        g2d.setFont(new Font("Ink Free",Font.BOLD,50));
        g2d.drawString("Teste de Texto", x, y);
    }

    public void tamanho(int w, int h){
        if(this.w >= 15 && this.w <= 100){
            if(this.w == 100){
                w = -5;
            }
            else if(this.w == 15){
                w = +5;
            }
            this.w += w;
        }
    }

}
