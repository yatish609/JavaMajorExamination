package com.ncu.main;
import com.ncu.validator.Validator1;
import com.ncu.status.DownloadStatus;
import java.io.*;
import java.net.*;
import java.util.*;

public class DownloadManager {

    public static String urls, path;
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println(" Enter URL from where file needs to be downloaded : ");
        urls = scan.nextLine();
        if (!Validator1.isValidURL(urls))
        {
            DownloadStatus.status(urls);
            System.exit(0);
        }
        System.out.println(" Enter file directory where file will be saved : ");
        path = scan.nextLine();
        if (!Validator1.isValidPath(path))
        {
            DownloadStatus.status(urls);
            System.exit(0);
        }
        URL url = new URL(urls);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream fin = null;

        String filename = url.getFile();
        filename = filename.substring(filename.lastIndexOf('/') + 1);

        FileOutputStream fout = new FileOutputStream(path + "/" + filename);
        fin = connection.getInputStream();
        int read = -1;
        byte[] buffer = new byte[4096];

        System.out.print("[PROGRESS]: Downloading file ... ");
        while((read = fin.read(buffer)) != -1){
            fout.write(buffer, 0, read);
        }

        System.out.println();
        fin.close();
        fout.close();
        System.out.println("[PROGRESS]: File Downloaded!");

        DownloadStatus.status(urls,path,filename);
    }

}
