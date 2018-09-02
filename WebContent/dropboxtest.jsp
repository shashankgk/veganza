<%@ page language="java" import="data.TestDrBx" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

TestDrBx cl=new TestDrBx();
//code = "E-DmBEZMAsYAAAAAAAAAJ7awZWASAnbSqPNuSSirihg";
String path = "C:\\ittc\\workspace1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dedup\\enc\\";
String file = "a5.txt.enc";
String code="";
cl.upload( path,file);   


%>
</body>
</html>