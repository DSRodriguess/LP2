package figures;
import java.awt.*;

public class ButaoTx extends Figure{

    protected String texto;

    public ButaoTx (String texto, int x, int y, int w, int h, int borda1, int borda2, int borda3, int preenchimento1, int preenchimento2, int preenchimento3) {
        super ( x, y, borda1, borda2, borda3, preenchimento1, preenchimento2,preenchimento3);
        this.w = w;
        this.h = h;
        this.texto = texto;        
    }

    public void paint (Graphics g, boolean focused){

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("arial", 1, this.w/3));
        g2d.setColor(new Color(this.borda1,this.borda2,this.borda3));
        g2d.drawString(this.texto, this.x, this.y);
    }
    
    public void resize(int x, int y, int tipo) {

    }
}
