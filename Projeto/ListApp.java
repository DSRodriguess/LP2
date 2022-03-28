import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();
    Point PrevPt;

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        
        this.addMouseListener (
            new MouseAdapter() {

                public int x, y;
                public int size = 50;

                public void mousePressed (MouseEvent evt) {
                    selectedFigure = null;
                    if (evt.getButton() == 1){
                        Point mousePointPosition = new Point(evt.getX(),evt.getY());
                        for (Figure fig:figs){
                            System.out.println("chamo quando clicou");
                            System.out.println(mousePointPosition);
                            if ((mousePointPosition.x >= this.x) && (mousePointPosition.x <= this.x + this.size) && (mousePointPosition.y >= this.y) && (mousePointPosition.y <= this.y + this.size) == true){
                                selectedFigure = fig;
                            }
                        }

                        if (selectedFigure != null) {
                            figs.remove(selectedFigure);
                            figs.add(selectedFigure);
                        }
                    }
                }
            }

        );

        this.addMouseMotionListener (
            new MouseAdapter() {
                public void mouseMoved(MouseEvent evt) {
                    mousePointPosition.x = evt.getX();
                    mousePointPosition.y = evt.getY();
                }
            }
        );

        this.addMouseMotionListener (
            new MouseAdapter() {
                public void mouseDragged (MouseEvent event) {

                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(350);
                    int y = rand.nextInt(350);
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);

                    if (evt.getKeyChar() == 'r') {
                        Rect r = new Rect(x,y, w,h,Color.green,Color.black);
                        figs.add(r);
                    } else if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(x,y, w,h,Color.blue,Color.pink));
                    } else if (evt.getKeyChar() == 't') {
                        figs.add(new Texto(x,y));
                    }else if (evt.getKeyChar() == 'l') {
                        figs.add(new Linha(x,y,w,h));
                    }
                    repaint();
                }
            }
        );

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);

    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}