
package Juego;

import Teclado.ManejarNave;
import java.awt.Color;

public abstract class MovNave extends Objetos implements Movimiento {
    
    ManejarNave control;
    
    // Constructor de la nave
    public MovNave(int Posicionx, int Posiciony, Color color, ManejarNave control)
    {
        super(Posicionx, Posiciony, color);
        this.control = control;
    }
}
