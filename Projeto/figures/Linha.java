package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.Color;

public class Linha extends Figure {
    private int w, h;

    public Linha (int x, int y, int w, int h, int borda1, int borda2, int borda3, int preenchimento1, int preenchimento2, int preenchimento3) {
        super(x, y, borda1, borda2, borda3, preenchimento1, preenchimento2, preenchimento3);
       
        this.w = 50;
        this.h = 50;
    }
    
    public void print () {
        System.out.format("Linha de tamanho  (%d, %d) na posição: (%d, %d).\n", 
            this.w, this.h, this.x, this.y);
    }

    public boolean colision (int mx, int my) {
        return (this.x <= mx && mx<= this.x + this.w && this.y - 20 <= my && my <= this.y + 20);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(new Color(this.borda1,this.borda2,this.borda3));
        g2d.drawLine(this.x, this.y, this.x + this.w, this.y);
    }

    public void tamanho (int lw, int lh) {
        if (this.w >= 30 && this.w <= 300){
            if(this.w == 300){
                lw = -6;
                lh = -6;
            }
            else if (this.w == 30 ){
                lw = +6;
                lh = +6;
            }
        }

        this.w += lw;
        this.h += lh;
    }
}