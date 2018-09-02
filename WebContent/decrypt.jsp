
    <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="connection.DedupConn" %>
<%@page import="data.Encryption" %>
<%@page import="java.util.List" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="header.jsp" %>
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
	String fid = request.getParameter("fn");
        String key = request.getParameter("key");
	String fileName = null;
	int refid = 0;
	String query1 = "select filename,refid from data_repo where id="+fid;
	//out.print(query1);
	ResultSet rs = st.executeQuery(query1);
	while(rs.next()){
		fileName = rs.getString(1);
		refid = rs.getInt(2);
		//out.print("<tr><td><input type='radio' name='fn' value='" + rs.getInt(1) + "' required /></td><td>"+rs.getInt(1)+"</td><td><a href='enc/"+rs.getString(2)+".enc'>"+rs.getString(2)+"</a></td></tr>");
	}
	if(refid != 0){
		query1 = "select filename from data_repo where id="+refid;
	}
	
	rs = st.executeQuery(query1);
	while(rs.next()){
                if(rs.getString(1).indexOf("~")<0)
                    fileName = rs.getString(1);
		
	}
         String path ="C:\\ittc\\workspace1\\dedup\\download\\";
         String code=request.getParameter("key");
       // Cloud cl = new Cloud();
        //cl.downloadtodropbox(path,fileName+".enc",code);
	key= request.getParameter("key");
	Encryption en= new Encryption();
	en.dec(fileName,key);
	out.print("<a href='enc/"+fileName+".dec' target='new' />Click here to download the decrypted file</a>");
	//copy(Cipher.DECRYPT_MODE, tempFileName, resultFileName, "password12345678");

    
%>
    </body>
    </html>