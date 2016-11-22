/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mdetest.concretesyntax.eap;

import java.sql.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EAPConnection {

    Connection con;
    Statement s;
    PreparedStatement ps;
    String sqlText;
    static EAPConnection c;

    /**
     *
     */
    private EAPConnection() {   }

    public static EAPConnection CreateConnection(String fileName) {

        c = new EAPConnection();

        try {

            // Definimos el conector a la Base de Datos MS Access
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        	//Connection conn=DriverManager.getConnection("jdbc:ucanaccess://<mdb or accdb file path>",user, password); 
        	
        	
            // Cadena de conexi�n
        	/*
            String database = "" +
            		"jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
            database += fileName.trim() + ";READONLY=true}";

            c.con = DriverManager.getConnection(database, "", "");*/
        	//Connection conn=DriverManager.getConnection("jdbc:ucanaccess://c:/pippo.mdb");
        	 c.con = DriverManager.getConnection("jdbc:ucanaccess://"+fileName);
            //c.s = c.con.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EAPConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new EAPException(ex);
        } catch (SQLException ex) {
            //Logger.getLogger(EAPConnection.class.getName()).log(Level.SEVERE, null, ex);
        	System.err.println("File nor found: " +fileName);
            throw new EAPException(ex);
        }

        return c;
    }

    /**
     *
     * @param query
     * @return
     */
    ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            this.s = c.con.createStatement();
            rs = this.s.executeQuery(query);
        } catch (SQLException ex) {
            //Logger.getLogger(DPPQueries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Consulta SQL:\n" + sqlText);
            throw new EAPException(ex);
        }

        return rs;
    }

    /**
     *
     * @param query
     */
    void execute(String query) {

        try {
            this.s = c.con.createStatement();
            this.s.execute(query);
            this.s.close();
        } catch (SQLException ex) {
            //Logger.getLogger(DPPQueries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Consulta SQL:\n" + sqlText);
            throw new EAPException(ex);
        }


    }

    /**
     * @param table
     * @param fieldName
     * @return
     */
    long nextKeyForTable(String table, String fieldName) {


        long key =  - 1;
        String sql ="";

        try {
            sql = "SELECT " + fieldName + " FROM " + table + " ORDER BY " + fieldName + " DESC;";

            java.sql.ResultSet rs = this.executeQuery(sql);
            if (rs != null && rs.next()) {
                key = rs.getLong(1);
            }
            rs.close();


        } catch (SQLException ex) {
            //Logger.getLogger(EAPConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Next key for table: " + sql);
            ex.printStackTrace();

        }

        return key + 1;
    }
}
