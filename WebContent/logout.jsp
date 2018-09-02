<%-- 
    Document   : logout
    Created on : 7 Mar, 2016, 12:26:13 PM
    Author     : EDEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
session.setAttribute("userid", null);
session.invalidate();
response.sendRedirect("index.jsp");
%>
    </body>
</html>
