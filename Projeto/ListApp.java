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
    Random rand = new Random();

    Point mouse = null;
    Point mousePos = null;
    Figure selectedFigure = null;

    int i, x, y, w, h, borda1, borda2, borda3, preenchimento1, preenchimento2, preenchimento3;

    ListFrame () {
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
                    selectedFigure = null;

                    for (int i = 0; i < figs.size(); i++){
                        if (figs.get(i).colision(mouse.x,mouse.y)) {
                            selectedFigure = figs.get(i); 
                        }
                        else{
                            figs.get(i).corBorda((0),(0),(0)); 
                     }        
                    }
                    

                    if (selectedFigure != null){ 
                        figs.remove(selectedFigure);
                        figs.add(selectedFigure);
                        selectedFigure.corBorda(255, 0, 0);
                    }
                    
                    repaint();
                }
            }
            );

            this.addMouseMotionListener( 
                new MouseAdapter() {
                    public void mouseDragged (MouseEvent evt) {
                        if(selectedFigure != null){
                            int dx = evt.getX() - mouse.x;
                            int dy = evt.getY() - mouse.y;
                            selectedFigure.drag(dx, dy);
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
                        selectedFigure = new Rect(x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
                        figs.add(selectedFigure);
                    } else if (evt.getKeyChar() == 'e') {
                        selectedFigure = (new Ellipse(x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3));
                        figs.add (selectedFigure);
                    } else if (evt.getKeyChar() == 't') {
                        selectedFigure = (new Texto("Projeto LP2",x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3));
                        figs.add(selectedFigure);
                    }else if (evt.getKeyChar() == 'l') {
                        selectedFigure = (new Linha (x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3));
                        figs.add(selectedFigure);
                    }

                    
                    
                    else if (selectedFigure != null){
                        if (evt.getKeyCode() == KeyEvent.VK_UP){ 
                            selectedFigure.drag(0,-5);   
                        }else if (evt.getKeyCode() == KeyEvent.VK_DOWN){ 
                            selectedFigure.drag(0,5);
                        }else if (evt.getKeyCode() == KeyEvent.VK_LEFT){ 
                            selectedFigure.drag(-5,0);
                        }else if (evt.getKeyCode() == KeyEvent.VK_RIGHT){ 
                            selectedFigure.drag(5,0);
                        }else if (evt.getKeyCode() == '=' || evt.getKeyCode() == '+'){ 
                            selectedFigure.tamanho(5,5);
                        }else if (evt.getKeyCode() == '-'){ 
                            selectedFigure.tamanho(-5,-5);
                        }else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
                            figs.remove(selectedFigure);
                            selectedFigure = null;
                        }
                        
                        else if (evt.getKeyChar() == 'p') {
                            selectedFigure.corPreenchimento(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                        }
                        else if (evt.getKeyChar() == 'b') {
                            selectedFigure.corBorda(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                        }
                        
                        if (evt.getKeyCode() == KeyEvent.VK_TAB){
                            if(selectedFigure != null){
                                if (figs.size() > 0){
                                    for(Figure fig: figs){
                                        if(fig == figs.get(i)){
                                            selectedFigure = figs.get(i);
                                            selectedFigure.corBorda(255, 0, 132);
                                        }
                                        else{
                                            fig.corBorda(0,0,0);
                                        }
                                    }
                                    
                                    figs.remove(selectedFigure);
                                    figs.add(selectedFigure);
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
            fig.paint(g);
        }
    }

    
}
