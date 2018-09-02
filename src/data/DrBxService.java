package data;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DrBxService {
    private static final String ACCESS_TOKEN = "eSBgkgido1AAAAAAAAAAERnRdqwWImIvx4xeDheOfY8jf8AVMzgGVPtV0t3oH6QO";

    public void upload(String path,String fn) throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

        // Upload "test.txt" to Dropbox
        try (InputStream in = new FileInputStream(path+fn)) {
            FileMetadata metadata = client.files().uploadBuilder("/"+fn)
                .uploadAndFinish(in);
        }
    }
    
    
    public static void downloadTest(){
    	 try
         {
    		 
    		   // Create Dropbox client
    	        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
    	        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    	        // Get current account info
    	        FullAccount account = client.users().getCurrentAccount();
    	        System.out.println(account.getName().getDisplayName());
    	        
    	        
             //output file for download --> storage location on local system to download file
             OutputStream downloadFile = new FileOutputStream("C:\\.....");
             try
             {
             FileMetadata metadata = client.files().downloadBuilder("/root or foldername here/Koala.jpg")
                     .download(downloadFile);
             }
             finally
             {
                 downloadFile.close();
             }
         }
         //exception handled
         catch (DbxException e)
         {
            e.printStackTrace();
             //JOptionPane.showMessageDialog(null, "Unable to download file to local system\n Error: " + e);
         }
         catch (IOException e)
         {
        	 e.printStackTrace();
             //error downloading file
             //JOptionPane.showMessageDialog(null, "Unable to download file to local system\n Error: " + e);
         }
    
    }
}