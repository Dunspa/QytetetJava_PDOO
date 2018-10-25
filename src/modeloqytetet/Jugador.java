/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Jugador
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;
import java.util.ArrayList;

public class Jugador {

    private boolean encarcelado;    // Si el jugador está en la cárcel
    private String nombre;          // Nombre del jugador
    private int saldo;              // Saldo del jugador (inicial 7500)
    private Sorpresa cartaLibertad; // Carta para salir de la cárcel
    private Casilla casillaActual;  // Casilla en la que está el jugador
    private ArrayList<TituloPropiedad> propiedades = new ArrayList(); 
    
    //------------------------------------------------------------------------
    
    Jugador(String nombre){
        this.nombre = nombre;
        encarcelado = false;
        saldo = 7500;
        casillaActual = new Casilla();
    }
    
    //------------------------------------------------------------------------
    
    /* private boolean cancelarHipoteca(TituloPropiedad titulo){
       
    }
    
    private boolean comprarTituloPropiedad(){
    
    }
    
    private int cuantasCasasHotelesTengo(){
    
    }
    
    private boolean deboPagarAlquiler(){
    
    }
    
    private Sorpresa devolverCartaLibertad(){
    
    }
    
    private boolean edificarCasa(TituloPropiedad titulo){
    
    }
    
    
    private boolean edificarHotel(TituloPropiedad titulo){
    
    }
    
    private void esDeMiPropiedad(TituloPropiedad titulo){
    
    }
    
    private boolean estoyEnCalleLibre(){
    
    } */
    
    private Sorpresa getCartaLibertad(){
        return cartaLibertad;
    }
    
    private Casilla getCasillaActual(){
        return casillaActual;
    }
    
    private boolean getEncarcelado(){
        return encarcelado;
    }

    private String getNombre(){
        return nombre;
    }
    
    private ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    public int getSaldo(){
        return saldo;
    }

    /*private boolean hipotecarPropiedad(TituloPropiedad titulo){
    
    }
    
    private void irACarcel(Casilla casilla){
    
    }
    
    private int modificarSaldo(int cantidad){
    
    }
    
    private int obtenerCapital(){
    
    }
    
    private ArrayList<TituloPropiedad> obtenerPropiedades(boolean hipotecada){
    
    }
    
    private void pagarAlquiler(){
    
    }
    
    private void pagarImpuesto(){
    
    }
    
    private void pagarLibertad(int cantidad){
    
    }*/

    public void setCartaLibertad(Sorpresa carta){
        this.cartaLibertad = carta;
    }

    public void setCasillaActual(Casilla casilla){
        this.casillaActual = casilla;
    }
    
    public void setEncarcelado(boolean encarcelado){
        this.encarcelado = true;
    }
    
    /*private boolean tengoCartaLibertad(){
    
    }
    
    private boolean tengoSaldo(int cantidad){
    
    }
    
    private boolean venderPropiedad(Casilla casilla){
    
    }*/
    
    @Override
    public String toString(){
        String s = "\nJugador{" + "nombre=" + nombre 
                    + ", encarcelado=" + Boolean.toString(encarcelado)
                    + ", saldo=" + Integer.toString(saldo);
        if (cartaLibertad != null)
            s = s + ", cartaLibertad=" + cartaLibertad.toString();
        
        s = s + ", casillaActual=" + casillaActual.toString() + ", propiedades=";
        for (TituloPropiedad t : propiedades){
            s = s + t.toString();
        }
        s  = s + "}";
        
        return s;
    }
}
