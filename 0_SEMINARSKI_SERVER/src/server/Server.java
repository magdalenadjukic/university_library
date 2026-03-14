/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author DELL
 */
public class Server extends Thread{
    
    boolean kraj=false;
    ServerSocket ss;
    List<ObradaKlijentskihZahteva> listaKlijenti;

    public Server() {
        listaKlijenti=new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            ss=new ServerSocket(9000);
            
            while(!kraj){
                Socket s=ss.accept();
                System.out.println("povezan klijent");
                    
                ObradaKlijentskihZahteva okz=new ObradaKlijentskihZahteva(s);
                listaKlijenti.add(okz);
                okz.start();
           }
        } catch (IOException ex) {
           //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
    
    public void zaustaviServer() {
        kraj =true;
        try {
            for(ObradaKlijentskihZahteva k:listaKlijenti){
                k.prekini();
            }
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
