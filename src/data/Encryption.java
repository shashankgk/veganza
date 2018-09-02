package data;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Encryption {

	private static final int IV_LENGTH=16;

	/* A helper - to reuse the stream code below - if a small String is to be encrypted */
	public static byte[] encrypt(String plainText, String password) throws Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream(plainText.getBytes("UTF8"));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		encrypt(bis, bos, password);
		return bos.toByteArray();
	}


	public static byte[] decrypt(String cipherText, String password) throws Exception {
		byte[] cipherTextBytes = cipherText.getBytes();
		ByteArrayInputStream bis = new ByteArrayInputStream(cipherTextBytes);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		decrypt(bis, bos, password);		
		return bos.toByteArray();
	}


	public static void encrypt(InputStream in, OutputStream out, String password) throws Exception{

		SecureRandom r = new SecureRandom();
		byte[] iv = new byte[IV_LENGTH];
		r.nextBytes(iv);
		out.write(iv); //write IV as a prefix
		out.flush();
		//System.out.println(">>>>>>>>written"+Arrays.toString(iv));

		Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding"); //"DES/ECB/PKCS5Padding";"AES/CBC/PKCS5Padding"
		SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);    	

		out = new CipherOutputStream(out, cipher);
		byte[] buf = new byte[1024];
		int numRead = 0;
		while ((numRead = in.read(buf)) >= 0) {
			out.write(buf, 0, numRead);
		}
		out.close();
	}


	public static void decrypt(InputStream in, OutputStream out, String password) throws Exception{
		System.out.println("decrypt");
		byte[] iv = new byte[IV_LENGTH];
		in.read(iv);
		System.out.println(">>>>>>>>red"+Arrays.toString(iv));

		Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding"); //"DES/ECB/PKCS5Padding";"AES/CBC/PKCS5Padding"
		SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		in = new CipherInputStream(in, cipher);
		byte[] buf = new byte[1024];
		int numRead = 0;
		while ((numRead = in.read(buf)) >= 0) {
			out.write(buf, 0, numRead);
		}
		out.close();
	}


	public void copy(int mode, String inputFile, String outputFile, String password) throws Exception {
System.out.println("ttttcopyyyyyyyyyyyyyyyy::"+inputFile);
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(inputFile));
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile));
		if(mode==Cipher.ENCRYPT_MODE){
			encrypt(is, os, password);
		}
		else if(mode==Cipher.DECRYPT_MODE){
			try{
				decrypt(is, os, password);
			}catch(Exception e){
				is.close();
				os.close();
				e.printStackTrace();
			}
		}
		else throw new Exception("unknown mode");
		is.close();
		os.close();
	}


	public void enc(File pfile, String fileName,String key,String fileName1){
		try{
			//check files - just for safety
                   System.out.println(fileName);
                  // System.out.println(filePath);
                   String tempFileName=null;
            String uploadEncPath = "C:\\ittc\\workspace1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dedup\\enc\\";
            System.out.println(fileName1);
                        if(fileName1!=null){
                               tempFileName=uploadEncPath+fileName1+".enc";
                        }else{
                            tempFileName=uploadEncPath+fileName+".enc";
                        }
			String resultFileName="c:\\out\\"+fileName+".dec";

			File file = pfile;
                        System.out.println("aaaaaaaaaaaaaaaaaaaabbb" + file.getPath().replace("\\", "/"));
			/*if(!file.exists()){
				System.out.println("No file "+fileName);
				return;
			}*/
			File file2 = new File(tempFileName);
			File file3 = new File(resultFileName);
			if(file2.exists() || file3.exists()){
				System.out.println("File for encrypted temp file or for the result decrypted file already exists. Please remove it or use a different file name");
				return;
			}
                        String s=file.getPath();
                        		//"c:/dedup/WordCount.java";
                        		
			copy(Cipher.ENCRYPT_MODE, s.replace("\\", "/"), tempFileName, key);
			//copy(Cipher.DECRYPT_MODE, tempFileName, resultFileName, "password12345678");

			System.out.println("Success. Find encrypted and decripted files in current directory");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	
	public void dec(String fileName,String key){
		try{
			//check files - just for safety
                   System.out.println(fileName);
                  // System.out.println(filePath);C
           String uploadEncPath = "C:\\ittc\\workspace1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dedup\\enc\\";
	   		
            String tempFileName=uploadEncPath+fileName+".enc";
			String resultFileName=uploadEncPath+fileName+".dec";

			File file = new File(tempFileName);//pfile;
                        System.out.println("aaaaaaaaaaaaaaaaaaaabbb" + file.getPath().replace("\\", "/"));
			/*if(!file.exists()){
				System.out.println("No file "+fileName);
				return;
			}*/
			
			File file3 = new File(resultFileName);
			if(file3.exists()){
				System.out.println(resultFileName + "File for encrypted temp file or for the result decrypted file already exists. Please remove it or use a different file name");
				//return;
				
			}
                        String s=file.getPath();
                        		//"c:/dedup/WordCount.java";
                        		
			//copy(Cipher.ENCRYPT_MODE, s.replace("\\", "/"), tempFileName, key);
			copy(Cipher.DECRYPT_MODE, tempFileName, resultFileName, key);
		
			//System.out.println("Success. Find decrypted and decripted files in current directory");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	

}