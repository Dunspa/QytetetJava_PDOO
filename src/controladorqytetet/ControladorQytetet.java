/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase ControladorQytetet
 * Autor: Jose Luis Gallego Peña
 */
package controladorqytetet;
import modeloqytetet.MetodoSalirCarcel;
import modeloqytetet.EstadoJuego;
import modeloqytetet.Qytetet;
import java.util.*;

public class ControladorQytetet{
    
    // Singleton
    private static final ControladorQytetet instance = new ControladorQytetet();  
    private ArrayList<String> nombreJugadores;
    private Qytetet modelo = Qytetet.getInstance();
    
    //------------------------------------------------------------------------
  
    private ControladorQytetet(){
        
    }
    
    //------------------------------------------------------------------------
    
    public void setNombreJugadores(ArrayList<String> nombreJugadores){
        this.nombreJugadores = nombreJugadores;
    }
    
    public ArrayList<Integer> obtenerOperacionesJuegoValidas(){
        ArrayList<Integer> operaciones = new ArrayList<Integer>();
        int opcion;
        
        if (modelo.getJugadores().isEmpty()){
            opcion = OpcionMenu.INICIARJUEGO.ordinal();
            operaciones.add(opcion);
        }
        else{
            // Opciones por defecto
            opcion = OpcionMenu.TERMINARJUEGO.ordinal();
            operaciones.add(opcion);
            opcion = OpcionMenu.MOSTRARJUGADORACTUAL.ordinal();
            operaciones.add(opcion);
            opcion = OpcionMenu.MOSTRARJUGADORES.ordinal();
            operaciones.add(opcion);
            opcion = OpcionMenu.MOSTRARTABLERO.ordinal();
            operaciones.add(opcion);
            
            if (modelo.getEstadoJuego() == EstadoJuego.JA_PREPARADO){
                opcion = OpcionMenu.JUGAR.ordinal();
                operaciones.add(opcion);
            }
            
            if (modelo.getEstadoJuego() == EstadoJuego.ALGUNJUGADORENBANCARROTA){
                // Una vez que se obtiene el ranking no se puede seguir jugando
                opcion = OpcionMenu.OBTENERRANKING.ordinal();
                operaciones.add(opcion);
            }
            
            if (modelo.getEstadoJuego() == EstadoJuego.JA_CONSORPRESA){
                opcion = OpcionMenu.APLICARSORPRESA.ordinal();
                operaciones.add(opcion);
            }
            
            if (modelo.getEstadoJuego() == EstadoJuego.JA_ENCARCELADO){
                opcion = OpcionMenu.PASARTURNO.ordinal();
                operaciones.add(opcion);
            }
            
            if (modelo.getEstadoJuego() == EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD){
                opcion = OpcionMenu.INTENTARSALIRCARCCELTIRANDODADO.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.INTENTARSALIRCARCELPAGANDOLIBERTAD.ordinal();
                operaciones.add(opcion);
            }
            
            if (modelo.getEstadoJuego() == EstadoJuego.JA_PUEDECOMPRAROGESTIONAR){
                opcion = OpcionMenu.PASARTURNO.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.COMPRARTITULOPROPIEDAD.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.VENDERPROPIEDAD.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.HIPOTECARPROPIEDAD.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.CANCELARHIPOTECA.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.EDIFICARCASA.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.EDIFICARHOTEL.ordinal();
                operaciones.add(opcion);
            }
            
            if (modelo.getEstadoJuego() == EstadoJuego.JA_PUEDEGESTIONAR){
                opcion = OpcionMenu.PASARTURNO.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.VENDERPROPIEDAD.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.HIPOTECARPROPIEDAD.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.CANCELARHIPOTECA.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.EDIFICARCASA.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.EDIFICARHOTEL.ordinal();
                operaciones.add(opcion);
            }
        }
        
        return operaciones;
    }
    
    public boolean necesitaElegirCasilla(int opcionMenu){
        
    }
    
    public ArrayList<Integer> obtenerCasillasValidas(int opcionMenu){
        
    }
    
    public String realizarOperacion(int opcionElegida, int casillaElegida){
        
    }
    
    public static ControladorQytetet getInstance(){
        return instance;
    }
}
