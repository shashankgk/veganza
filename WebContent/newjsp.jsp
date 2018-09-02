<%-- 
    Document   : newjsp
    Created on : 21 Jul, 2015, 11:00:16 AM
    Author     : EDEN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="connection.DedupConn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
DedupConn ms=new DedupConn();
Connection con=ms.Connection();
%>
    <body>
        
        <%
            Statement st=con.createStatement();
            String id=request.getParameter("repid");
            String clas=request.getParameter("filename");
            String hash=request.getParameter("hascode");
            String query1="insert into  data_repository (repository_id,filename,generated_hashcode ) values ('" + id + "','" +clas +"','" + hash + "')";%>
            
            <%
            int s=st.executeUpdate(query1);
            if(s>0)
            
                out.print("inserted");
            else
                out.print("not inserted");
          
            con.close();
            %>
            
            
            <script>
                window.location="UploadServlet";
            </script>
            
    </body>
</html>
