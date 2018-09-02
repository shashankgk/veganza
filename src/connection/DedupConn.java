package connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DedupConn
{
    public Connection Connection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            {Logger.getLogger(DedupConn.class.getName()).log(Level.SEVERE, null, e);}
        }   
        try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dedup","root","");
            return con;
        } catch ( SQLException ex) {Logger.getLogger(DedupConn.class.getName()).log(Level.SEVERE, null, ex);}
        return null;
    }
   
}