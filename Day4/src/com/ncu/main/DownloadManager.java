package com.ncu.main;
import com.ncu.validator.Validator1;
import com.ncu.status.DownloadStatus;
import java.io.*;
import java.net.*;
import java.util.*;

public class DownloadManager {
    static Scanner scan = new Scanner(System.in);
    public static String urls, path;

    public static int menu(){
        System.out.println("----------------MENU----------------- ");
        System.out.println(" 1. Download a file ");
        System.out.println(" 2. Display Status of previous files ");
        System.out.println(" 3. Exit ");
        System.out.println(" Enter your choice : ");
        int x;
        for (; ; ) {
            try {
                x = scan.nextInt();
                if (x < 1 || x > 3) {
                    System.out.println(" Re-enter your choice : ");
                } else
                    break;
            } catch (Exception e) {
                System.out.println(" Enter an integer !");
                scan.nextLine();
            }
        }
        scan.nextLine();     //clears buffer
        return x;
    }

    public static void download() throws IOException{
        System.out.println(" Enter URL from where file needs to be downloaded : ");
        for (;;) {
            urls = scan.nextLine();
            if (!Validator1.isValidURL(urls)) {
                DownloadStatus.status(urls);
                System.out.println(" Re-enter URL : ");
            }
            else
                break;
        }
        System.out.println(" Enter file directory where file will be saved : ");
        for (;;) {
            path = scan.nextLine();
            if (!Validator1.isValidPath(path)) {
                DownloadStatus.status(urls);
                System.out.println(" Re-enter path : ");
            }
            else
                break;
        }
        URL url = new URL(urls);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream fin = null;

        String filename = url.getFile();
        filename = filename.substring(filename.lastIndexOf('/') + 1);

        for (;;) {
            if (!Validator1.isValidFileName(filename)) {
                System.out.println(" Enter name manually : ");
                filename = scan.nextLine();
                System.out.println(" Do you want to enter extension? (Y for yes or N for no)");
                System.out.println(" By default extension is html ");
                String s3 = scan.nextLine().toLowerCase();
                if (s3.charAt(0)=='y'){
                    System.out.println(" Enter extension : ");
                    String extension = scan.nextLine();
                    filename = filename+"."+extension;
                }
                else
                    filename = filename+".html";
            }
            else
                break;
        }

        try {
            File f = new File(path + "/" + filename);
            if (f.isFile()){
                System.out.println(" File Already Exists! ");
                System.out.println(" Continuing will overwrite already existing file ");
                System.out.println(" Do you want to continue ? (Y for yes or N for no)");
                String s = scan.nextLine().toLowerCase();
                if (s.charAt(0)=='n')
                    System.exit(0);
            }
        }
        catch (Exception e3){
            e3.printStackTrace();
        }
        FileOutputStream fout = new FileOutputStream(path + "/" + filename);
        fin = connection.getInputStream();
        int read = -1;
        byte[] buffer = new byte[4096];

        System.out.print("[PROGRESS]: Downloading file ... ");
        while ((read = fin.read(buffer)) != -1) {
            fout.write(buffer, 0, read);
        }

        System.out.println();
        fin.close();
        fout.close();
        System.out.println("[PROGRESS]: File Downloaded!");

        DownloadStatus.status(urls, path, filename);
    }
    public static void main(String[] args) {

        for (;;) {
            switch(menu())
            {
                case 1:
                    try {
                        download();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    DownloadStatus.DownloadHistory();
                    break;
                case 3:
                    System.exit(0);
            }

            System.out.println(" Do you want to run again ? (Y for yes or N for no)");
            String s = scan.nextLine().toLowerCase();
            System.out.println("-------------------------------------");

            if (s.charAt(0)=='n')
                System.exit(0);

        }
    }

}
