<%-- 
    Document   : register
    Created on : 20 Feb, 2016, 11:00:52 AM
    Author     : EDEN
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="connection.DedupConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>

<head>
  <title>textured_blue</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
 <!-- <link rel="stylesheet" type="text/css" href="css/register.css" title="style" />-->
  <link rel="stylesheet" type="text/css" href="css/style.css" title="style" />

</head>
<script type="text/javascript">
function validate()
            {
                alert(1);
                alert(document.getElementById("password").value);
                var ps=document.getElementById("password").value;
                var cp=document.getElementById("confirm").value;
                var mob=document.getElementById("phone").value;
                if(!isNaN(mob)){
                    alert("Digits only");
                    return false;
                }
                if(mob.length!=10)
                {
                    alert("invalid mobile number");
                    return false;
                }
                if(ps!=cp)
                {
                    alert("Password Does Not Match.....");
                    return false;
                }
                
                
            }
</script>

<body>
  <!-- Form Mixin-->
<!-- Input Mixin-->
<!-- Button Mixin-->
<!-- Pen Title-->
<!--<div class="pen-title">
</div>-->
<!-- Form Module-->
<div class="module form-module">
  <div class="toggle"><i class="fa fa-times fa-pencil"></i>
  <!--  <div class="tooltip">Click Me</div>-->
  </div>
  <div class="form">
    <h2>Create an account</h2>
    <form name="register" method="post">
        <input type="text" placeholder="Username" id="username" name="username" required/>
      <input type="password" placeholder="Password" id="password" name="password" required/>
      <input type="password" placeholder="Confirm Password" id="confirm"  name="confirm" required/>
      <input type="email" placeholder="Email Address" id="email" name="email" required/>
      <input type="tel" placeholder="Phone Number" name="phone" id="phone" required/>
      <input type="submit" value="Register" name="register" onclick="validate()" style="color: #FFF; background-color: #1293EE;"/>
    </form>
  </div>
 <!-- <div class="cta"><a href="http://andytran.me">Forgot your password?</a></div>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://codepen.io/andytran/pen/vLmRVp.js'></script>

        <script src="js/index.js"></script>-->
        
    <!--END #signup-form -->   
    <%        DedupConn ms=new DedupConn();
              Connection con=ms.Connection();
              Statement st=con.createStatement();
              String username,name,password,email,phone,sub,address,confirm;
              sub=request.getParameter("register");
              if(sub!=null)
              {
              username=request.getParameter("username");
              password=request.getParameter("password");
              confirm=request.getParameter("confirm");
              email=request.getParameter("email");
              phone=request.getParameter("phone");
              if(username==""|| password==""|| confirm=="" || email=="" || phone=="")
            {
                 out.print("some fields are empty");
                response.sendRedirect("login.jsp");   
            }
            String qry="insert into login(username,password,confirm,email,phone) values('"+username+"','"+ password +"','"+confirm+"','"+ email+"','"+phone+"')";
            int s=st.executeUpdate(qry);
            if (s==1) {
                response.sendRedirect("regmessage.jsp");
            } 
            else {
                out.println("register.jsp");
            }
            
            }
             
           %>    
</body>
</html>