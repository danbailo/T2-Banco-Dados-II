/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T2_BDII;

/**
 *
 * @author daniel
 */
public class Telefone {
    private long idtelefone;
    private String tipo;
    private String numero;
    private long id_vendedor;    
    
    public Telefone(){
        
    }
    
    public Telefone(String tipo, String numero, long id_vendedor){
        this.tipo = tipo;
        this.numero = numero;
        this.id_vendedor = id_vendedor;
    }

    public long getIdtelefone() {
        return idtelefone;
    }

    public void setIdtelefone(long idtelefone) {
        this.idtelefone = idtelefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public long getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(long id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
}
