/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Casilla
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;

public abstract class Casilla {
    
    private int numeroCasilla;      // Número de casilla (posición en tablero)
    private int coste;              // Coste de la casilla
    
    //------------------------------------------------------------------------
    
    // Casilla de salida
    Casilla(){
        this.numeroCasilla = 0;
        this.coste = 0;
    }
    
    Casilla(int numeroCasilla, int coste){
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
    }
    
    //------------------------------------------------------------------------

    int getCoste() {
        return coste;
    }
    
    int getNumeroCasilla() {
        return numeroCasilla;
    }
    
    protected abstract TipoCasilla getTipo();
    
    protected abstract TituloPropiedad getTitulo();
    
    public void setCoste(int coste){
        this.coste = coste;
    }
    
    protected abstract boolean soyEdificable();
    
    @Override
    public String toString(){
        return "\nCasilla{" + "numeroCasilla=" + Integer.toString(numeroCasilla) +
                ", coste=" + Integer.toString(coste);
    }   
}
