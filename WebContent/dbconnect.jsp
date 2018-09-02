<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%
Statement stmt = null;
Connection con=null;
try
{
	Class.forName("com.mysql.jdbc.Driver");
	 con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12254812","sql12254812","qUZkaXbA5N");
	stmt=con.createStatement();
}
catch(Exception ex)
{
	out.println("there's a problem "+ex);
}	
%>