/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Posiljalac {

    private ObjectOutputStream oos;

    public Posiljalac(Socket s) {
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void posalji(Object obj) {
        try {
            oos.writeObject(obj);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
