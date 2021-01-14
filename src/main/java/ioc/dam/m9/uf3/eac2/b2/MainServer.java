package ioc.dam.m9.uf3.eac2.b2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author tomas
 */
public class MainServer {   
    
    public static void main(String[] args) {         
        
        int port = 7777;
        int maxConnexions = 10; 
        ServerSocket servidor = null; 
        Socket socket = null;
        int numClients = 0;       
        
        try {            
            servidor = new ServerSocket(port, maxConnexions);
            System.out.println("Servidor a l'espera de connexions");
        
	    while (true) {
                System.out.println("Esperant client...");
	       	socket = servidor.accept();
                numClients++;
                ThreadClient tc = new ThreadClient(socket);
                System.out.println("Connectat client " + numClients + " amb la IP " + socket.getInetAddress().getHostName());    
                tc.start();                           
	    }            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  finally{
            try {
                if(socket!=null)socket.close();
                if(servidor!=null)servidor.close();
            } catch (IOException ex) {
                System.out.println("Error al tancar el servidor: " + ex.getMessage());
            }
        }
    }
    
}