package figures;
import java.awt.*;

public abstract class Figure {
    protected int x, y;
    protected int w = 50, h = 50;
    protected int[] cor;


    public Figure (int[] cor,int x, int y) {
        this.cor = cor;
        this.x = x;
        this.y = y;
    }

    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void cor(int [] cor){
        this.cor = cor;
    }

    public boolean colision(int mx, int my){
        return (this.x <= mx && mx<= this.x + this.w && this.y <= my && my <= this.y + this.h);
    }

    public void tamanho (int w, int h){
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

    public abstract void paint (Graphics g);
}