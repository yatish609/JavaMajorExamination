package com.ncu.main;
import com.ncu.validator.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class DownloadManager {

    public static String urls, path;
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println(" Enter URL from where file needs to be downloaded : ");
        urls = scan.nextLine();
        boolean b = Validator1.isValidURL(urls);
        if (!b)
        {
            System.exit(0);
        }
        URL url = new URL(urls);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream fin = null;

        String filename = url.getFile();
        filename = filename.substring(filename.lastIndexOf('/') + 1);

        System.out.println(" Enter file directory where file will be saved : ");
        path = scan.nextLine();
        b = Validator1.isValidPath(path);
        if (!b)
        {
            System.exit(0);
        }

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
    }

}
