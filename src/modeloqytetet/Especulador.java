/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Especulador
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;

public class Especulador extends Jugador{
    
    private int fianza;
    
    //------------------------------------------------------------------------
    
    protected Especulador(Jugador jugador, int fianza){
        super(jugador);
        this.fianza = fianza;
    }
    
    //------------------------------------------------------------------------
    
    @Override
    protected void pagarImpuesto(){
        super.modificarSaldo(-(super.getCasillaActual().getCoste() / 2));
    }
    
    @Override
    protected boolean deboIrACarcel(){
        return (super.deboIrACarcel() && !pagarFianza());
    }
    
    @Override
    protected Especulador convertirme(int fianza){
        this.fianza = fianza;
        return this;
    }
    
    private boolean pagarFianza(){
        boolean puedoPagarFianza = false;
        
        if (super.tengoSaldo(fianza)){
            puedoPagarFianza = true;
            super.modificarSaldo(-fianza);
        }
        
        return puedoPagarFianza;
    }
    
    @Override
    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
        return ((titulo.getNumCasas() < 8) && tengoSaldo(titulo.getPrecioEdificar()));
    }
    
    @Override
    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
        return ((titulo.getNumCasas() >= 4) && (titulo.getNumHoteles() < 8) && tengoSaldo(titulo.getPrecioEdificar()));
    }
    
    @Override
    public String toString(){
        return ("\n-Especulador " + super.toString());
    }
}
