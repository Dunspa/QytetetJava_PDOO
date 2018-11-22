/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Qytetet
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;
import java.util.*;

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
    private EstadoJuego estadoJuego;
    
    //------------------------------------------------------------------------
    
    private Qytetet(){
        
    }
    
    //------------------------------------------------------------------------
    
    void actuarSiEnCasillaEdificable(){
        boolean deboPagar = jugadorActual.deboPagarAlquiler();
        if (deboPagar){
            jugadorActual.pagarAlquiler();
            
            if (jugadorActual.getSaldo() <= 0 ){
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            }
        }
        
        Casilla casilla = obtenerCasillaJugadorActual();
        boolean tengoPropietario = casilla.tengoPropietario();
        if (estadoJuego != EstadoJuego.ALGUNJUGADORENBANCARROTA){
            if (tengoPropietario){
                setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
            }
            else{
                setEstadoJuego(EstadoJuego.JA_PUEDECOMPRAROGESTIONAR);
            }
        }
    }
    
    void actuarSiEnCasillaNoEdificable(){
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        Casilla casillaActual = jugadorActual.getCasillaActual();
        if (casillaActual.getTipo() == TipoCasilla.IMPUESTO){
            jugadorActual.pagarImpuesto();
            
            if (jugadorActual.getSaldo() <= 0){
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            }
        }
        else{
            if (casillaActual.getTipo() == TipoCasilla.JUEZ){
                encarcelarJugador();
            }
            else if (casillaActual.getTipo() == TipoCasilla.SORPRESA){
                cartaActual = mazo.remove(0);
                setEstadoJuego(EstadoJuego.JA_CONSORPRESA);
            }
        }
    }
    
    public void aplicarSorpresa(){
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
        }
        else{
            mazo.add(cartaActual);
            
            if (cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR){
                jugadorActual.modificarSaldo(cartaActual.getValor());
                
                if (jugadorActual.getSaldo() <= 0){
                    setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                }
            }
            else if (cartaActual.getTipo() == TipoSorpresa.IRACASILLA){
                int valor = cartaActual.getValor();
                boolean casillaCarcel = tablero.esCasillaCarcel(valor);
            
                if (casillaCarcel){
                    encarcelarJugador();
                }
                else{
                    mover(valor);
                }
            }
            else if (cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
                int cantidad = cartaActual.getValor();
                int numeroTotal = jugadorActual.cuantasCasasHotelesTengo();
                jugadorActual.modificarSaldo(numeroTotal * cantidad);

                if (jugadorActual.getSaldo() <= 0){
                    setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                }
            }
            else if (cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
                for (Jugador j : jugadores){
                    if (j != jugadorActual){
                        j.modificarSaldo(cartaActual.getValor());

                        if (j.getSaldo() <= 0){
                            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                        }

                        jugadorActual.modificarSaldo(-cartaActual.getValor());

                        if (jugadorActual.getSaldo() <= 0){
                            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                        }
                    }
                }
            }
        }
    }
    
    public boolean cancelarHipoteca(int numeroCasilla){
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        TituloPropiedad titulo = tablero.obtenerCasillaNumero(numeroCasilla).getTitulo();
        return jugadorActual.cancelarHipoteca(titulo);
    }
    
    public boolean comprarTituloPropiedad(){
        boolean comprado = jugadorActual.comprarTituloPropiedad();
        
        if (comprado){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        
        return comprado;
    }
    
    public boolean edificarCasa(int numeroCasilla){
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        boolean edificada = jugadorActual.edificarCasa(titulo);
        
        if (edificada){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        
        return edificada;
    }
    
    public boolean edificarHotel(int numeroCasilla){
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        boolean edificada = jugadorActual.edificarHotel(titulo);
        
        if (edificada){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        
        return edificada;
    }
    
    private void encarcelarJugador(){
        if (!jugadorActual.tengoCartaLibertad()){
            Casilla casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
            setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        }
        else{
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
    }

    public Sorpresa getCartaActual() {
        return cartaActual;
    }

    Dado getDado() {
        return dado;
    }
    
    public EstadoJuego getEstadoJuego(){
        return estadoJuego;
    }
    
    Jugador getJugadorActual() {
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
    
    public int getValorDado(){
        return dado.getValor();
    }
    
    public void hipotecarPropiedad(int numeroCasilla){
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        jugadorActual.hipotecarPropiedad(titulo);
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    }
    
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
        
        // Se barajan las cartas
        Collections.shuffle(mazo);
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarJugadores(nombres);
        inicializarTablero();
        inicializarCartasSorpresa();
        salidaJugadores();
    }
    
    private void inicializarJugadores(ArrayList<String> nombres){
        for (String n : nombres)
            this.jugadores.add(new Jugador(n));
    }
    
    private void inicializarTablero(){  
        tablero = new Tablero();
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        if (metodo == MetodoSalirCarcel.TIRANDODADO){
            int resultado = tirarDado();
            
            if (resultado >= 5){
                jugadorActual.setEncarcelado(false);
            }
        }
        else if (metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
            jugadorActual.pagarLibertad(PRECIO_LIBERTAD);
        }
        
        boolean encarcelado = jugadorActual.getEncarcelado();
        if (encarcelado){
            setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        }
        else{
            setEstadoJuego(EstadoJuego.JA_PREPARADO);
        }
        
        return !encarcelado;
    }
    
    public void jugar(){
        int num = tirarDado();
        int pos = tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(), num).getNumeroCasilla();
        mover(pos);
    }
    
    void mover(int numCasillaDestino){
        Casilla casillaInicial = jugadorActual.getCasillaActual();
        Casilla casillaFinal = tablero.obtenerCasillaNumero(numCasillaDestino);
        jugadorActual.setCasillaActual(casillaFinal);
        
        if (numCasillaDestino < casillaInicial.getNumeroCasilla()){
            jugadorActual.modificarSaldo(SALDO_SALIDA);
        }
        
        if (casillaFinal.soyEdificable()){
            actuarSiEnCasillaEdificable();
        }
        else{
            actuarSiEnCasillaNoEdificable();
        }
    }
    
    public Casilla obtenerCasillaJugadorActual(){
        return jugadorActual.getCasillaActual();
    }
    
    /*public Casilla obtenerCasillasTablero(){
    
    }*/
    
    public ArrayList<Integer> obtenerPropiedadesJugador(){
        ArrayList<Integer> propiedadesJugador = new ArrayList();
        
        for (Casilla c : tablero.getCasillas()){
            if (c.getTipo() == TipoCasilla.CALLE){
                if (jugadorActual.getPropiedades().contains(c.getTitulo())){
                    propiedadesJugador.add(c.getNumeroCasilla());
                }
            }
        }
        
        return propiedadesJugador;
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca){
        ArrayList<Integer> propiedadesSegunHipoteca = new ArrayList();
        
        for (Casilla c : tablero.getCasillas()){
            if (c.getTipo() == TipoCasilla.CALLE){
                if (jugadorActual.obtenerPropiedades(estadoHipoteca).contains(c.getTitulo())){
                    propiedadesSegunHipoteca.add(c.getNumeroCasilla());
                }
            }
        }
        
        return propiedadesSegunHipoteca;
    }
    
    public void obtenerRanking(){
        Collections.sort(jugadores);
    }
    
    public int obtenerSaldoJugadorActual(){
        return jugadorActual.getSaldo();
    }
    
    private void salidaJugadores(){
        for (Jugador j : jugadores){
            j.setCasillaActual(tablero.obtenerCasillaNumero(0));
        }   
        
        Random rand = new Random();
        int jugador_actual = rand.nextInt(jugadores.size());
        jugadorActual = jugadores.get(jugador_actual);
        estadoJuego = EstadoJuego.JA_PREPARADO;
    }
    
    private void setCartaActual(Sorpresa cartaActual){
        this.cartaActual = cartaActual;
    }
    
    public void setEstadoJuego(EstadoJuego estadoJuego){
        this.estadoJuego = estadoJuego;
    }
    
    public void siguienteJugador(){
        int pos = jugadores.indexOf(jugadorActual);
        if (pos + 1 == jugadores.size()){
            jugadorActual = jugadores.get(0);
        }
        else{
            jugadorActual = jugadores.get(pos + 1);
        }
        
        if (jugadorActual.getEncarcelado()){
            estadoJuego = EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD;
        }
        else{
            estadoJuego = EstadoJuego.JA_PREPARADO;
        }
    }
    
    int tirarDado(){
        return dado.tirar();
    }
    
    public void venderPropiedad(int numeroCasilla){
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        jugadorActual.venderPropiedad(casilla);
    }
    
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
