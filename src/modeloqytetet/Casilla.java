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
    
    //------------------------------------------------------------------------
    
    /* Constructores (Depende de si la casilla es una calle o no) */
    // Por defecto
    Casilla(){
        this.numeroCasilla = 0;
        this.coste = 0;
        this.tipo = TipoCasilla.SALIDA;
        this.titulo = null;  
    }
    
    // Para calles
    Casilla(int numeroCasilla, TituloPropiedad titulo){
        this.numeroCasilla = numeroCasilla;
        this.coste = titulo.getPrecioCompra();
        this.tipo = TipoCasilla.CALLE;
        this.setTitulo(titulo);
    }
    
    // Para cualquier otra casilla
    Casilla(int numeroCasilla, int coste, TipoCasilla tipo){ 
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.tipo = tipo;
        this.titulo = null;   
    }
    
    //------------------------------------------------------------------------
    
    /*TituloPropiedad asignarPropietario(Jugador jugador){
        
    }*/

    int getCoste() {
        return coste;
    }
    
    int getNumeroCasilla() {
        return numeroCasilla;
    }

    TipoCasilla getTipo() {
        return tipo;
    }

    TituloPropiedad getTitulo() {
        return titulo;
    }
    
    /*int pagarAlquiler(){
        
    }
    
    boolean propietarioEncarcelado(){
        
    }*/

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    
    /*boolean soyEdificable(){
        
    }
    
    boolean tengoPropietario(){
        
    }*/
    
    public String toString(){
        return "\nCasilla{" + "numeroCasilla=" + Integer.toString(numeroCasilla) +
                ", coste=" + Integer.toString(coste) +
                ", tipo=" + tipo + ", titulo=" + titulo + "}";
    }   
}
