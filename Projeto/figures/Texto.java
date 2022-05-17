package figures;
import java.awt.*;

public class Texto extends Figure {

    protected String texto;

    public Texto (String texto, int x, int y, int w, int h, int borda1, int borda2, int borda3, int preenchimento1, int preenchimento2, int preenchimento3) {
        super ( x, y, borda1, borda2, borda3, preenchimento1, preenchimento2,preenchimento3);
        this.w = w;
        this.h = h;
        this.texto = texto;
        
    }

    public void print () {
        System.out.format("Texto de conteúdo %s na posição %d, %d.\n",
            this.texto, this.x, this.y);
    }
    
    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(this.preenchimento1,this.preenchimento2,this.preenchimento3));
        g2d.setFont(new Font("arial", 1, this.w));
        g2d.drawString(this.texto, this.x, this.y);

        if (focused){
            g2d.setColor(Color.red);
            g2d.drawString(this.texto, this.x, this.y);
        }
    }

}