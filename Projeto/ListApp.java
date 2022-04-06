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
    }
}

class ListFrame extends JFrame {
    
    ArrayList<Figure> figs = new ArrayList<Figure>();
    
    Random rand = new Random();
    Point mouse = null;
    Point mousePos = null;
    Figure selectedFigure = null;

    int cor [];
    int branco[] = new int[] {255, 255, 255};
    int focusRed[] = new int[] {255, 0, 0};
    int focusBlack[] = new int[]{0,0,0};


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

                        else {
                            if(figs.get(i).getClass().getSimpleName().equals("Texto")){
                                figs.get(i).cor(focusBlack); 
                            }
                            else{
                                figs.get(i).cor(branco); 
                            }
                        }    

                        
                    }

                    if (selectedFigure != null){ 
                        figs.remove(selectedFigure);
                        figs.add(selectedFigure);
                        selectedFigure.cor(focusRed);
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
                        mouse = evt.getPoint();
                    }
                }
            );



        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {

                    mousePos = getMousePosition();

                    int x, y;

                    if (mousePos != null) {
                        x = mousePos.x;
                        y = mousePos.y;
                    }else{
                        x = rand.nextInt(350);
                        y = rand.nextInt(350);
                    }

                    cor = new int[] {rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)};

                    if (evt.getKeyChar() == 'r') {
                        selectedFigure = new Rect(branco,x,y,cor);
                        figs.add(selectedFigure);
                    } else if (evt.getKeyChar() == 'e') {
                        selectedFigure = (new Ellipse(branco,x,y,cor));
                        figs.add (selectedFigure);
                    } else if (evt.getKeyChar() == 't') {
                        selectedFigure = (new Texto("Teste texto",focusBlack,x,y));
                        figs.add(selectedFigure);
                    }else if (evt.getKeyChar() == 'l') {
                        selectedFigure = new Linha(branco,x,y,cor);
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
