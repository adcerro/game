
package Teclado;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ManejarNave implements KeyListener
{
    private boolean[] NumTecla; 
    
    public ManejarNave()
    {
        NumTecla = new boolean[256]; 
    }
    
    public boolean TenerTecla(int Codigo)
    {
        if(Codigo < 0 || Codigo > 255)
        {
            return false; 
        }
        else
        {
            return NumTecla[Codigo]; 
        }
    }
    
    public void ReiniciarControles()
    {
        NumTecla = new boolean[256]; 
    }
    
    //Procedimientos propios de keylistener 
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        NumTecla[ke.getKeyCode()] = true; 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        NumTecla[ke.getKeyCode()] = false; 
    }
    
}
