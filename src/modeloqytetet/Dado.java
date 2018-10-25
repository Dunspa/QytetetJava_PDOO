/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Dado
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;

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
    
    /*int tirar(){
    
    }*/
    
    @Override
    public String toString(){
        return "\nDado{" + "valor=" + Integer.toString(valor) + "}";
    }
}
