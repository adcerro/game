
package Juego;

import Teclado.ManejarNave;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import sun.audio.AudioPlayer;

public class ConfigJ extends JPanel {
    
    BaseDeDatos db = new BaseDeDatos();
    Connection conectar;
    String nombre;
    private Timer temporizador;
    private ManejarNave controlesj; 
    private final int AnchoJ = 1000;
    private final int AltoJ = 800;
    private final int FPS = 200;   
    private int Score = 0;
    private int Nvidas = 3;
    private int HighScore;
    File Hs = new File("Highscore.txt");   
    private Nave nave;
    private Nave vida;
    private Alien alien;
    private Bala bala;   
    private boolean disparar = true;
    private boolean pegar = false;
    private ArrayList<Nave> cantvida = new ArrayList();
    private ArrayList<Alien> cantaliens = new ArrayList();
    private ImageIcon fondo = new ImageIcon("images/opcion2.2.gif");
    Font font = new Font("Courier", Font.BOLD,24);
  
    // Estado inicial del juego
    public final void ConfiguracionJ() {
        String directory = "LABDB.accdb"; 
        conectar= db.conexion(directory);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    alien = new Alien((20 + (i * 100)), (20 + (j * 60)), 5, 0, j, null, 60, 44); 
                    cantaliens.add(alien);
                }
            }
      
        ImageIcon inicio = new ImageIcon("images/startf.png");
        JOptionPane.showMessageDialog(null,"","|Invasores del espacio|",JOptionPane.INFORMATION_MESSAGE,inicio);   
        nave = new Nave(450, 710, null, controlesj);
        //Contador de vidas para la nave
        for (int j = 0; j < Nvidas; j++) {
            vida = new Nave(95 + (j * 20), 8, Color.WHITE, null);
            cantvida.add(vida);
        }
    }
    
// Dibujar objetos del juego
    @Override
    public void paint(Graphics g) {

        fondo.paintIcon(null, g, 0, -150);        
        nave.dibujar(g);

        try {
            for (int i = 0; i < cantaliens.size(); i++) {
                cantaliens.get(i).dibujar(g);
                cantaliens.get(i).DibColores(g);
            }

        } catch (IndexOutOfBoundsException e) {
        }

        // Dispara la bala al undir espacio
        if (controlesj.TenerTecla(32)) {
            if (disparar) {
                bala = new Bala(nave.TenerPosx() + 22, nave.TenerPosy() - 20, 0, Color.YELLOW);
                disparar = false;
            }
        }
        // Verifica que se pueda dibujar una bala (que el frame no contenga una bala)
        if (bala != null) {
            bala.dibujar(g);
        }
        // Muestra el score, el highscore y las vidas
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + Score, 260, 20);
        g.setColor(Color.WHITE);
        g.drawString("Vidas:", 11, 20);
        for (int index = 0; index < cantvida.size(); index++) {
            cantvida.get(index).ImgNave(g);
        }
        g.setColor(Color.WHITE);
        g.drawString("Highscore: " + HighScore, 440, 20);
    }
    
