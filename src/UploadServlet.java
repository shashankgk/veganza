
import connection.DedupConn;
import data.Encryption;
import data.DrBxService;

import java.io.*;
import java.security.MessageDigest;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UploadServlet extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 50 * 1024;
   private int maxMemSize = 4 * 2048;
   private File file ;
   String hash = null;
   String fileName = null;
   String fileName1 = null;
   String hash1 = null;
   String query1 = "";
   public void init( ){
      // Get the file location where it would be stored.
      filePath = 
             getServletContext().getInitParameter("file-upload"); 
   }
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);

      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
      if( !isMultipart ){
   
       
         out.println("<p>No file uploaded</p>"); 
        
         return;
      }
      try{
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      
      // Parse the request to get file items.
      List fileItems = null;
      
           fileItems = upload.parseRequest(request);
      
      // Process the uploaded file items
      Iterator i = fileItems.iterator();

      String code = null;
      String filecontent = null;
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            fileName = fi.getName();
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            //integrate your code here
            
      
                    filecontent = fi.getString();
        	   fi.write( file );
        	  
	     
           
         }else{
            if(fi.getFieldName().equals("ekey")){
                  
                  code=fi.getString();
                  
              }
        	  
            /*if(fi.getFieldName().equals("fn")){
                  
                  fileName=fi.getString();
                  
              }*/
         }
      }	 
		 
		 
        MD5Digest m = new MD5Digest();
    	
        System.out.println("bbbbbbbbbbbbbbbbbbbb");
    	
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(filecontent.getBytes());
        
        byte byteData[] = md.digest();
 
       
        
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int j=0;j<byteData.length;j++) {
    		String hex=Integer.toHexString(0xff & byteData[j]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	System.out.println("Digest(in hex format):: " + hexString.toString());
        hash= hexString.toString();
        
        int count = 0;
       
       DedupConn ms=new DedupConn();
       Connection con=ms.Connection();
       Statement st=con.createStatement();
      
       
       Statement st1=con.createStatement();

       String UserID=request.getSession().getAttribute("LoginID").toString();
       String  qry="select id from data_repo where generated_hashcode = '"+hash+"'";
       ResultSet rs=st.executeQuery(qry);
       System.out.println(qry);
       while(rs.next())
       {
           count=rs.getInt(1);
           if(count>0){
        	   System.out.println("DEDUPLICATION:::ELIMINATING DUPLICATE UPLOAD");
        	   query1="insert into data_repo(filename,generated_hashcode,refid,uid) values ('" +fileName +"','" + hash + "','"+rs.getInt(1)+"','"+UserID+"')";
        	   st1.executeUpdate(query1);
                   break;
           }
       }
           if(count==0){
        	   qry="select max(id) from data_repo where filename = '"+fileName+"'";
        	   System.out.print(qry);
        	   ResultSet rs1=st1.executeQuery(qry);
        	   int cnt = 0;
        	   while(rs1.next())
               {
        		   cnt = rs1.getInt(1);
               }
        	   int li =0;
        	   qry="select max(id) from data_repo";
        	   System.out.print(qry);
        	   rs1=st1.executeQuery(qry);
        	   while(rs1.next())
               {
        		   li = rs1.getInt(1);
               }
        	   
        	   if(cnt>0){
        		   fileName1 = fileName +  "~" + (li+1);
        		   // Write the file
                    /*   if( fileName.lastIndexOf("\\") >= 0 ){
                           file = new File( filePath + 
                           fileName.substring( fileName.lastIndexOf("\\"))) ;
                        }else{
                           file = new File( filePath + 
                           fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                        } */
                        
                        
        	   }
			   
			   
			  // fi.write( file );
        	   String key=code;//request.getParameter("ekey");
        	   if(key==null || key.equals("")){
        		   key = hash.substring(0, Math.min(hash.length(), 16));
        	   }
        	   System.out.println("kkkkkkkkkkkkkeyyyyyyyyy" + key);
	           Encryption en= new Encryption();
	           en.enc(file,file.getName(),key,fileName1);
                   System.out.println(fileName1+"222222222222222222222222");
                   if(fileName1!=null){
	           query1="insert into data_repo(filename,generated_hashcode,uid) values ('" +fileName1 +"','" + hash + "','"+UserID+"')";
                   }else{
                       query1="insert into data_repo(filename,generated_hashcode,uid) values ('" +fileName +"','" + hash + "','"+UserID+"')";
                   }
	           
	           st1.executeUpdate(query1);
                   System.out.print(query1+"eeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	           // After Upload
                   qry="select max(id) from data_repo";
        	   System.out.print(qry);
        	   rs1=st1.executeQuery(qry);
        	   while(rs1.next())
               {
        		   li = rs1.getInt(1);
               }
        	   
	           query1="update data_repo set refid="+li+" where id="+cnt;
                   System.out.print(query1);
	           out.println("Uploaded Filename....: " + fileName + "<br>");
	           st1.executeUpdate(query1);
	           		DrBxService cl=new DrBxService();
                   //code = "E-DmBEZMAsYAAAAAAAAAJ7awZWASAnbSqPNuSSirihg";
                   String path = "C:\\ittc\\workspace1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dedup\\enc\\";
                   if(fileName1!=null){
                    //cl.upload( path,fileName1+".enc");
                   }else{
                    //cl.upload( path,file.getName()+".enc");   
                   }
                  
		 
		 
        
         
			}
    
     
  
      
      }catch(Exception ex) {
       ex.printStackTrace();
   }
     
      
      response.sendRedirect("fileList.jsp");
      
      
   
   }
   public void doGet(HttpServletRequest request, 
                       HttpServletResponse response)
        throws ServletException, java.io.IOException {
        
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
        
        
            
                 
        
       
      // response.sendRedirect("/deduplication/index.jsp");
   } 

    private int executeUpdate(String qry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int executeQuery(String qry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}