<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="connection.DedupConn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	DedupConn ms=new DedupConn();
	Connection con=ms.Connection();
	Statement st=con.createStatement();
	String uid = request.getParameter("shareto");
	String fid = request.getParameter("fn");
	String query1 = "insert into share (fid,uid) values ('"+fid+"','"+uid+"')";
	 st.executeUpdate(query1);
	
	response.sendRedirect("sharestatus.jsp");
%>
</body>
</html>