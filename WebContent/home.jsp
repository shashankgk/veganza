<%-- 
    Document   : login
    Created on : 18 Feb, 2016, 10:12:12 PM
    Author     : EDEN
--%>

<%@page import="connection.DedupConn"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<%
String userid=null;

if(session.getAttribute("LoginID")==null)
{
	response.sendRedirect("login.jsp");
}else{
	userid=session.getAttribute("LoginID").toString();
}

%>
<head>
  <title>textured_blue</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="css/style.css" title="style" />
</head>
</body>
 <div class="module form-module">
  <div class="toggle"><i class="fa fa-times fa-pencil"></i>
  <!--  <div class="tooltip">Click Me</div>-->
  </div>
  <div class="form">
    <h2>Upload Your File</h2>
    <form action="UploadServlet" method="post" ENCTYPE="multipart/form-data">
        <input type="text" name="ekey" placeholder="Enter key"/>
      <input type="file" name="filename" />
      <button type="submit">Upload</button>
    </form>
  </div>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://codepen.io/andytran/pen/vLmRVp.js'></script>

        <script src="js/index.js"></script>
</body>
</html>


