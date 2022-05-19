import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.lang.String;
import java.io.*;

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

    Button focus_but = null;
    boolean but_clicked=false;

    int w = 50;
    int h = 50;

    int i, x, y, borda1, borda2, borda3, preenchimento1, preenchimento2, preenchimento3;

    ListFrame () {
        
        buts.add(new Button(0, new Rect(0, 0, 0,0, 0,0,0, 0,0,0)));
        buts.add(new Button(1, new Ellipse(24,24, 0,0, 0,0,0, 0,0,0)));
        buts.add(new Button(2, new Linha(24,24, 0,0, 0,0,0, 0,0,0)));
        buts.add(new Button(3, new Texto("T",40,225, 0,0, 0,0, 0,0,0,0)));

        try{
            FileInputStream f=new FileInputStream("proj.bin");
            ObjectInputStream o=new ObjectInputStream(f);
            this.figs=(ArrayList<Figure>) o.readObject();
            o.close();
          } 
          
          catch (Exception x) {
              System.out.println("ERRO ao abrir o arquivo");
          }
          
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    try {
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    }  
                    
                    catch (Exception x) {
                        System.out.println("ERRO ao abrir o arquivo");
                    }
                    
                    System.exit(0);
                }
            }
        );

        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent evt){
                    mouse = getMousePosition();
                    focused = null;
                    focus_but = null;
                    but_clicked = false;

                    int borda1 = rand.nextInt(255);
                    int borda2 = rand.nextInt(255);
                    int borda3 = rand.nextInt(255);
 
                    int preenchimento1 = rand.nextInt(255);
                    int preenchimento2 = rand.nextInt(255);
                    int preenchimento3 = rand.nextInt(255);

                    int x = evt.getX();
                    int y = evt.getY();

                    for (Figure fig: figs){
                        if(fig.clicked(x, y)){
                            focused = fig;
                            focus_but = null;
                            but_clicked = false;
                        }
                    }
                    
                    if (focused != null){ 
                        figs.remove(focused);
                        figs.add(focused);
                    }

                    for (Button but:buts) {
                        if(but.clicked(x,y)){ 
                            focus_but = but;
                            focused = null;
                            but_clicked = true;
                        }
                    }
                    
                    repaint();

                    if (focus_but != null){
                        if (focus_but == buts.get(0)) { 
                            Figure fig = new Rect(mouse.x,mouse.y, w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
                            figs.add(fig);
                            focused = fig;                      
                        }
                       else if (focus_but == buts.get(1)) {
                            Figure fig = new Ellipse(mouse.x,mouse.y, w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
                            figs.add(fig);
                            focused = fig;   
                        }
                        else if (focus_but == buts.get(2)) {
                            Figure fig = new Linha(mouse.x,mouse.y, w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
                            figs.add(fig);
                            focused = fig;   

                        }
                        else if (focus_but == buts.get(3)) {
                            Figure fig = new Texto("Projeto LP2",mouse.x,mouse.y, w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
                            figs.add(fig);
                            focused = fig;   
                        } 
                     }

                     repaint();

                }
            }
        );

        this.addMouseMotionListener (
            new MouseAdapter() {
                public void mouseMoved(MouseEvent evt) {
                    x = evt.getX();
                    y = evt.getY();
                }

   
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
        this.setSize(450, 450);
        this.getContentPane().setBackground(Color.CYAN);
    }



    public void paint (Graphics g) {
        super.paint(g);

        for (Button but: this.buts){
            but.paint(g, but == focus_but);
        }

        for (Figure fig: this.figs) {
            fig.paint(g, fig == focused);
        }
    }

    
}