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
            else if (modelo.getEstadoJuego() == EstadoJuego.ALGUNJUGADORENBANCARROTA){
                // Una vez que se obtiene el ranking no se puede seguir jugando
                opcion = OpcionMenu.OBTENERRANKING.ordinal();
                operaciones.add(opcion);
            }
            else if (modelo.getEstadoJuego() == EstadoJuego.JA_CONSORPRESA){
                opcion = OpcionMenu.APLICARSORPRESA.ordinal();
                operaciones.add(opcion);
            }
            else if (modelo.getEstadoJuego() == EstadoJuego.JA_ENCARCELADO){
                opcion = OpcionMenu.PASARTURNO.ordinal();
                operaciones.add(opcion);
            }
            else if (modelo.getEstadoJuego() == EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD){
                opcion = OpcionMenu.INTENTARSALIRCARCELTIRANDODADO.ordinal();
                operaciones.add(opcion);
                opcion = OpcionMenu.INTENTARSALIRCARCELPAGANDOLIBERTAD.ordinal();
                operaciones.add(opcion);
            }
            else if (modelo.getEstadoJuego() == EstadoJuego.JA_PUEDECOMPRAROGESTIONAR){
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
            else if (modelo.getEstadoJuego() == EstadoJuego.JA_PUEDEGESTIONAR){
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
        return (opcionMenu == OpcionMenu.HIPOTECARPROPIEDAD.ordinal() ||
                opcionMenu == OpcionMenu.CANCELARHIPOTECA.ordinal() ||
                opcionMenu == OpcionMenu.EDIFICARCASA.ordinal() ||
                opcionMenu == OpcionMenu.EDIFICARHOTEL.ordinal() ||
                opcionMenu == OpcionMenu.VENDERPROPIEDAD.ordinal() 
                );
    }
    
    public ArrayList<Integer> obtenerCasillasValidas(int opcionMenu){
        ArrayList<Integer> casillas = new ArrayList<Integer>();
        OpcionMenu opcion = OpcionMenu.values()[opcionMenu];
        
        if (opcion == OpcionMenu.HIPOTECARPROPIEDAD){
            casillas = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(false);
        }
        else if (opcion == OpcionMenu.CANCELARHIPOTECA){
            casillas = modelo.obtenerPropiedadesJugadorSegunEstadoHipoteca(true);
        }
        else if (opcion == OpcionMenu.EDIFICARCASA){
            casillas = modelo.obtenerPropiedadesJugador();
        }
        else if (opcion == OpcionMenu.EDIFICARHOTEL){
            casillas = modelo.obtenerPropiedadesJugador();
        }
        else if (opcion == OpcionMenu.VENDERPROPIEDAD){
            casillas = modelo.obtenerPropiedadesJugador();
        }
        
        return casillas;
    }
    
    public String realizarOperacion(int opcionElegida, int casillaElegida){
        String operaciones = "";
        OpcionMenu opcionMenu = OpcionMenu.values()[opcionElegida];
        
        if (opcionMenu == OpcionMenu.INICIARJUEGO){
            modelo.inicializarJuego(nombreJugadores);
            operaciones += "\nQue empiece el juego";
        }
        else if (opcionMenu == OpcionMenu.JUGAR){
            modelo.jugar();
            operaciones += "\nSe tira el dado y se obtiene: " + modelo.getValorDado();
            operaciones += "\nEl jugador cae en la casilla: " + modelo.obtenerCasillaJugadorActual();
        }
        else if (opcionMenu == OpcionMenu.APLICARSORPRESA){
            operaciones += "Se aplica la sorpresa: " + modelo.getCartaActual();
            modelo.aplicarSorpresa();
        }
        else if (opcionMenu == OpcionMenu.INTENTARSALIRCARCELPAGANDOLIBERTAD){
            boolean sale = modelo.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
            operaciones += "\nIntentas salir de la cárcel pagando";
            if (sale){
                operaciones += "\nTienes suficiente dinero así que pagas y sales de la cárcel";
            }
            else{
                operaciones += "\nEres un pobre, te quedas en la cárcel";
            }
        }
        else if (opcionMenu == OpcionMenu.INTENTARSALIRCARCELTIRANDODADO){
            boolean sale = modelo.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
            operaciones += "\nIntentas salir de la cárcel tirando el dado y sacas: " + modelo.getValorDado();
            if (sale){
                operaciones += "\nConsigues salir de la cárcel";
            }
            else{
                operaciones += "\nSale mal. No sales de la cárcel";
            }
        }
        else if (opcionMenu == OpcionMenu.COMPRARTITULOPROPIEDAD){
            boolean comprado = modelo.comprarTituloPropiedad();
            if (comprado){
                operaciones += "\nHas comprado la propiedad";
            }
            else{
                operaciones += "\nNo has podido comprar la propiedad";
            }
        }
        else if (opcionMenu == OpcionMenu.HIPOTECARPROPIEDAD){
            modelo.hipotecarPropiedad(casillaElegida);
            operaciones += "\nHipotecas la casilla número " + casillaElegida;
        }
        else if (opcionMenu == OpcionMenu.CANCELARHIPOTECA){
            modelo.cancelarHipoteca(casillaElegida);
            operaciones += "\nCancelas la hipoteca de la casilla número " + casillaElegida;
        }
        else if (opcionMenu == OpcionMenu.EDIFICARCASA){
            boolean edificada = modelo.edificarCasa(casillaElegida);
            if (edificada){
                operaciones += "\nHas edificado una casa";
            }
            else{
                operaciones += "\nNo has podido edificar la casa";
            }
        }
        else if (opcionMenu == OpcionMenu.EDIFICARHOTEL){
            boolean edificada = modelo.edificarHotel(casillaElegida);
            if (edificada){
                operaciones += "\nHas edificado un hotel";
            }
            else{
                operaciones += "\nNo has podido edificar el hotel";
            }
        }
        else if (opcionMenu == OpcionMenu.VENDERPROPIEDAD){
            modelo.venderPropiedad(casillaElegida);
            operaciones += "\nVendes la propiedad de la casilla " + casillaElegida;
        }
        else if (opcionMenu == OpcionMenu.PASARTURNO){
            modelo.siguienteJugador();
            operaciones += "\nPasas tu turno";
            operaciones += "\nSiguiente jugador: " + modelo.getJugadorActual();
        }
        else if (opcionMenu == OpcionMenu.OBTENERRANKING){
            int i = 1;
            modelo.obtenerRanking();
            operaciones += "\nRanking de jugadores: ";
            for (modeloqytetet.Jugador j : modelo.getJugadores()){
                operaciones += "\n" + i + " - " + j.toString();
                i++;
            }
        }
        else if (opcionMenu == OpcionMenu.TERMINARJUEGO){
            operaciones += "\nCERRAMOS EL CHIRINGUITO\n";
            System.exit(0);
        }
        else if (opcionMenu == OpcionMenu.MOSTRARJUGADORACTUAL){
            operaciones += modelo.getJugadorActual();
        }
        else if (opcionMenu == OpcionMenu.MOSTRARJUGADORES){
            operaciones += modelo.getJugadores();
        }
        else if (opcionMenu == OpcionMenu.MOSTRARTABLERO){
            operaciones += modelo.getTablero();
        }
        
        return operaciones;
    }
    
    public static ControladorQytetet getInstance(){
        return instance;
    }
}
