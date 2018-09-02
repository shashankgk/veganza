<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.sql.Connection,java.sql.DriverManager,java.sql.SQLException" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%@ include file="dbconnect.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String UserName=request.getParameter("username");
String Password=request.getParameter("password");
String dbpass=null;
int LoginID=0;
try{
	
	String query="select password,userid from login where username='"+UserName+"'";
	out.print(query);
	ResultSet rs=stmt.executeQuery(query);
	while(rs.next()){
		dbpass=rs.getString(1);
		LoginID=rs.getInt(2);
	}
	if(dbpass != null && dbpass.equals(Password))
	{
		session.setAttribute("UserName",UserName); 
		session.setAttribute("LoginID",LoginID); 
	%>
	<script type="text/javascript">
	window.location="index.jsp";
</script>
<%		
}
else
{
%>
<script type="text/javascript">
	window.location="login.jsp?err=1";
</script>
<%
}
	stmt.close();
	con.close();

	
	
}catch(Exception e)
{
	out.println(e);
}

%>

</body>
</html>