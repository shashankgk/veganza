<%@include file="dbconnect.jsp" %>
<%@include file="header.jsp" %>
<html>
    <%
    
String userid=null;

if(session.getAttribute("LoginID")==null)
{
	response.sendRedirect("login.jsp");
}else{
	userid=session.getAttribute("LoginID").toString();


%>
<head>
    <script>
        function valid()
        {
            if(document.getElementById("shareto")=="")
            {
                alert("Please select");
                return false;
            }
        }
        </script>
  <title>textured_blue</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="css/style.css" title="style" />
</head>
<body>
 <div class="module form-module1">
  <div class="toggle"><i class="fa fa-times fa-pencil"></i>
  <!--  <div class="tooltip">Click Me</div>-->
  </div>
  <div class="form1">
    <form name="flist" action="share.jsp" method="post">
	<select name="shareto" style="width:200px" required>
			<option value="">Select User</option>
            <%
            
            String query="select * from login where userid!='"+userid+"'";
            Statement stmt1=con.createStatement();
            	ResultSet rs1=stmt1.executeQuery(query);
            	while(rs1.next())
            	{
            		out.print("<option value='"+rs1.getInt(1)+"'>"+rs1.getString(2)+"</option>");
            	}
            
            %>
				</select>
	<input type="submit" value="Share" onclick="valid()"/>
<table>
<%
	String linkname=null;
        String UserID=session.getAttribute("LoginID").toString();
	int linkid = 0;
	String query1 = "select id,filename,refid,uploaded_date from data_repo where uid='"+UserID+"'";
	System.out.print(query1);
	ResultSet rs = stmt.executeQuery(query1);
        int k=1;
	while(rs.next()){
		linkname=rs.getString(2);
		linkid = rs.getInt(1);
		if(rs.getInt(3)!=0){
			linkid = rs.getInt(3);
			query1="select filename from data_repo where id='"+rs.getInt(3)+"'";
			Statement stmt3=con.createStatement();
			ResultSet rs5 = stmt3.executeQuery(query1);
			while(rs5.next()){
				linkname=rs5.getString(1);
			}
		}
		
		if(rs.getString(2).indexOf("~")==-1){
			out.print("<tr><td><input type='radio' name='fn' value='" + linkid  + "' required /></td><td>"+k+"</td><td><a href='enc/"+linkname+".enc'>"+rs.getString(2)+"</a></td><td>"+rs.getTimestamp(4)+"</td></tr>");
		}
                k++;
	}
        
}//end session check
%>
</table>
</form>
 
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://codepen.io/andytran/pen/vLmRVp.js'></script>

        <script src="js/index.js"></script>
 </div>
</body>
</html>
