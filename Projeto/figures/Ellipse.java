package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.Color;

public class Ellipse extends Figure{
    public Ellipse (int x, int y, int w, int h, int borda1, int borda2, int borda3, int preenchimento1, int preenchimento2, int preenchimento3){
        super ( x, y, borda1, borda2, borda3, preenchimento1, preenchimento2,preenchimento3);
        this.w = w;
        this.h = h;
    }
    
 

    public void print(){
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
        this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g, boolean focused){

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.preenchimento1,this.preenchimento2,this.preenchimento3));
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setStroke(new BasicStroke(2)); 
        g2d.setColor(new Color(this.borda1,this.borda2,this.borda3));
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));

        if (focused){
        g2d.setColor(Color.red);
        g2d.drawOval(this.x, this.y, this.w,this.h);
        }
    }
}