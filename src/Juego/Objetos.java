
package Juego;

import java.awt.Color;
import java.awt.Rectangle;

public abstract class Objetos implements Dibujar {

    int Posx;
    int Posy;
    Color color;
    boolean Choque;
    
    public Objetos(){};
    
    // Para construir los objetos
    public Objetos(int Posicionx, int Posiciony, Color color) {
        this.Posx = Posicionx;
        this.Posy = Posiciony;
        this.color = color;
    }

    public abstract Rectangle Limites();

    //obtener posicion en X y Y
    public int TenerPosx() {
        return Posx;
    }
    public int TenerPosy() {
        return Posy;
    }
    
    //obtener el color
    public Color Tenercolor() {
        return color;
    }

    // Establece la posicion en x 
    public void Ponerx(int Posicionx) {
        this.Posx = Posicionx;
    }

    // Establece la posicion en Y
    public void Ponery(int Posiciony) {
        this.Posy = Posiciony;
    }

    // Establece el color
    public void Ponercolor(Color color) {
        this.color = color;
    }

    // Comprueba dos objetos colisionan
    public boolean Chocar(Objetos other) {
        Choque = other.Limites().intersects(this.Limites());
        return Choque;
    }
}
