/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Qytetet
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;
import java.util.ArrayList;

public class Qytetet {
    
    public static final int MAX_JUGADORES = 4;
    static final int NUM_SORPRESAS = 10;
    public static final int NUM_CASILLAS = 20;
    static final int PRECIO_LIBERTAD = 200;
    static final int SALDO_SALIDA = 1000;
    
    private static final Qytetet instance = new Qytetet();  // Singleton
    private ArrayList<Sorpresa> mazo = new ArrayList();     // Almacena las cartas
    private ArrayList<Jugador> jugadores = new ArrayList(); // Jugadores
    private Tablero tablero;
    private Sorpresa cartaActual;
    private Jugador jugadorActual;
    private Dado dado;
    
    //------------------------------------------------------------------------
    
    private Qytetet(){
        
    }
    
    //------------------------------------------------------------------------
    
    /* void actuarSiEnCasillaEdificable(){
    
    }
    
    void actuarSiEnCasillaNoEdificable(){
    
    }
    
    public void aplicarSorpresa(){
    
    }
    
    public boolean cancelarHipoteca(int numeroCasilla){
    
    }
    
    public boolean comprarTituloPropiedad(){
    
    }
    
    public boolean edificarCasa(int numeroCasilla){
    
    }
    
    public boolean edificarHotel(int numeroCasilla){
    
    }
    
    private void encarcelarJugador(){
    
    } */

    public Sorpresa getCartaActual() {
        return cartaActual;
    }

    public Dado getDado() {
        return dado;
    }
    
    public Jugador getJugadorActual() {
        return jugadorActual;
    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    ArrayList<Sorpresa> getMazo(){
        return mazo;    
    }

    public Tablero getTablero() {
        return tablero;
    }
    
    public static Qytetet getInstance(){
        return instance;
    }
    
    /*public int getValorDado(){
    
    }
    
    public int hipotecarPropiedad(int numeroCasilla){
    
    }*/
    
    //------------------------------------------------------------------------
    
    // Se crean y se incluyen en el mazo todas las cartas sorpresa
    private void inicializarCartasSorpresa(){

        // Carta sorpresa que suma una cantidad de dinero al saldo del jugador
        mazo.add(new Sorpresa ("Después de ser un fracasado toda tu vida, "
        + "¡te toca la lotería! ...Pero sigues siendo un fracasado.", 
        + 7000, TipoSorpresa.PAGARCOBRAR));
        
        // Carta sorpresa que resta una cantidad de dinero al saldo del jugador
        mazo.add(new Sorpresa ("El banco te quita dinero porque sí, da gracias"
        + " que aún sigas teniendo un plato que poner en la mesa.", 
        + -7000, TipoSorpresa.PAGARCOBRAR));
        
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
        
        // Carta sorpresa que hace que el jugador cobre por su propiedad
        mazo.add(new Sorpresa ("Tu propiedad ha obtenido beneficios así que te "
        + "llevas un buen pico.", 10000, TipoSorpresa.PORCASAHOTEL));
        
        // Carta sorpresa que hace que el jugador pague por su propiedad
        mazo.add(new Sorpresa ("Te toca pagar por tener tierras, no seas tan"
        + "avaricioso la próxima vez.", -10000, TipoSorpresa.PORCASAHOTEL));
                
        // Carta sorpresa que hace que todos los jugadores te paguen
        mazo.add(new Sorpresa ("Hoy es tu día de suerte, todos los jugadores"
        + " deben pagarte por tu cara bonita.", 3000, TipoSorpresa.PORJUGADOR));
        
        // Carta sorpresa que hace que tengas que pagar a todos los jugadores
        mazo.add(new Sorpresa ("Hoy no es tu día de suerte, debes pagar a todos"
        + " los jugadores porque así lo dice esta carta", -3000, 
        TipoSorpresa.PORJUGADOR));
       
        // Carta sorpresa que saca al jugador de la cárcel
        mazo.add(new Sorpresa ("Parece ser que le has caído bien a alguien y ha"
        + " pagado tu fianza. Sales de la cárcel.", 0, TipoSorpresa.SALIRCARCEL));        
        
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarJugadores(nombres);
        inicializarTablero();
        inicializarCartasSorpresa();
    }
    
    private void inicializarJugadores(ArrayList<String> nombres){
        for (String n : nombres)
            this.jugadores.add(new Jugador(n));
    }
    
    private void inicializarTablero(){  
        tablero = new Tablero();
    }
    
    /*public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
    
    }
    
    public void jugar(){
    
    }
    
    void mover(int numCasillaDestino){
    
    }
    
    public Casilla obtenerCasillaJugadorActual(){
    
    }
    
    public Casilla obtenerCasillasTablero(){
    
    }
    
    public int obtenerPropiedadesJugador(){
    
    }
    
    public int obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca){
    
    }
    
    public void obtenerRanking(){
    
    }
    
    public int obtenerSaldoJugadorActual(){
    
    }
    
    private void salidaJugadores(){
    
    }*/
    
    private void setCartaActual(Sorpresa cartaActual){
        this.cartaActual = cartaActual;
    }
    
    /*public void siguienteJugador(){
    
    }
    
    int tirarDado(){
    
    }
    
    public boolean venderPropiedad(int numeroCasilla){
    
    }*/
    
    @Override
    public String toString(){
        String s = "\nJuego Qytetet:" + tablero.toString();
        for (Jugador j : jugadores){
            s = s + j.toString();
        }
        for (Sorpresa sorp : mazo){
            s = s + sorp.toString();
        }   
        if (jugadorActual != null && cartaActual != null){
            s = s + (", jugadorActual=" + jugadorActual.toString());
            s = s + (", cartaActual=" + cartaActual.toString());
        }
        if (dado != null)
            s = s + (", dado=" + dado.toString());
        
        s = s + "\n";
        
        return s;
    }
}
