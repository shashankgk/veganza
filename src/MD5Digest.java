/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MD5Digest {

	public static String getFileData(String fp) {

		BufferedReader br = null;
                StringBuffer sb = new StringBuffer();

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fp));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
                                sb.append(sCurrentLine);
			}
                        
                        

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
                
                return sb.toString();

	}
}
    
