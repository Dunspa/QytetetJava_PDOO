/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Dado
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;
import java.util.Random;

public class Dado {

    private static final Dado instance = new Dado();
    private int valor;
    
    //------------------------------------------------------------------------
    
    private Dado(){
        valor = 0;
    }
    
    //------------------------------------------------------------------------
    
    public static Dado getInstance(){
        return instance;
    }

    public int getValor() {
        return valor;
    }
    
    int tirar(){
        Random rand = new Random();
        valor = (rand.nextInt(6)) + 1;   // Genera aleatorio entre 1 y 6
        return valor;
    }
    
    @Override
    public String toString(){
        return "\n-Dado:" + "\nValor: " + Integer.toString(valor) + "\n";
    }
}
