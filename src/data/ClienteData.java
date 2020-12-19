/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import modelo.Producto;
import util.ErrorLogger;

/**
 *
 * @author jonatan
 */
public class ProductoData {
     static Connection cn = Conn.connectSQLite();
    static PreparedStatement ps;  //Statement= Sentencia  -  PreparedStatement=Sentencia Preparada (cuando no conozco el valor del dato a ingresar)
    static ErrorLogger log = new ErrorLogger(ProductoData.class.getName());

    public static int create(Producto d) {
        int rsId = 0;
        String[] returns = {"idTipo"};
        String sql = "INSERT INTO Tipo(nomTipo) "
                + "VALUES(?)";
        int i = 0;
        try {
            ps = cn.prepareStatement(sql, returns); //genera el id en automatico de manera secuencial 
            ps.setString(++i, d.getNombres()); //establezco el nombre
            ps.setInt(++i, d.getNombres()); //establezco el nombre
            ps.setInt(++i, d.getNombres()); //establezco el nombre
            ps.setString(++i, d.getNombres()); //establezco el nombre
            ps.setDouble(++i, d.getNombres()); //establezco el nombre
            
            rsId = ps.executeUpdate();// 0 no o 1 si commit 
            try (ResultSet rs = ps.getGeneratedKeys()) { //resulset=tabla virtual que es el reflejo de mi tabla en la base de datos
                if (rs.next()) { //next=siguiente (si es que existe un siguiente registro)
                    rsId = rs.getInt(1); // select tk, max(id)  from cliente
                    //System.out.println("rs.getInt(rsId): " + rsId);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            //System.err.println("create:" + ex.toString());
            
        }
        return rsId;
    }

    public static int update(Producto d) {
        System.out.println("actualizar d.getId(): " + d.getId());
        int comit = 0;
        String sql = "UPDATE Producto SET "  // update = actualizar
                + "nombres=?, "
                + "telefono=? "
                + "WHERE id=?"; // where = donde
        int i = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(++i, d.getNombres());
            ps.setString(++i, d.getTelefono());
            ps.setInt(++i, d.getId());
            ps.setString(++i, d.getTelefono());
            ps.setInt(++i, d.getId());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }

    public static int delete(int id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM Producto WHERE id = ?";  //Delete = eliminar
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "delete", ex);
            // System.err.println("NO del " + ex.toString());
            throw new Exception("Detalle:" + ex.getMessage());
        }
        return comit;
    }

    public static List<Producto> listCmb(String filter) {
        List<Producto> ls = new ArrayList();
        ls.add(new Producto("Seleccione..."));
        ls.addAll(list(filter));
        return ls;
    }

    public static List<Producto> list(String filter) {
        String filtert = null;
        if (filter == null) {
            filtert = "";
        } else {
            filtert = filter;
        }
        System.out.println("list.filtert:" + filtert);

        List<Producto> ls = new ArrayList(); // operador "new" significa instanciar una clase.

        String sql = "";
        if (filtert.equals("")) {
            sql = "SELECT nombres, telefono, dni FROM Producto ORDER BY id";
        } else {
            sql = "SELECT * FROM cliente WHERE (id LIKE'" + filter + "%' OR "
                    + "nombres LIKE'" + filter + "%' OR infoadic LIKE'" + filter + "%' OR "
                    + "id LIKE'" + filter + "%') "
                    + "ORDER BY nombres";
        }
        try {
            Statement st = cn.createStatement();  //ya no se usa una consulta preparada (preparedstatement). se usa un statement (sentencia) normal
            ResultSet rs = st.executeQuery(sql); //ejecuta consulta
            while (rs.next()) {
                Producto d = new Producto();  //instanciado de una clase, de la clase Producto
                d.setId(rs.getInt("id"));
                d.setNombres(rs.getString("nombres"));
                d.setTelefono(rs.getString("telefono"));
                d.setDni(rs.getString("dni"));
                ls.add(d);
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "list", ex);
        }
        return ls;
    }

    public static Producto getByPId(int id) {
        Producto d = new Producto();

        String sql = "SELECT * FROM cliente WHERE id = ? ";
        int i = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(++i, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                d.setId(rs.getInt("id"));
                d.setNombres(rs.getString("nombres"));
                d.setInfoadic(rs.getString("infoadic"));
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "getByPId", ex);
        }
        return d;
    }
    
}
