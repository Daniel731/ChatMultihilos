package hilos;

import interfaz.Chat;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente extends Thread {
    
    Socket socket;
    DataOutputStream output;

    @Override
    public void run() {
        super.run();
        
        DataInputStream input = null;
        
        try {
            socket = new Socket("127.0.0.1", 9000);
            
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {}
        
        while (true) {
            try {
                Chat.taMensajes.append(input.readUTF());
            } catch (IOException e) {}
        }
    }
    
    public void enviar(String mensaje) {
        try {
            output.writeUTF(mensaje);
        } catch (IOException e) {}
    }
    
}
