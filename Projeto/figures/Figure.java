package figures;

import java.awt.Graphics;
import java.io.Serializable;
import ivisible.IVisible;

public abstract class Figure implements IVisible, Serializable{
    public int x;
    public int y;
    public int w = 50;
    public int h = 50;
    protected int borda1, borda2, borda3, preenchimento1, preenchimento2, preenchimento3;


    public Figure (int x, int y, int borda1, int borda2, int borda3, int preenchimento1, int preenchimento2, int preenchimento3) {
        this.x = x;
        this.y = y;
        this.borda1 = borda1;
        this.borda2 = borda2;
        this.borda3 = borda3;
        this.preenchimento1 = preenchimento1;
        this.preenchimento2 = preenchimento2;
        this.preenchimento3 = preenchimento3;
    }

    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void corBorda(int a, int b, int c){
        this.borda1 = a;
        this.borda2 = b;
        this.borda3 = c;
    }

    public void corPreenchimento(int a, int b, int c){
        this.preenchimento1 = a;
        this.preenchimento2 = b;
        this.preenchimento3 = c;
    }


    public boolean clicked(int mx, int my){
        return (this.x <= mx && mx<= this.x + this.w && this.y <= my && my <= this.y + this.h);
    }

    public void tamanho (int rw){
            if(w >= 200){
                w = 198;
                h = 198;
            }
            else if(this.w == 30){
                w = 32;
                h = 32;
            }
            
            w += rw;
            h += rw;
        }

    public abstract void paint (Graphics g, boolean focused);
}