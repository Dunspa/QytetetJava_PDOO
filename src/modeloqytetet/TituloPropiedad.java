/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase TituloPropiedad
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;

public class TituloPropiedad {
    
    private String nombre;              // Nombre de la calle
    private boolean hipotecada;         // Indica si el título está hipotecado
    private int precioCompra;           // Precio de compra
    private int alquilerBase;           // Precio base a pagar
    private float factorRevalorizacion; // Indica cuanto se revaloriza el título
    private int hipotecaBase;           // Valor base de la hipoteca
    private int precioEdificar;         // Precio para edificar casas y hoteles
    private int numHoteles;             // Número de hoteles edificados
    private int numCasas;               // Número de casas edificadas
    
    /* Constructor */
    
    public TituloPropiedad(String nombre, int precioCompra, int alquilerBase,
                           float factorRevalorizacion, int hipotecaBase, 
                           int precioEdificar){
        
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
        hipotecada = false;
        numHoteles = 0;
        numCasas = 0;
        
    }
    
    /* Consultores */

    public String getNombre() {
        return nombre;
    }

    public boolean isHipotecada() {
        return hipotecada;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public int getAlquilerBase() {
        return alquilerBase;
    }

    public float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    public int getHipotecaBase() {
        return hipotecaBase;
    }

    public int getPrecioEdificar() {
        return precioEdificar;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public int getNumCasas() {
        return numCasas;
    }
    
    /* Modificadores */

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    
    @Override
    public String toString(){
    
        return "TituloPropiedad{" + "nombre=" + nombre + 
                ", hipotecada=" + Boolean.toString(hipotecada) + 
                ", precioCompra=" + Integer.toString(precioCompra) + 
                ", alquilerBase=" + Integer.toString(alquilerBase) +
                ", factorRevalorizacion=" + Float.toString(factorRevalorizacion) +
                ", hipotecaBase=" + Integer.toString(hipotecaBase) +
                ", precioEdificar=" + Integer.toString(precioEdificar) +
                ", numHoteles=" + Integer.toString(numHoteles) +
                ", numCasas=" + Integer.toString(numCasas) + "}";
    
    }
    
}
