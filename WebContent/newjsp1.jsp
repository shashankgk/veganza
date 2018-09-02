<%-- 
    Document   : newjsp1
    Created on : 21 Jul, 2015, 11:47:21 AM
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
        <form action="newjsp.jsp" method="post">
            Name <input type="text" name="name" /><br />
            Class<input type="text" name="class"/><br/>
           Mark1<input type="text" name="mark1"/><br/>
           Mark2<input type="text" name="mark2"/><br/>
           Mark3<input type="text" name="mark3"/><br/>
           <input type="submit" name="submit" value="submit" />
        </form>
        
    </body>
</html>
