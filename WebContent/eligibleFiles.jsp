<%-- 
    Document   : login
    Created on : 20 Feb, 2016, 12:33:11 PM
    Author     : EDEN
--%>

<%@include file="dbconnect.jsp" %>
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
 <div class="module form-module1">
  <div class="toggle"><i class="fa fa-times fa-pencil"></i>
  <!--  <div class="tooltip">Click Me</div>-->
  </div>
  <div class="form">
    <form name="flist" action="decrypt.jsp" method="post">
 
        <input type="text" name="key" required/>
    
<input type="submit" value="Decrypt and Download" />
<table>
<%
	
	String query1 = "select s.id,dr.filename,s.fid from share s,data_repo dr where s.fid=dr.id and s.uid='"+userid+"'";
	//out.print(query1);
        ResultSet rs = stmt.executeQuery(query1);
	while(rs.next()){
		out.print("<tr><td><input type='radio' name='fn' value='" + rs.getInt(3) + "' required /></td><td>"+rs.getInt(1)+"</td><td><a href='enc/"+rs.getString(2)+".enc'>"+rs.getString(2)+"</a></td></tr>");
	}
%>
</table>
</form>
  </div>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://codepen.io/andytran/pen/vLmRVp.js'></script>

        <script src="js/index.js"></script>
</body>
</html>
