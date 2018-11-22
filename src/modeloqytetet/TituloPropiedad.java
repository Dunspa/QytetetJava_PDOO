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
    private Jugador propietario;        // Jugador que tiene esta propiedad
    
    //------------------------------------------------------------------------
    
    TituloPropiedad(String nombre, int precioCompra, int alquilerBase,
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
        propietario = null;
    }
    
    //------------------------------------------------------------------------
    
    int calcularCosteCancelar(){
        return ((int)(calcularCosteHipotecar() + 0.10 * calcularCosteHipotecar()));
    }
    
    int calcularCosteHipotecar(){
        return ((int)(hipotecaBase + numCasas * 0.5 * hipotecaBase + numHoteles * hipotecaBase));
    }
    
    int calcularImporteAlquiler(){
        return (alquilerBase + ((int)(numCasas * 0.5 + numHoteles * 2)));
    }
    
    int calcularPrecioVenta(){
        return ((int)(precioCompra + (numCasas + numHoteles) * precioEdificar * factorRevalorizacion));
    }
    
    void cancelarHipoteca(){
        setHipotecada(false);
    }
    
    /*void cobrarAlquiler(int coste){
        
    }*/
    
    void edificarCasa(){
        numCasas = numCasas + 1;
    }
    
    void edificarHotel(){
        numHoteles = numHoteles + 1;
    }
    
    String getNombre() {
        return nombre;
    }

    boolean isHipotecada() {
        return hipotecada;
    }

    int getPrecioCompra() {
        return precioCompra;
    }

    int getAlquilerBase() {
        return alquilerBase;
    }

    float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    int getHipotecaBase() {
        return hipotecaBase;
    }

    int getPrecioEdificar() {
        return precioEdificar;
    }

    int getNumHoteles() {
        return numHoteles;
    }

    int getNumCasas() {
        return numCasas;
    }
    
    Jugador getPropietario(){
        return propietario;
    }
    
    int hipotecar(){
        setHipotecada(true);
        int costeHipoteca = calcularCosteHipotecar();
        
        return costeHipoteca;
    }
    
    int pagarAlquiler(){
       int costeAlquiler = calcularImporteAlquiler(); 
       propietario.modificarSaldo(costeAlquiler);
       
       return costeAlquiler;
    }
    
    boolean propietarioEncarcelado(){
        return propietario.getEncarcelado();
    }
    
    /*void setHipotecada(boolean hipotecada){
        
    }*/
    
    void setPropietario(Jugador propietario){
        this.propietario = propietario;
    }
    
    boolean tengoPropietario(){
        return (propietario != null);
    }
    
    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    
    @Override
    public String toString(){
        return "\nTituloPropiedad{" + "nombre=" + nombre + 
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
