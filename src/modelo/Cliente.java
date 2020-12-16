/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jonatan
 */
public class Cliente {  //instanciar=crear cliente con valor en sus variables o atributos)
    private int id;
    private String nombres;
    private String telefono;
    private String dni;
    
    
    public Cliente() {
    }
    public Cliente(String nombres) {
        this.nombres= nombres;
       
    }
    public String toString() {
        return nombres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String infoadic) {
        this.telefono = infoadic;
    }
    
    public String getDni(){
        return dni;
    }
    public void setDni(String ingresardni){
        this.dni=ingresardni;
    }
}
