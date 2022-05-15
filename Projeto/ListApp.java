import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
        frame.setFocusTraversalKeysEnabled(false);
    }
}

class ListFrame extends JFrame {
    
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Button> buts = new ArrayList<Button>();

    Random rand = new Random();
    Point mouse = null;
    Point mousePos = null;
    Figure focused = null;
    Button focus_but;
    boolean but_clicked=false;
    int i, x, y, w, h, borda1, borda2, borda3, preenchimento1, preenchimento2, preenchimento3;

    ListFrame () {
        
        buts.add(new Button(0, new Rect(0,0,0,0,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3))); 
        buts.add(new Button(1, new Ellipse(0,0,0,0,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3)));
        buts.add(new Button(2, new Texto ("T", 0,0,0,0,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3)));
        buts.add(new Button(3, new Linha(0,0,0,0,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3)));     


        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent evt){
                    mouse = getMousePosition();
                    focused = null;

                    int x = evt.getX();
                    int y = evt.getY();

                    for (Figure fig: figs){
                        if(fig.clicked(x, y)){
                            focused = fig;
                        }
                    }
                    
                    if (focused != null){ 
                        figs.remove(focused);
                        figs.add(focused);
                    }
                    
                    repaint();
                }
            }
            );

            this.addMouseMotionListener( 
                new MouseAdapter() {
                    public void mouseDragged (MouseEvent evt) {
                        if(focused != null){
                            int dx = evt.getX() - mouse.x;
                            int dy = evt.getY() - mouse.y;
                            focused.drag(dx, dy);
                            repaint();
                        }
                        mouse = ((MouseEvent)evt).getPoint();
                    }
                }
            );



        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {

                    mousePos = getMousePosition();
                    int x = mousePos.x;
                    int y = mousePos.y;

                     int w = 50;
                     int h = 50;

                     int borda1 = rand.nextInt(255);
                     int borda2 = rand.nextInt(255);
                     int borda3 = rand.nextInt(255);
  
                     int preenchimento1 = rand.nextInt(255);
                     int preenchimento2 = rand.nextInt(255);
                     int preenchimento3 = rand.nextInt(255);


                    if (evt.getKeyChar() == 'r') {
                        focused = new Rect(x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
                        figs.add(focused);
                    } else if (evt.getKeyChar() == 'e') {
                        focused = (new Ellipse(x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3));
                        figs.add (focused);
                    } else if (evt.getKeyChar() == 't') {
                        focused = (new Texto("Projeto LP2",x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3));
                        figs.add(focused);
                    }else if (evt.getKeyChar() == 'l') {
                        focused = (new Linha (x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3));
                        figs.add(focused);
                    }

                    
                    
                    else if (focused != null){
                        if (evt.getKeyCode() == KeyEvent.VK_UP){ 
                            focused.drag(0,-5);   
                        }else if (evt.getKeyCode() == KeyEvent.VK_DOWN){ 
                            focused.drag(0,5);
                        }else if (evt.getKeyCode() == KeyEvent.VK_LEFT){ 
                            focused.drag(-5,0);
                        }else if (evt.getKeyCode() == KeyEvent.VK_RIGHT){ 
                            focused.drag(5,0);
                        }else if (evt.getKeyCode() == '=' || evt.getKeyCode() == '+'){ 
                            focused.tamanho(5,5);
                        }else if (evt.getKeyCode() == '-'){ 
                            focused.tamanho(-5,-5);
                        }else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
                            figs.remove(focused);
                            focused= null;
                        }
                        
                        else if (evt.getKeyChar() == 'p') {
                            focused.corPreenchimento(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                        }
                        else if (evt.getKeyChar() == 'b') {
                            focused.corBorda(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                        }
                        
                        if (evt.getKeyCode() == KeyEvent.VK_TAB){
                            if(focused != null){
                                if (figs.size() > 0){
                                    for(Figure fig: figs){
                                        if(fig == figs.get(i)){
                                            focused = figs.get(i);
                                            focused.corBorda(255, 0, 132);
                                        }
                                        else{
                                            fig.corBorda(0,0,0);
                                        }
                                    }
                                    
                                    figs.remove(focused);
                                    figs.add(focused);
                                    i++;
                               
                                    if (i >= figs.size()){
                                    i = 0;
                                    }
                                }
                            }
                        }

                        
                    }
                        repaint();
                }
            }
        );

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.CYAN);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g, fig == focused);
        }
    }

    
}