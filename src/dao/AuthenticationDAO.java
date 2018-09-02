package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connection.DedupConn;
import data.RegistrationDTO;

/**
 *
 * @author PRANAV
 */
public class AuthenticationDAO {
	  public ArrayList<String> getPassword(String uname) throws SQLException, ClassNotFoundException
	    {
	    			ArrayList<String> al=new ArrayList<String>();
				     	
	                                
	    							DedupConn dcon=new DedupConn();
	                                 Connection con=dcon.Connection();
					//Creating statement 
	                                 try
	                                 {
	                                   Statement stmt=con.createStatement(); 
	                                   String query="select password,LoginID from login where UserName='"+uname+"'";
					//Executing queries 
	                                   System.out.print(query);
	                                ResultSet rs=stmt.executeQuery(query);   
	                                while(rs.next()){
	                                	al.add(rs.getString(1));
	                                	al.add(rs.getInt(2)+"");
	                                }                               
	                                con.close();     
	                                 }catch(SQLException e){
	                                 }
	                                 
					
	                                  return al;
	         
	               
	                                 
	    }
   public boolean registration(RegistrationDTO rdto) throws SQLException, ClassNotFoundException
    {
       
	  	
                                
	   							 DedupConn dcon=new DedupConn();
                                 Connection con=dcon.Connection();
			
				                 //Executing queries
                                 Statement stmt=con.createStatement();
                                String sql = "insert into registration (Fullname,email,password,confirmpassword,number,address,categoryid,topic,membership,accessRight,accessStatus) values ('"+rdto.getFullname()+"','"+rdto.getEmail()+"','"+rdto.getPassword()+"','"+rdto.getConfirmpassword()+"','"+rdto.getNumber()+"','"+rdto.getAddress()+"','"+rdto.getCategoryID()+"','"+rdto.getTopic()+"','"+rdto.getMembership()+"','"+rdto.getAccessRight()+"','"+rdto.getAccessStatus()+"')";
				                
                                
                                System.out.println("dsafdsafds"+sql);
                               stmt.executeUpdate(sql);
                                
                                con.close();   
                                
                                return true;
                                
                             
         
               
                                 
    } 
}
