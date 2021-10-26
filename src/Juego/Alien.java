
package Juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Alien extends MovAlien {

    ImageIcon alien1 = new ImageIcon("fila1.png");
    ImageIcon alien2 = new ImageIcon("fila2.png");
    ImageIcon alien3 = new ImageIcon("fila3.png");
    ImageIcon alien4 = new ImageIcon("fila4.png");
    ImageIcon alien5 = new ImageIcon("fila5.png");
    ImageIcon alien6 = new ImageIcon("fila6.png");
    ImageIcon alien7 = new ImageIcon("fila7.png");
    ImageIcon alien8 = new ImageIcon("fila8.png");
    private int tipoalien, ancho, alto;

    
    // Para construir cualquier enemigo
    public Alien(int Posicionx, int Posiciony, int Velocidadx, int Velocidady, int TipoA, Color color, int ancho, int alto) {
        super(Posicionx, Posiciony, Velocidadx, Velocidady, color);
        this.tipoalien = TipoA;
        this.ancho = ancho;
        this.alto = alto;
        this.Posy=Posiciony;
    }
    
    @Override
    // Para dibujar los aliens
    public void dibujar(Graphics g) {
        if (this.tipoalien == 0) {
            alien1.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.tipoalien == 1 && this.tipoalien != 100) {
            alien2.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.tipoalien == 2) {
            alien3.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.tipoalien == 3){
            alien4.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.tipoalien == 4){
            alien5.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.tipoalien == 5){
            alien6.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.tipoalien == 6){
            alien7.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.tipoalien == 7){
            alien8.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        }
    }
    public void DibColores(Graphics g){
        if (this.Posy == 20) {
            alien1.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==40) {
            alien2.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==60) {
            alien3.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==80){
            alien4.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==100){
            alien5.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==120){
            alien6.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==140){
            alien7.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==160){
            alien8.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==180){
            alien1.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==200){
            alien2.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==220) {
            alien3.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==240){
            alien4.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==260){
            alien5.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==280){
            alien6.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==300){
            alien7.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==320){
            alien8.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==340){
            alien1.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==360){
            alien2.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==380) {
            alien3.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==400){
            alien4.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==420){
            alien5.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==440){
            alien6.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==460){
            alien7.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        } else if (this.Posy==480){
            alien8.paintIcon(null, g, this.TenerPosx(), this.TenerPosy());
        }
    }

    // Obtiene el hitbox de los enemigos
    @Override
    public Rectangle Limites() {
        Rectangle LimAlien = new Rectangle(this.TenerPosx(), this.TenerPosy(), ancho, alto);
        return LimAlien;
    }

    // Para mover todos los enemigos
    @Override
    public void mover() {
        Posx += Velx;
    }

}
