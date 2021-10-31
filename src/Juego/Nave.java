
package Juego;

import Teclado.ManejarNave;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Nave extends MovNave {

    ImageIcon nave = new ImageIcon("images/cohetef.png");
    ImageIcon vidasnave = new ImageIcon("images/vidas.png");

    // Constructor para los valores de la nave
    public Nave(int Posicionx, int Posiciony, Color color, ManejarNave control) {
        super(Posicionx, Posiciony, color, control);
    }


    // Dibuja las vidas de la nave
    public void ImgNave(Graphics g) {
        vidasnave.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
    }

    // Dibuja la nave
    @Override
    public void dibujar(Graphics g) {
        nave.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());

    }

    // Obtiene el hitbox de la nave
    @Override
    public Rectangle Limites() {
        Rectangle LimNave = new Rectangle(this.TenerPosx(), this.TenerPosy(), 50, 50);
        return LimNave;
    }

    // Para mover la nave
    @Override
    public void mover() {
        // Flecha izquierda
        if (control.TenerTecla(37)) {
            Posx -= 10;
        }
        // Flecha derecha
        if (control.TenerTecla(39)) {
            Posx += 10;
        }
        
        // Para pasar de un lado a otro sobrepasando los bordes
        if (Posx > 1000) {
            Posx = -50;
        }
        if (Posx < -50) {
            Posx = 1000;
        }
    }
}
