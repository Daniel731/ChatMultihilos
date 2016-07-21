package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Mensajes extends Thread {
    
    // Atributos
    /**************/
    Socket socket;
    /**************/
    
    
    // Contructor
    /********************************/
    public Mensajes(Socket socket) {
        this.socket = socket;
    }
    /********************************/

    
    // Logica
    /******************************************************************************/
    // Metodo requerido para la ejecucion de la aplicacion
    @Override
    public void run() {
        super.run();
        
        String mensaje = "";
        
        while (true) {
            try {
                mensaje = new DataInputStream(socket.getInputStream()).readUTF();
                
                for (Mensajes i: Servidor.alMensajes) {
                    i.enviar(mensaje);
                }
            } catch (IOException e) {}
        }
    }
    
    public void enviar(String mensaje) {
        try {
            new DataOutputStream(socket.getOutputStream()).writeUTF(mensaje);
        } catch (IOException e) {}
    }
    /******************************************************************************/
    
}
