package Translator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class ThreadClient extends Thread {
    private Socket client;
    private Scanner in;
    private PrintWriter out;
    

    public ThreadClient(Socket client) {
        try {
            this.client = client;
            this.in = new Scanner(client.getInputStream());
            this.out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
    @Override
    public void run() {
        String msg;
        SoftCatalaClient soft = new SoftCatalaClient();
        String traduccio;    
        msg = in.nextLine();
        
        while(!msg.equals("EXIT")){           
            traduccio = soft.translate(msg);            
            out.println("TRADUCCIÓ> " + traduccio);
            System.out.println("Missatge rebut: "+traduccio);
            msg = in.nextLine();           
        }
        out.println("Tancant connexió");
        
        try {
            System.out.println("Client amb la IP " + client.getInetAddress().getHostName() + " desconnectat.");
            in.close();
            out.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
}