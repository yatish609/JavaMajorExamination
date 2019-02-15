package com.ncu.validator;
import com.ncu.exceptions.*;
import java.io.*;
import java.net.*;
import java.nio.file.Paths;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Validator1 {

    public static boolean isValidPath (String path)
    {
        if (!Paths.get(path).toFile().isDirectory())
        {
            Logger logger = Logger.getLogger(Validator1.class);
            FileInputStream input = null;
            Properties prop = new Properties();
            PropertyConfigurator.configure("D:/JavaMajorProject/Day3/configs/logger/logger.properties");
            try {
                input = new FileInputStream("D:/JavaMajorProject/Day3/configs/constants/exceptions.properties");
                prop.load(input);
                throw new InvalidDirectory(" Invalid Directory entered ");
            }
            catch (InvalidDirectory e2){
                logger.error("\n \n"+e2+prop.getProperty("InvalidDirectory")+"\n");
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
            return true;
        }
        catch (Exception e) {
            Logger logger = Logger.getLogger(Validator1.class);
            FileInputStream input = null;
            Properties prop = new Properties();
            PropertyConfigurator.configure("D:/JavaMajorProject/Day3/configs/logger/logger.properties");
            try {
                input = new FileInputStream("D:/JavaMajorProject/Day3/configs/constants/exceptions.properties");
                prop.load(input);
                throw new InvalidURL(" Invalid URL entered ");
            }
            catch (InvalidURL e2){
                logger.error("\n \n"+e2+prop.getProperty("InvalidURL")+"\n");
            }
            catch (Exception e1){
            }
            return false;
        }
    }
}
