package hilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Servidor extends Thread {
    
    static ArrayList<Mensajes> alMensajes;

    @Override
    public void run() {
        super.run();
        
        alMensajes = new ArrayList<>();
        
        try {
            ServerSocket server = new ServerSocket(9000);
            
            while (true) {
                Mensajes mensajes = new Mensajes(server.accept());
                mensajes.start();
                
                alMensajes.add(mensajes);
            }
        } catch (IOException e) {}
    }
    
    public static void main(String[] args) {
        new Servidor().start();
    }

}