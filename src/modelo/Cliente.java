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
public class Cliente {
    private int id;
    private String nombres;
    private String infoadic;
    
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

    public String getInfoadic() {
        return infoadic;
    }

    public void setInfoadic(String infoadic) {
        this.infoadic = infoadic;
    }
}
