package com.ncu.status;
import java.io.*;

public class DownloadStatus {
    public static void status(String urls) {
        try {
            File f = new File("D:/JavaMajorProject/Day4/downloadstatus/downloadstatus.txt");
            if (!f.isFile()){
                f.createNewFile();
                BufferedWriter bout= new BufferedWriter(new FileWriter(f));
                bout.write("\t \t \t URL \t \t \t \t \t \t \t \t \t Status ");
                bout.close();
            }
            BufferedWriter bout= new BufferedWriter(new FileWriter(f,true));
            bout.newLine();
            bout.write(urls);
            bout.append("\t \t \t Download Failed!");
            bout.newLine();
            bout.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void status(String urls, String path,String filename) {
        try {
            File f = new File("D:/JavaMajorProject/Day4/downloadstatus/downloadstatus.txt");
            if (!f.isFile()){
                f.createNewFile();
                BufferedWriter bout= new BufferedWriter(new FileWriter(f));
                bout.write(" URL \t \t \t \t \t \t \t \t \t Status ");
                bout.close();
            }
            BufferedWriter bout= new BufferedWriter(new FileWriter(f,true));
            bout.newLine();
            bout.write(urls);
            File g = new File(path+"/"+filename);
            if(g.isFile())
            {
                bout.append("\t \t \t Download Completed! ");
            }
            else {
                bout.append("\t \t \t Download Failed! ");
            }
            bout.newLine();
            bout.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void DownloadHistory() {
        try {
            File f = new File("D:/JavaMajorProject/Day4/downloadstatus/downloadstatus.txt");
            FileReader fin = new FileReader(f);
            BufferedReader bin = new BufferedReader(fin);
            String sr;
            System.out.println("\n Download History \n");
            System.out.println("---------------------------------------------------------");
            while ((sr = bin.readLine()) != null) {
                System.out.println(sr);
            }
            bin.close();
            System.out.println("---------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(" No Previous Download History Exists !");
        }
    }
}

 
