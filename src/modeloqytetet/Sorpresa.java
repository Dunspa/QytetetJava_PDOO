/*
 * Programación y Diseño Orientado a Objetos - Práctica Qytetet
 * Clase Sorpresa
 * Autor: Jose Luis Gallego Peña
 */
package modeloqytetet;

public class Sorpresa {
    
    private String texto;       // Describe la sorpresa
    private int valor;          // Afecta de forma diferente según la carta
    private TipoSorpresa tipo;  // Indica el tipo de sorpresa
    
    //------------------------------------------------------------------------
    
    Sorpresa(String texto, int valor, TipoSorpresa tipo){   
        this.texto = texto;
        this.valor = valor;
        this.tipo = tipo;    
    }
    
    public Sorpresa(Sorpresa s){
        this.texto = s.getTexto();
        this.valor = s.getValor();
        this.tipo = s.getTipo();    
    }
    
    //------------------------------------------------------------------------
    
    String getTexto(){
        return texto;
    }
    
    TipoSorpresa getTipo(){
        return tipo;   
    }
    
    int getValor(){
        return valor;
    }
    
    // Devuelve un string con el estado del objeto correspondiente
    @Override
    public String toString() {      
        return "\nSorpresa{" + "texto=" + texto + ", valor=" +
                Integer.toString(valor) + ", tipo=" + tipo + "}";
    }   
}
