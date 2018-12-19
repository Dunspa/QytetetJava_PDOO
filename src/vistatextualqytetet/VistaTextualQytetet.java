/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase VistaTextualQytetet
 * Autor: Jose Luis Gallego Peña
 */
package vistatextualqytetet;
import controladorqytetet.*;
import java.util.*;

public class VistaTextualQytetet {
    
    private static final Scanner in = new Scanner (System.in);
    private ControladorQytetet controlador = ControladorQytetet.getInstance();
    
    //------------------------------------------------------------------------
    
    public ArrayList<String> obtenerNombreJugadores(){
        ArrayList<String> jugadores = new ArrayList();
        
        System.out.println("¿Cuántos jugadores van a jugar? (4 máximo, 2 mínimo)");
        int n = in.nextInt();
        System.out.println("Introduzca los nombres de los " + n + " jugadores:");
        in.nextLine();
        for (int i = 0 ; i < n ; i++){
            jugadores.add(in.nextLine());
        }
            
        return jugadores;
    }
    
    public int elegirCasilla(int opcionMenu){
        ArrayList<Integer> casillasValidas = controlador.obtenerCasillasValidas(opcionMenu);
        ArrayList<String> casillasValidas_s = new ArrayList<String>();
        
        if (casillasValidas.isEmpty()){
            return -1;
        }
        else{
            System.out.println("Casillas: ");
            
            for (Integer i : casillasValidas){
                String numCasilla = i.toString();
                System.out.println(numCasilla);
                casillasValidas_s.add(numCasilla);
            }
            
            return Integer.parseInt(leerValorCorrecto(casillasValidas_s));
        }
    }
    
    public String leerValorCorrecto(ArrayList<String> valoresCorrectos){
        String valor = in.next();
        
        while (!valoresCorrectos.contains(valor)){
            System.out.println("Error. Debe introducir un valor correcto.");
            valor = in.next();
        }
        
        return valor;
    }
    
    public int elegirOperacion(){
        ArrayList<Integer> operaciones = controlador.obtenerOperacionesJuegoValidas(); 
        ArrayList<String> operaciones_s = new ArrayList<String>();
        
        for (Integer i : operaciones){
            String numOperacion = i.toString();
            System.out.println(OpcionMenu.values()[i] + ": " + numOperacion);
            operaciones_s.add(numOperacion);
        }
        
        return Integer.parseInt(leerValorCorrecto(operaciones_s));
    }
    
    public static void main(String[] args){
        VistaTextualQytetet ui = new VistaTextualQytetet();
        ui.controlador.setNombreJugadores(ui.obtenerNombreJugadores());
        int operacionElegida, casillaElegida = 0;
        boolean necesitaElegirCasilla;
        
        do{
            operacionElegida = ui.elegirOperacion();
            necesitaElegirCasilla = ui.controlador.necesitaElegirCasilla(operacionElegida);
            if (necesitaElegirCasilla)
                casillaElegida = ui.elegirCasilla(operacionElegida);
            if (!necesitaElegirCasilla || casillaElegida >= 0)
                System.out.println(ui.controlador.realizarOperacion(operacionElegida, casillaElegida));
        }while (1 == 1);
    }
}
