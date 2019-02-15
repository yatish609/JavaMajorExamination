package com.ncu.status;
import java.io.*;

public class DownloadStatus {
    public static void status(String urls) {
        try {
            File f = new File("D:/JavaMajorProject/Day3/downloadstatus/downloadstatus.txt");
            if (!f.exists()){
                f.createNewFile();
                BufferedWriter bout= new BufferedWriter(new FileWriter(f));
                bout.write(" URL \t \t \t Status ");
            }
            BufferedWriter bout= new BufferedWriter(new FileWriter(f,true));
            bout.newLine();
            bout.write(" " + urls + "");
            bout.append("\t \t \t File Does not Exist! \t \t \t ");
            bout.newLine();
            bout.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void status(String urls, String path,String filename) {
        try {
            File f = new File("D:/JavaMajorProject/Day3/downloadstatus/downloadstatus.txt");
            if (!f.exists()){
                f.createNewFile();
                BufferedWriter bout= new BufferedWriter(new FileWriter(f,true));
                bout.write(" URL \t \t \t Status ");
            }
            BufferedWriter bout= new BufferedWriter(new FileWriter(f,true));
            bout.newLine();
            bout.write(" " + urls + "");
            File g = new File(path+"/"+filename);
            if(g.exists())
            {
                bout.append("\t \t \t File Exists! ");
            }
            else {
                bout.append("\t \t \t File Does not Exist! ");
            }
            bout.newLine();
            bout.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

 
