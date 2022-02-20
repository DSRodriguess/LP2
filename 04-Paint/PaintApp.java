import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect backgraud, r1, r2, r3;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);

        this.backgraud = new Rect(0, 0, 350, 350, Color.GREEN, Color.GREEN);
        this.r1 = new Rect(50,50, 100,30, Color.BLUE, Color.pink);
        this.r2 = new Rect(100,100, 100,30, Color.RED, Color.MAGENTA);
        this.r3 = new Rect(150,150, 100,30, Color.GRAY, Color.YELLOW);
        

    }

    public void paint (Graphics g) {
        super.paint(g);
        this.backgraud.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        
    }

}

class Rect {
    int x, y;
    int w, h;
    Color corPreenchimento;
    Color corBorda;

    Rect (int x, int y, int w, int h, Color corBorda, Color corPreenchimento) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corBorda = corBorda;
        this.corPreenchimento = corPreenchimento;
    }


    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {

        Graphics2D g2d = (Graphics2D) g;  

        g2d.setStroke(new BasicStroke(10)); 
        g2d.setColor(this.corBorda);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(this.corPreenchimento);
        g2d.fillRect(this.x,this.y, this.w,this.h);
   
        
    }
}