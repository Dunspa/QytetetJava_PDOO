/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;

/**
 *
 * @author Jose Luis Gallego Peña
 */
public class Qytetet {
    
    private ArrayList<Sorpresa> mazo = new ArrayList<>(); // Almacena las cartas
    
    ArrayList<Sorpresa> getMazo(){
    
        return mazo;
        
    }
    
    // Se crean y se incluyen en el mazo todas las cartas sorpresa
    void inicializarCartasSorpresa(){
        
        /* PAGARCOBRAR */
        
        // Carta sorpresa que suma una cantidad de dinero al saldo del jugador
        mazo.add(new Sorpresa ("Después de ser un fracasado toda tu vida, "
        + "¡te toca la lotería! ...Pero sigues siendo un fracasado.", 
        + 7000, TipoSorpresa.PAGARCOBRAR));
        
        // Carta sorpresa que resta una cantidad de dinero al saldo del jugador
        mazo.add(new Sorpresa ("El banco te quita dinero porque sí, da gracias"
        + " que aún sigas teniendo un plato que poner en la mesa.", 
        + -7000, TipoSorpresa.PAGARCOBRAR));
        
        /* IRACASILLA */
        
        // Carta sorpresa que manda al jugador a la cárcel
        mazo.add(new Sorpresa ("Estás más pillado que pilladín, mejor suerte "
        + "la próxima vez. ¡A la carcel!", 9, TipoSorpresa.IRACASILLA));
        
        // Carta sorpresa que manda al jugador a una casilla
        mazo.add(new Sorpresa ("Te tropiezas y sales volando a una casilla "
        + "diferente.", 16, TipoSorpresa.IRACASILLA));
        
        // Carta sorpresa que manda al jugador a una casilla
        mazo.add(new Sorpresa ("Te tropiezas y sales volando a una casilla "
        + "diferente.", 4, TipoSorpresa.IRACASILLA));
        
        /* PORCASAHOTEL */
        
        // Carta sorpresa que hace que el jugador cobre por su propiedad
        mazo.add(new Sorpresa ("Tu propiedad ha obtenido beneficios, así que te "
        + "llevas un buen pico.", 10000, TipoSorpresa.PORCASAHOTEL));
        
        // Carta sorpresa que hace que el jugador pague por su propiedad
        mazo.add(new Sorpresa ("Te toca pagar por tener tierras, así que te "
        + "toca apoquinar.", -10000, TipoSorpresa.PORCASAHOTEL));
        
        /* PORJUGADOR */
                
        // Carta sorpresa que hace que todos los jugadores te paguen
        mazo.add(new Sorpresa ("Hoy es tu día de suerte, todos los jugadores"
        + " deben pagarte por tu cara bonita.", 3000, TipoSorpresa.PORJUGADOR));
        
        // Carta sorpresa que hace que tengas que pagar a todos los jugadores
        mazo.add(new Sorpresa ("Hoy no es tu día de suerte, debes pagar a todos"
        + " los jugadores por ser tan avaricioso.", -3000, TipoSorpresa.PORJUGADOR));
        
        /* SALIRCARCEL */
       
        // Carta sorpresa que saca al jugador de la cárcel
        mazo.add(new Sorpresa ("Poderoso caballero es Don Dinero, alguien ha "
        + "pagado tu fianza. Sales de la cárcel.", 0, TipoSorpresa.SALIRCARCEL));        
        
    }
    
}