// Actualiza el estado del juego
    
    public void Actualizar(int fps) {

        // Permite moverse a los lados
        nave.mover();

        // Actualiza el highscore
        try {
            Scanner leerscore = new Scanner(Hs);
            while (leerscore.hasNextInt()) {
                String scorel = leerscore.nextLine();
                Scanner scanscore = new Scanner(scorel);
                HighScore = scanscore.nextInt();
            }
        } catch (FileNotFoundException e) {
        }
        // Actualiza el archivo de highscore si este se supera
        try {
            if (Score > HighScore) {
                String valorscore = Integer.toString(Score);
                PrintWriter pw = new PrintWriter(new FileOutputStream(Hs, false));
                pw.write(valorscore);
                pw.close();
            }
        } catch (FileNotFoundException e) {
        }

        // Mueve los enemigos y cambia de dirección cuando tocan un borde
        if ((cantaliens.get(cantaliens.size() - 1).TenerPosx() + cantaliens.get(cantaliens.size() - 1).TenerVelx()) > 960 || (cantaliens.get(0).TenerPosx() + cantaliens.get(0).TenerVelx()) < 0) {
            for (int i = 0; i < cantaliens.size(); i++) {
                cantaliens.get(i).PonerVelx(cantaliens.get(i).TenerVelx() * -1);
                cantaliens.get(i).Ponery(cantaliens.get(i).TenerPosy() + 10);
                
            }
        } else {
            for (int i = 0; i < cantaliens.size(); i++) {
                cantaliens.get(i).mover();
                if(cantaliens.get(i).TenerPosy()==800)
                {
                    cantaliens.remove(i);
                    if(cantvida.size()>0)
                    {
                    cantvida.remove(cantvida.size() - 1);
                    }
                    else
                    {
                        String nombre =JOptionPane.showInputDialog(null,"Digite el nombre del jugador para guardar su puntaje");
                        String anadir_jugador ="INSERT INTO SCORE (NOMBRE,SCORE)  VALUES('"+nombre+"','"+Score+"')";
                        db.add_edit_delete(conectar, anadir_jugador);
                        int respuesta = JOptionPane.showConfirmDialog(null, "Quieres jugar de nuevo?", "Perdiste con " + Score + " puntos", 0);
                        if (respuesta == 0) {
                            cantvida.clear();
                            cantaliens.clear();
                            Score = 0;
                            Nvidas = 5;
                            disparar = true;
                            ConfiguracionJ();
                        }
                        if (respuesta == 1) {
                            
                            String titulos [] ={"NOMBRE","PUNTAJE"};
                            DefaultTableModel B2= new DefaultTableModel (null,titulos);
                            String datos [] = new String[2];
                            String query_buscar_pacientest="SELECT NOMBRE,SCORE FROM SCORE ORDER BY SCORE DESC";
                            B2= db.SEARCH (conectar, query_buscar_pacientest, datos, B2);
                            JTable table = new JTable();
                            table.setModel(B2);
                            JOptionPane.showMessageDialog(null, new JScrollPane(table));
                            System.exit(0);
                        }
                    }
                }
            }
        }

        // Para mover la bala
        if (bala != null) {
            bala.Ponery(bala.TenerPosy() - 15);
            if (bala.TenerPosy() < 0) {
                disparar = true;
            }

            // Verifica si la bala impacta un enemigo
            for (int i = 0; i < cantaliens.size(); i++) {
                if (bala.Chocar(cantaliens.get(i))) {
                    bala = new Bala(0, 0, 0, null);
                    disparar = true;
                    // Actualiza el score
                        Score += 10;
                        cantaliens.remove(i);

                }
            }
            
        }

        // Actualiza las vidas cuando los aliens tocan la nave
         for (int i = 0; i < cantaliens.size(); i++) {
            if (cantaliens.get(i).Chocar(nave)) {
                cantaliens.remove(i);
                cantvida.remove(cantvida.size() - 1);
            }
            else if (cantvida.isEmpty()) {
            // Opcion para jugar otra vez o salirse
            String nombre =JOptionPane.showInputDialog(null,"Digite el nombre del jugador para guardar su puntaje");
            String anadir_jugador ="INSERT INTO SCORE (NOMBRE,SCORE)  VALUES('"+nombre+"','"+Score+"')";
            db.add_edit_delete(conectar, anadir_jugador);
            int answer = JOptionPane.showConfirmDialog(null, "Quieres jugar de nuevo?", "Perdiste con " + Score + " puntos", 0);
            // Si juega otra vez, se reinician todos los elementos
            if (answer == 0) {
                cantvida.clear();
                cantaliens.clear();
                Score = 0;
                Nvidas = 5;
                disparar = true;
                ConfiguracionJ();
            }
            // Muestra la tabla de highscore si decide no jugar.
            if (answer == 1) {
                String titulos [] ={"NOMBRE","PUNTAJE"};
                DefaultTableModel B2= new DefaultTableModel (null,titulos);
                String datos [] = new String[2];
                String query_buscar_pacientest="SELECT NOMBRE,SCORE FROM SCORE ORDER BY SCORE DESC";
                B2= db.SEARCH (conectar, query_buscar_pacientest, datos, B2);
                JTable table = new JTable();
                table.setModel(B2);
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
                System.exit(0);
            }
        }
        }

        if (cantaliens.isEmpty()) {
            String nombre =JOptionPane.showInputDialog(null,"Digite el nombre del jugador para guardar su puntaje");
            String anadir_jugador ="INSERT INTO SCORE (NOMBRE,SCORE)  VALUES('"+nombre+"','"+Score+"')";
            db.add_edit_delete(conectar, anadir_jugador);
            int answer = JOptionPane.showConfirmDialog(null, "Quieres jugar de nuevo?", "Ganaste con " + Score + " puntos", 0);
            if (answer == 0) {
                cantvida.clear();
                cantaliens.clear();
                Score = 0;
                Nvidas = 5;
                disparar = true;
                ConfiguracionJ();
            }
            if (answer == 1) {
                String titulos [] ={"NOMBRE","PUNTAJE"};
                DefaultTableModel B2= new DefaultTableModel (null,titulos);
                String datos [] = new String[2];
                String query_buscar_pacientest="SELECT NOMBRE,SCORE FROM SCORE ORDER BY SCORE DESC";
                B2= db.SEARCH (conectar, query_buscar_pacientest, datos, B2);
                JTable table = new JTable();
                table.setModel(B2);
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
                System.exit(0);
            }
        }
    }

// Panel de juego    
    
    public ConfigJ() {
        // Tamaño del panel
        this.setSize(AnchoJ, AltoJ);
        this.setPreferredSize(new Dimension(AnchoJ, AltoJ));
        this.setBackground(Color.BLACK);
        controlesj = new ManejarNave();
        this.addKeyListener(controlesj);

        // Iniciar los campos
        this.ConfiguracionJ();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    
    
     
 
    public void comenzar() {
        // Temporizador que se repite cada 5ms
        temporizador = new Timer(1000 / FPS, new ActionListener() {
            
            private int fps = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualiza el estado del juego y vuelve a dibujar el frame
                Actualizar(fps++);
                repaint();
            }
        });
        Timer cuandodisparar = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pegar = false;
            }
        });

        temporizador.setRepeats(true);
        temporizador.start();
        cuandodisparar.setRepeats(true);
        cuandodisparar.start();
    }

}
