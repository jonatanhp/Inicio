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
import modelo.Cliente;
import util.ErrorLogger;

/**
 *
 * @author jonatan
 */
public class ClienteData {
     static Connection cn = Conn.connectSQLite();
    static PreparedStatement ps;
    static ErrorLogger log = new ErrorLogger(ClienteData.class.getName());

    public static int create(Cliente d) {
        int rsId = 0;
        String[] returns = {"id"};
        String sql = "INSERT INTO cliente(nombres, infoadic) "
                + "VALUES(?,?)";
        int i = 0;
        try {
            ps = cn.prepareStatement(sql, returns);
            ps.setString(++i, d.getNombres());
            ps.setString(++i, d.getInfoadic());
            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1); // select tk, max(id)  from cliente
                    //System.out.println("rs.getInt(rsId): " + rsId);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            //System.err.println("create:" + ex.toString());
            log.log(Level.SEVERE, "create", ex);
        }
        return rsId;
    }

    public static int update(Cliente d) {
        System.out.println("actualizar d.getId(): " + d.getId());
        int comit = 0;
        String sql = "UPDATE cliente SET "
                + "nombres=?, "
                + "infoadic=? "
                + "WHERE id=?";
        int i = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(++i, d.getNombres());
            ps.setString(++i, d.getInfoadic());
            ps.setInt(++i, d.getId());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }

    public static int delete(int id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM cliente WHERE id = ?";
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

    public static List<Cliente> listCmb(String filter) {
        List<Cliente> ls = new ArrayList();
        ls.add(new Cliente("Seleccione..."));
        ls.addAll(list(filter));
        return ls;
    }

    public static List<Cliente> list(String filter) {
        String filtert = null;
        if (filter == null) {
            filtert = "";
        } else {
            filtert = filter;
        }
        System.out.println("list.filtert:" + filtert);

        List<Cliente> ls = new ArrayList();

        String sql = "";
        if (filtert.equals("")) {
            sql = "SELECT * FROM cliente ORDER BY id";
        } else {
            sql = "SELECT * FROM cliente WHERE (id LIKE'" + filter + "%' OR "
                    + "nombres LIKE'" + filter + "%' OR infoadic LIKE'" + filter + "%' OR "
                    + "id LIKE'" + filter + "%') "
                    + "ORDER BY nombres";
        }
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente d = new Cliente();
                d.setId(rs.getInt("id"));
                d.setNombres(rs.getString("nombres"));
                d.setInfoadic(rs.getString("infoadic"));
                ls.add(d);
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "list", ex);
        }
        return ls;
    }

    public static Cliente getByPId(int id) {
        Cliente d = new Cliente();

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
