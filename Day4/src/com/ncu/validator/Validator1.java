package com.ncu.validator;
import com.ncu.exceptions.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Validator1 {

    public static boolean isValidPath (String path)
    {
        File f = new File(path);
        if (!f.isDirectory())
        {
            Logger logger = Logger.getLogger(Validator1.class);
            FileInputStream input = null;
            Properties prop = new Properties();
            PropertyConfigurator.configure("D:/JavaMajorProject/Day4/configs/logger/logger.properties");
            try {
                input = new FileInputStream("D:/JavaMajorProject/Day4/configs/constants/exceptions.properties");
                prop.load(input);
                throw new InvalidDirectory(" Invalid Directory entered ");
            }
            catch (InvalidDirectory e2){
                logger.error("\n \n"+prop.getProperty("InvalidDirectory")+"\n");
            }
            catch (Exception e1){
            }
            return false;
        }
        return true;
    }
    public static boolean isValidURL(String url)
    {
        try {
            new URL(url).toURI();
        }
        catch (Exception e) {
            Logger logger = Logger.getLogger(Validator1.class);
            FileInputStream input = null;
            Properties prop = new Properties();
            PropertyConfigurator.configure("D:/JavaMajorProject/Day4/configs/logger/logger.properties");
            try {
                input = new FileInputStream("D:/JavaMajorProject/Day4/configs/constants/exceptions.properties");
                prop.load(input);
                throw new InvalidURL(" Invalid URL entered ");
            }
            catch (InvalidURL e2){
                logger.error("\n \n"+prop.getProperty("InvalidURL")+"\n");
            }
            catch (Exception e1){
            }
            return false;
        }
        return true;
    }

    public static boolean isValidFileName(String filename) {
        File f = new File(filename);
        try {
            if (f.createNewFile()) {
                f.delete();
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(Validator1.class);
            FileInputStream input = null;
            Properties prop = new Properties();
            PropertyConfigurator.configure("D:/JavaMajorProject/Day4/configs/logger/logger.properties");
            try {
                input = new FileInputStream("D:/JavaMajorProject/Day4/configs/constants/exceptions.properties");
                prop.load(input);
                throw new InvalidFileName(" File at the URL has invalid name! ");
            }
            catch (InvalidFileName e2){
                logger.error("\n \n"+prop.getProperty("InvalidFileName")+"\n");
            }
            catch (Exception e3){
            }
            return false;
        }
        return true;
    }
}
