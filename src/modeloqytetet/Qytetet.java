/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Qytetet
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;
import java.util.ArrayList;

public class Qytetet {
    
    private ArrayList<Sorpresa> mazo = new ArrayList<>(); // Almacena las cartas
    Tablero tablero;
    
    /* Consultores */
    
    ArrayList<Sorpresa> getMazo(){
        return mazo;    
    }

    public Tablero getTablero() {
        return tablero;
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
        mazo.add(new Sorpresa ("Has sido pillado, mejor suerte "
        + "la próxima vez. ¡A la carcel!", tablero.getCarcel().getNumeroCasilla(), 
        TipoSorpresa.IRACASILLA));
        
        // Carta sorpresa que manda al jugador a una casilla
        mazo.add(new Sorpresa ("Te tropiezas y sales volando a una casilla "
        + "diferente.", 17, TipoSorpresa.IRACASILLA));
        
        // Carta sorpresa que manda al jugador a una casilla
        mazo.add(new Sorpresa ("Te tropiezas y sales volando a una casilla "
        + "diferente.", 4, TipoSorpresa.IRACASILLA));
        
        /* PORCASAHOTEL */
        
        // Carta sorpresa que hace que el jugador cobre por su propiedad
        mazo.add(new Sorpresa ("Tu propiedad ha obtenido beneficios así que te "
        + "llevas un buen pico.", 10000, TipoSorpresa.PORCASAHOTEL));
        
        // Carta sorpresa que hace que el jugador pague por su propiedad
        mazo.add(new Sorpresa ("Te toca pagar por tener tierras, no seas tan"
        + "avaricioso la próxima vez.", -10000, TipoSorpresa.PORCASAHOTEL));
        
        /* PORJUGADOR */
                
        // Carta sorpresa que hace que todos los jugadores te paguen
        mazo.add(new Sorpresa ("Hoy es tu día de suerte, todos los jugadores"
        + " deben pagarte por tu cara bonita.", 3000, TipoSorpresa.PORJUGADOR));
        
        // Carta sorpresa que hace que tengas que pagar a todos los jugadores
        mazo.add(new Sorpresa ("Hoy no es tu día de suerte, debes pagar a todos"
        + " los jugadores porque así lo dice esta carta", -3000, 
        TipoSorpresa.PORJUGADOR));
        
        /* SALIRCARCEL */
       
        // Carta sorpresa que saca al jugador de la cárcel
        mazo.add(new Sorpresa ("Parece ser que le has caído bien a alguien y ha"
        + " pagado tu fianza. Sales de la cárcel.", 0, TipoSorpresa.SALIRCARCEL));        
        
    }
    
    // Inicializa el tablero del juego
    void inicializarTablero(){
    
        tablero = new Tablero();
    
    }
}
