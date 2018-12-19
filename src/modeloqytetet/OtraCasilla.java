/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * SubClase OtraCasilla, hereda de Casilla
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;

public class OtraCasilla extends Casilla{
    private TipoCasilla tipo;       // Tipo de la casilla
    
    //------------------------------------------------------------------------
    
    // Casilla de salida
    OtraCasilla(){
        super();
        this.tipo = TipoCasilla.SALIDA;
    }
    
    OtraCasilla(int numeroCasilla, int coste, TipoCasilla tipo){ 
        super(numeroCasilla, coste);
        this.tipo = tipo;
    }
    
    //------------------------------------------------------------------------
    
    @Override
    protected TipoCasilla getTipo() {
        return tipo;
    }
    
    @Override
    protected boolean soyEdificable(){
        return false;
    }
    
    @Override
    protected TituloPropiedad getTitulo(){
        return null;
    }
    
    protected void setTipo(TipoCasilla tipo){
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", tipo=" + tipo;
    }
}
