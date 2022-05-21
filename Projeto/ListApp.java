import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.String;
import java.io.*;
import java.awt.event.MouseEvent;
import figures.*;
import java.util.ArrayList;
import java.util.Random;

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

    Rect aux = new Rect (0, 0, 5, 5, 255, 0, 0, 255, 0, 0);

    Point mouse = null;
    Point mousePos = null; 

    Figure focused = null;
    Figure focusedAux = null;

    Button focus_but = null;

    boolean but_clicked= false;
    boolean auxB = false;
    boolean auxKey = false;

    int w = 50;
    int h = 50;
    int i, x, y, borda1, borda2, borda3, preenchimento1, preenchimento2, preenchimento3,posX = 0, posY = 0, indice;

    ListFrame (){
        
        buts.add(new Button(0, new Rect(0, 0, 0,0, 0,0,0, 0,0,0)));
        buts.add(new Button(1, new Ellipse(24,24, 0,0, 0,0,0, 0,0,0)));
        buts.add(new Button(2, new Linha(24,24, 0,0, 0,0,0, 0,0,0)));
        buts.add(new Button(3, new Texto("T",0,0, 0,0, 0,0, 0,0,0,0)));       
        buts.add(new Button(4, new ButaoTx("Limpar",0,0, 0,0, 0,0,0, 0,0,0)));
        buts.add(new Button(5, new ButaoTx("Sair",0,0, 0,0, 0,0,0, 0,0,0)));
        


        try{
            FileInputStream f=new FileInputStream("proj.bin");
            ObjectInputStream o=new ObjectInputStream(f);
            figs = (ArrayList<Figure>) o.readObject();
            o.close();
        } 
          
        catch (Exception x){
            System.out.println("ERRO ao abrir o arquivo");
        }
          
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e){
                    try {
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    }  
                    
                    catch (Exception x){
                        System.out.println("ERRO ao abrir o arquivo");
                    }                    
                    System.exit(0);
                }
            }
        );

        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent evt){
                    focused = null;
                    auxKey = false;
                    mouse = evt.getPoint();

                    if(auxB && focus_but != null){
                        if(!(mouse.x < 60 && mouse.y < 680) && focus_but.idx != 9 && focus_but.idx != 10 && focus_but.idx != 12){
                            figureBut(focus_but.idx, mouse.x, mouse.y);
                            auxB = false;
                            focus_but = null;
                        }
                    }

                    for (Button but:buts) {
                        if(but.clicked(mouse.x,mouse.y)){ 
                            focus_but = but;
                            auxB = true;

                            if(but.idx > 4 ){
                                figureBut(focus_but.idx, mouse.x, mouse.y);
                            }
                        }
                    }

                    for (int i = 0; i < figs.size(); i++){
                        if (figs.get(i).clicked(mouse.x,mouse.y)) {
                            focused = figs.get(i); 
                            focusedAux = focused;    
                        }                       
                        else if(aux.clicked(mouse.x, mouse.y)){
                            focused = figs.get(i);  
                            focusedAux = focused;    
                            auxKey = true;                          
                        }
                        else{
                            focusedAux = focused;
                            auxKey = false;
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

        this.addMouseMotionListener (
            new MouseAdapter(){               
                public void mouseDragged (MouseEvent evt) {
                    if(auxKey){
                        focused.tamanho(evt.getX() - mouse.x);
                    }
                    else{
                        if(focused != null){
                            int dx = evt.getX() - mouse.x;
                            int dy = evt.getY() - mouse.y;
                            focused.drag(dx, dy);
                        }
                    }
                        mouse = evt.getPoint();
                        repaint();               
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


                    if (evt.getKeyChar() == 'r'){
                        focused = new Rect(x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
                        figs.add(focused);
                    } else if (evt.getKeyChar() == 'e'){
                        focused = (new Ellipse(x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3));
                        figs.add (focused);
                    } else if (evt.getKeyChar() == 't'){
                        focused = (new Texto("Projeto LP2",x,y,w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3));
                        figs.add(focused);
                    } else if (evt.getKeyChar() == 'l'){
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
                            focused.tamanho(5);
                        }else if (evt.getKeyCode() == '-'){ 
                            focused.tamanho(-5);                       
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

    public void figureBut(int idx, int x, int y){
        if (idx == 0){
            Figure fig = new Rect(x,y, w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
            figs.add(fig);
            focused = fig;   
            indice = idx;
        }
        else if (idx == 1){
            Figure fig = new Ellipse(x,y, w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
            figs.add(fig);
            focused = fig;  
        }
        else if (idx == 2){
            Figure fig = new Linha(x,y, w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
            figs.add(fig);
            focused = fig;   
        }
        else if (idx == 3){
            Figure fig = new Texto("Projeto LP2",x,y, w,h,borda1,borda2,borda3,preenchimento1,preenchimento2,preenchimento3);
            figs.add(fig);
            focused = fig;  
        }
        else if (idx == 4){
            int i = JOptionPane.showConfirmDialog(null, "Deseja limpar a tela ?", "limpar",
            JOptionPane.YES_NO_OPTION);
            
            if (i == JOptionPane.YES_OPTION){
                figs.clear();
            }
            else if(i == JOptionPane.NO_OPTION){
                System.out.close();

            }
        }

        else if (idx == 5){
            int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do programa ?", "sair",
            JOptionPane.YES_NO_OPTION);
            
            if (i == JOptionPane.YES_OPTION){
                System.exit(0);
            }
            else if(i == JOptionPane.NO_OPTION){
                System.out.close();
            }
        }

    }

    public void paint (Graphics g) {
        super.paint(g);

        for (Button but: this.buts){
            but.paint(g, but == focus_but);
        }

        for (Figure fig: this.figs) {
            fig.paint(g, fig == focused);
        }

        if(focused != null){
            aux.x = focused.x + (focused.w + 3);
            aux.y = focused.y + (focused.h + 3);
            aux.paint(g, true);
        }

    }
    
}