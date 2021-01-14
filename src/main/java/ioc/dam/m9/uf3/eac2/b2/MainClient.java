package ioc.dam.m9.uf3.eac2.b2;

/**
 *
 * @author tomas
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainClient {
    
    public static void main(String[] args) {
        
        Socket socket = null;
        Scanner in = null;
        PrintWriter out = null;
        
        try {
            socket = new Socket("localhost", 7777);
            //Llegeix del servidor            
            in = new Scanner(socket.getInputStream());
            //escriu al servidor
            out = new PrintWriter(socket.getOutputStream(), true);
            //llegeix del teclat
            Scanner teclat = new Scanner (System.in);
            
            String mensaje, entrada;
            System.out.println("Conectado al Server.  Finalice con EXIT");

            do {
                System.out.println("Escriu la paraula a traduïr:");
                mensaje =  teclat.nextLine();
                out.println(mensaje);        
                entrada = in.nextLine();                
                String[] traduccions = entrada.split("/");              
                
                for (String traduccio : traduccions) {
                     System.out.println("  " + traduccio);
                }
                
                System.out.println();
                
            } while (!mensaje.equals("EXIT"));            
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {            
            try {
                if(in!=null)in.close();
                if(out!=null)out.close();
                if(socket!=null)socket.close();
            
            } catch (IOException ex) {
                Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}