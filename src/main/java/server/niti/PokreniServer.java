/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Marija
 */
public class PokreniServer extends Thread {
    private ServerSocket serverskiSoket;

    public PokreniServer() {
    }

    @Override
    public void run() {
        System.out.println("Povezivanje servera...");
        try {
            this.serverskiSoket = new ServerSocket(9000);
            System.out.println("Server se povezao, ceka se klijent...");
            while (true) {
                Socket s = serverskiSoket.accept();
                System.out.println("Klijent se povezao!");
                ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s);
                nit.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerSocket getServerskiSoket() {
        return serverskiSoket;
    }

    public void setServerskiSoket(ServerSocket serverskiSoket) {
        this.serverskiSoket = serverskiSoket;
    }


}
