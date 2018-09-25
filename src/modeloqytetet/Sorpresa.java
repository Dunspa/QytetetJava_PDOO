/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author Jose Luis Gallego Peña
 */
public class Sorpresa {
    
    private String texto;       // Describe la sorpresa
    private TipoSorpresa tipo;  // Indica el tipo de sorpresa
    private int valor;          // Afecta de forma diferente según la carta
    
    /* Constructor */ 
    
    public Sorpresa(String texto, int valor, TipoSorpresa tipo){
    
        this.texto = texto;
        this.valor = valor;
        this.tipo = tipo;
    
    }
    
    /* Consultores */
    
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
        
        return "Sorpresa{" + "texto=" + texto + ", valor=" +
        Integer.toString(valor) + ", tipo=" + tipo + "}";

    }
    
}
