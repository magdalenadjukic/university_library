/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Primalac {

    
    private ObjectInputStream ois;

    public Primalac(Socket s) {
        try {
            ois = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Object primi() {
        try {
            return ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } 
    }
}
