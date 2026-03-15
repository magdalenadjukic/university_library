/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties konfiguracija;
    
    private Konfiguracija() {
        try {
            konfiguracija = new Properties();
            String path = System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.properties";
            konfiguracija.load(new FileInputStream(path));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Konfiguracija getInstance() {
        if (instance == null)
            instance = new Konfiguracija();
        return instance;
    }
    
    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }
    
    public void saveChanges() {
        try {
            String path = System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.properties";
            konfiguracija.store(new FileOutputStream(path), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}