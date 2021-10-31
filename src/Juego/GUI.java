 
package Juego;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GUI extends JFrame
{
    private ConfigJ juegof;
    public GUI()
    {
        super("Laboratorio Final");
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
     
        juegof = new ConfigJ();

        this.getContentPane().add(juegof); 
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("images/cohetef.png").getImage());
        
        //Comienza el juego
        juegof.comenzar();
        InputStream in;
        try{
            in=new FileInputStream(new File("src\\wav\\audio.wav"));
            AudioStream ad= new AudioStream(in);
            AudioPlayer.player.start(ad);
        }catch(Exception e){
            System.out.println(e);
        }  
    }
    
    public static void main(String[] args) 
    {
         java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
        
    }
}
