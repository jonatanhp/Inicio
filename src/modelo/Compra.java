/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author jonatan
 */
public class Compra {
     private int id;
    private int clie_id;
    private String clie_nom;
    private String producto_id;
    private String producto_nombre;
     
    private Date date_created;
    private Date last_updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClie_id() {
        return clie_id;
    }

    public void setClie_id(int clie_id) {
        this.clie_id = clie_id;
    }

    public String getClie_nom() {
        return clie_nom;
    }

    public void setClie_nom(String clie_nom) {
        this.clie_nom = clie_nom;
    }
    public String getProductoId(){
        return producto_id;
    }
    public void setProductoId(String id){
        this.producto_id=id;
    }
      public String getProductoNombre(){ 
        return producto_nombre;
    }
    public void setProductoNombre(String id){
        this.producto_id=id;
    }
    
    
   

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    
}
