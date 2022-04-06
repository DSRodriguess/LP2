package figures;
import java.awt.*;

public class Texto extends Figure {
    protected String texto;

    public Texto (String texto, int [] cor, int x, int y) {
        super (cor, x, y);
        this.texto = texto;
        
    }

    public void print () {
        System.out.format("Texto de conteúdo %s na posição %d, %d.\n",
            this.texto, this.x, this.y);
    }
    
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(this.cor[1], this.cor[1], this.cor[1]));
        g2d.setFont(new Font("Ink Free", 1, this.w));
        g2d.drawString(this.texto, this.x, this.y);
    }

}
