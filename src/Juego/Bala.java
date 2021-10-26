
package Juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bala extends MovAlien {

    
    int diametro;

    // Para generar la bala
    public Bala(int Posicionx, int Posiciony, int Dia, Color color) {
        super(Posicionx, Posiciony, 0, 0, color);
        this.diametro = Dia;
    }

    // Obtiene el diametro de la bala
    public int TenerDia() {
        return diametro;
    }

    // Para dibujar la bala
    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillRect(this.TenerPosx(), this.TenerPosy(), 7, 15);

    }

    @Override
    public Rectangle Limites() {
        Rectangle LimBala = new Rectangle(Posx, Posy, 7, 15);
        return LimBala;
    }
}
