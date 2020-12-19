/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;
import modelo.Cliente;
/**
 *
 * @author jonatan
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cliente cliente1=new Cliente();
        cliente1.setId(1);
        cliente1.setNombres("Juan");
        cliente1.setDni("74646");
        
        
        System.out.println(cliente1.getNombres());
        
    }
    
}
