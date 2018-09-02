<%-- 
    Document   : login
    Created on : 20 Feb, 2016, 12:33:11 PM
    Author     : EDEN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="connection.DedupConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
  <title>textured_blue</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="css/style.css" title="style" />
</head>
<script>
    
        </script>
</body>
 <div class="module form-module">
  <div class="toggle"><i class="fa fa-times fa-pencil"></i>
  <!--  <div class="tooltip">Click Me</div>-->
  </div>
  <div class="form">
    <h2>Login to your account</h2>
    <form name="form1" action="processlogin.jsp">
        <input type="text" placeholder="Username" name="username"/>
      <input type="password" placeholder="Password" name="password"/>
      <button>Login</button>
    </form>
<br />
&nbsp;&nbsp;&nbsp;<a href="register.jsp"> Sign Up</a>                       
  </div>
  <div class="cta"><a href="http://andytran.me">Forgot your password?</a></div>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://codepen.io/andytran/pen/vLmRVp.js'></script>

        <script src="js/index.js"></script>
</body>
</html>
