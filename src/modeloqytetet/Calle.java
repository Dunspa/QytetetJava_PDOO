/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * SubClase Calle, hereda de Casilla
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;

public class Calle extends Casilla{
    private TituloPropiedad titulo; // Asocia la casilla a su título
    
    //------------------------------------------------------------------------
    
    Calle(int numeroCasilla, TituloPropiedad titulo){
        super(numeroCasilla, titulo.getPrecioCompra());
        this.titulo = titulo;
    }
    
    //------------------------------------------------------------------------
    
    public void asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);
    }
    
    boolean propietarioEncarcelado(){
        return titulo.propietarioEncarcelado();
    }
    
    @Override
    protected TituloPropiedad getTitulo() {
        return titulo;
    }
    
    @Override
    protected TipoCasilla getTipo(){
        return TipoCasilla.CALLE;
    }
    
    public int pagarAlquiler(){
        int costeAlquiler = titulo.pagarAlquiler();
        
        return costeAlquiler;
    }
    
    private void setTitulo(TituloPropiedad titulo){
        this.titulo = titulo;
    }
    
    @Override
    protected boolean soyEdificable(){
        return true;
    }
    
    public boolean tengoPropietario(){
        return titulo.tengoPropietario();
    }
    
    @Override
    public String toString(){
        return super.toString() + ", tipo=" + TipoCasilla.CALLE + ", titulo=" + titulo + "}";
    }
}
