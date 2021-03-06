/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Tablero
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;
import java.util.ArrayList;

public class Tablero {
    
    private static final int NUM_CASILLAS = 20;
            
    private ArrayList<Casilla> casillas;    // Contiene las casillas
    private Casilla carcel;                 // Indica la cárcel
    
    //------------------------------------------------------------------------
    
    Tablero(){
        this.inicializar();  
    }
    
    //------------------------------------------------------------------------
    
    boolean esCasillaCarcel(int numeroCasilla){
        return (numeroCasilla == carcel.getNumeroCasilla());
    }
    
    Casilla getCarcel() {
        return carcel;
    }
    
    ArrayList<Casilla> getCasillas() {
        return casillas;
    }
    
    // Inicializa todas las casillas del tablero con sus atributos
    private void inicializar(){
        casillas = new ArrayList();   // Se asigna un nuevo arraylist a casillas
        
        casillas.add(new OtraCasilla(0, 0, TipoCasilla.SALIDA));  
        
        casillas.add(new Calle(1, new TituloPropiedad("Calle Bufita", 
        500, 50, 0.10f, 150, 250)));
        
        casillas.add(new Calle(2, new TituloPropiedad("Calle Victoria", 
        1000, 100, 0.20f, 1000, 750)));
        
        casillas.add(new OtraCasilla(3, 0, TipoCasilla.SORPRESA));
        
        casillas.add(new OtraCasilla(4, 0, TipoCasilla.JUEZ));
        
        casillas.add(new Calle(5, new TituloPropiedad("Hotel Chucho", 
        750, 75, 0.15f, 400, 400)));
        
        casillas.add(new Calle(6, new TituloPropiedad("Calle Goof", 
        600, 60, 0.13f, 300, 350)));
        
        casillas.add(new Calle(7, new TituloPropiedad("Centro Comercial Mango", 
        1000, 100, 0.20f, 1000, 750)));
        
        casillas.add(new OtraCasilla(8, 500, TipoCasilla.IMPUESTO));
        
        casillas.add(new Calle(9, new TituloPropiedad("Calle Rocky", 
        500, 50, 0.10f, 150, 250)));
        
        casillas.add(new Calle(10, new TituloPropiedad("Museo Gundam", 
        850, 75, 0.15f, 730, 600)));
        
        casillas.add(new Calle(11, new TituloPropiedad("Cloaca de ratas", 
        500, 60, 0.12f, 225, 420)));
        
        casillas.add(new OtraCasilla(12, 0, TipoCasilla.PARKING));
        
        casillas.add(new OtraCasilla(13, 0, TipoCasilla.SORPRESA));
        
        casillas.add(new Calle(14, new TituloPropiedad("Bosque Shiba", 
        735, 55, 0.17f, 500, 500)));
        
        casillas.add(new Calle(15, new TituloPropiedad("Calle Comunicación", 
        520, 60, 0.13f, 300, 250)));
        
        casillas.add(new OtraCasilla(16, 0, TipoCasilla.CARCEL));
        
        casillas.add(new Calle(17, new TituloPropiedad("Paseo de Torna", 
        780, 90, 0.19f, 400, 350)));
        
        casillas.add(new OtraCasilla(18, 0, TipoCasilla.SORPRESA));
        
        casillas.add(new Calle(19, new TituloPropiedad("Fábrica de roboces", 
        500, 70, 0.10f, 999, 475)));
        
        carcel = casillas.get(15);    // Añadir cárcel a su atributo     
    }

    Casilla obtenerCasillaFinal(Casilla casilla, int desplazamiento){
        int posicion = casilla.getNumeroCasilla() + desplazamiento;
        if (posicion > 19){
            posicion -= 20;
        }
        
        return casillas.get(posicion);
    }
    
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return casillas.get(numeroCasilla);
    }
    
    @Override
    public String toString(){
        String tablero = "\n-Tablero:";
        for (Casilla c : casillas)
            tablero = "\n" + tablero + c.toString();
        
        tablero += "\n";
            
        return tablero;    
    }
}
