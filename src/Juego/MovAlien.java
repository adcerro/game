
package Juego;

import java.awt.Color;

public abstract class MovAlien extends Objetos implements Movimiento{
    
    int Velx;
    int Vely;
    
    // Para construir los objetos
    public MovAlien(int Posicionx, int Posiciony, int Velocidadx, int Velocidady, Color color)
    {
        super(Posicionx, Posiciony, color);
        this.Velx = Velocidadx;
        this.Vely = Velocidady;
    
    }
    
    // Para obtener valores de los objetos
    public int TenerVelx()
    {
        return Velx;
    }
    public int TenerVely()
    {
        return Vely;
    }
    public void PonerVelx(int Velx)
    {
        this.Velx = Velx;
    }
    public void PonerVely(int Vely)
    {
        this.Vely = Vely;
    }
    @Override
    
    // Para mover los objetos
    public void mover()
    {
        this.Posx += Velx;
        this.Posy += Vely;
    }
    
}
