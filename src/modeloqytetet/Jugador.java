/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Jugador
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;
import java.util.ArrayList;

public class Jugador implements Comparable {

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
    
    boolean cancelarHipoteca(TituloPropiedad titulo){
        int costeCancelar = titulo.calcularCosteCancelar();
        boolean puedeCancelar = tengoSaldo(costeCancelar);
        if (puedeCancelar){
            modificarSaldo(-costeCancelar);
            titulo.cancelarHipoteca();
        }
        
        return puedeCancelar;
    }
    
    boolean comprarTituloPropiedad(){
        boolean comprado = false;
        int costeCompra = casillaActual.getCoste();
        if (costeCompra < saldo){
            TituloPropiedad titulo = casillaActual.asignarPropietario(this);
            propiedades.add(titulo);
            modificarSaldo(-costeCompra);
            comprado = true;
        }
        
        return comprado;
    }
    
    int cuantasCasasHotelesTengo(){
        int casashoteles = 0;
        
        for (TituloPropiedad t : propiedades){
            casashoteles += (t.getNumCasas() + t.getNumHoteles());
        }
        
        return casashoteles;
    }
    
    boolean deboPagarAlquiler(){
        boolean tienePropietario = false, estaEncarcelado = false, estaHipotecada = false;
        TituloPropiedad titulo = casillaActual.getTitulo();
        boolean esDeMiPropiedad = esDeMiPropiedad(titulo);
        
        if (!esDeMiPropiedad){
            tienePropietario = titulo.tengoPropietario();
        }
        
        if (!esDeMiPropiedad && tienePropietario){
            estaEncarcelado = titulo.propietarioEncarcelado();
        }
        
        if (!esDeMiPropiedad && tienePropietario && !estaEncarcelado){
            estaHipotecada = titulo.isHipotecada();
        }
        
        return (!esDeMiPropiedad && tienePropietario && !estaEncarcelado && !estaHipotecada);
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa copiaCartaLibertad = new Sorpresa(cartaLibertad);
        cartaLibertad = null;
        
        return copiaCartaLibertad;
    }
    
    boolean edificarCasa(TituloPropiedad titulo){
        boolean edificada = false;
        int numCasas = titulo.getNumCasas();
        
        if (numCasas < 4){
            int costeEdificarCasa = titulo.getPrecioEdificar();
            boolean tengoSaldo = tengoSaldo(costeEdificarCasa);
            
            if (tengoSaldo){
                titulo.edificarCasa();
                modificarSaldo(-costeEdificarCasa);
                edificada = true;
            }
        }
        
        return edificada;
    }
    
    boolean edificarHotel(TituloPropiedad titulo){
        boolean edificada = false;
        int numCasas = titulo.getNumCasas();
        int numHoteles = titulo.getNumHoteles();
        
        if ((numCasas == 4) && (numHoteles < 4)){
            int costeEdificarHotel = titulo.getPrecioEdificar();
            boolean tengoSaldo = tengoSaldo(costeEdificarHotel);
            
            if (tengoSaldo){
                titulo.edificarHotel();
                modificarSaldo(-costeEdificarHotel);
                edificada = true;
            }
        }
        
        return edificada;
    }
    
    private void eliminarDeMisPropiedades(TituloPropiedad titulo){
        propiedades.remove(titulo);
        titulo.setPropietario(null);
    }
    
    private boolean esDeMiPropiedad(TituloPropiedad titulo){
        return propiedades.contains(titulo);
    }
    
    /*boolean estoyEnCalleLibre(){
    
    }*/
    
    Sorpresa getCartaLibertad(){
        return cartaLibertad;
    }
    
    Casilla getCasillaActual(){
        return casillaActual;
    }
    
    boolean getEncarcelado(){
        return encarcelado;
    }

    String getNombre(){
        return nombre;
    }
    
    ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    public int getSaldo(){
        return saldo;
    }

    void hipotecarPropiedad(TituloPropiedad titulo){
        int costeHipoteca = titulo.hipotecar();
        modificarSaldo(costeHipoteca);
    }
    
    void irACarcel(Casilla casillaCarcel){
        setCasillaActual(casillaCarcel);
        setEncarcelado(true);
    }
    
    void modificarSaldo(int cantidad){
        saldo += cantidad;
    }
    
    int obtenerCapital(){
        int capital = saldo;
        
        for (TituloPropiedad t : propiedades){
            capital += (t.getPrecioCompra() + 
            (t.getNumCasas() + t.getNumHoteles()) * t.getPrecioEdificar());
            
            if (t.isHipotecada()){
                capital -= t.getHipotecaBase();
            }
        }
        
        return capital;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedades(boolean estadoHipoteca){
        ArrayList<TituloPropiedad> titulos = new ArrayList();
        
        for (TituloPropiedad t : propiedades){
            if (t.isHipotecada() == estadoHipoteca){
                titulos.add(t);
            }
        }
        
        return titulos;
    }
    
    void pagarAlquiler(){
        int costeAlquiler = casillaActual.pagarAlquiler();
        modificarSaldo(-costeAlquiler);
    }
    
    void pagarImpuesto(){
        saldo -= casillaActual.getCoste();
    }
    
    void pagarLibertad(int cantidad){
        boolean tengoSaldo = tengoSaldo(cantidad);
        
        if (tengoSaldo){
            setEncarcelado(false);
            modificarSaldo(-cantidad);
        }
    }

    void setCartaLibertad(Sorpresa carta){
        this.cartaLibertad = carta;
    }

    void setCasillaActual(Casilla casilla){
        this.casillaActual = casilla;
    }
    
    void setEncarcelado(boolean encarcelado){
        this.encarcelado = true;
    }
    
    boolean tengoCartaLibertad(){
        return (cartaLibertad != null);
    }
    
    private boolean tengoSaldo(int cantidad){
        return (saldo > cantidad);
    }
    
    void venderPropiedad(Casilla casilla){
        TituloPropiedad titulo = casilla.getTitulo();
        eliminarDeMisPropiedades(titulo);
        int precioVenta = titulo.calcularPrecioVenta();
        modificarSaldo(precioVenta);
    }
    
    @Override
    public String toString(){
        String s = "\nJugador{" + "nombre=" + nombre 
                    + ", encarcelado=" + Boolean.toString(encarcelado)
                    + ", saldo=" + Integer.toString(saldo)
                    + ", capital=" + Integer.toString(obtenerCapital());
        if (cartaLibertad != null)
            s = s + ", cartaLibertad=" + cartaLibertad.toString();
        
        s = s + ", casillaActual=" + casillaActual.toString() + ", propiedades=";
        for (TituloPropiedad t : propiedades){
            s = s + t.toString();
        }
        s  = s + "}";
        
        return s;
    }
    
    @Override
    public int compareTo(Object otroJugador){
        int otroCapital = ((Jugador) otroJugador).obtenerCapital();
        return otroCapital - obtenerCapital();
    }
}
