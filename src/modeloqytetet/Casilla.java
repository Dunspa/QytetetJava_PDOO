/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Casilla
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;

public class Casilla {
    
    private int numeroCasilla;      // Número de casilla (posición en tablero)
    private int coste;              // Coste de la casilla
    private TipoCasilla tipo;       // Tipo de la casilla
    private TituloPropiedad titulo; // Asocia la casilla a su título
    
    /* Constructores (Depende de si la casilla es una calle o no) */
    
    // Para calles
    public Casilla(int numeroCasilla, TituloPropiedad titulo){
    
        this.numeroCasilla = numeroCasilla;
        this.coste = titulo.getPrecioCompra();
        this.tipo = TipoCasilla.CALLE;
        this.setTitulo(titulo);
        
        
    }
    
    // Para cualquier otra casilla
    public Casilla(int numeroCasilla, int coste, TipoCasilla tipo){
    
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.tipo = tipo;
        this.titulo = null;
    
    }
    
    /* Consultores */

    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    public int getCoste() {
        return coste;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }

    public TituloPropiedad getTitulo() {
        return titulo;
    }
    
    /* Modificadores */

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    
    public String toString(){
    
        return "Casilla{" + "numeroCasilla=" + Integer.toString(numeroCasilla) +
                ", coste=" + Integer.toString(coste) +
                ", tipo=" + tipo + ", titulo=" + titulo + "}";
    
    }   
}
