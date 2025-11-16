import java.io.PrintWriter;
import java.net.*;

public class Client extends Thread{
    String serverIp;
    int port;
    Socket socket;
    PrintWriter pw;

    public Client(String serverIp, int port){
        this.serverIp = serverIp;
        this.port = port;
        try {
            socket = new Socket(serverIp,port);
            pw = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {

        }
    }

    public Socket getSocket(){
        return this.socket;
    }

    public void handshake(){
        pw.println("12345");
        pw.flush();
    }
     public void disconnect(){
       if (pw != null)
       {

            try {
                pw.close();
                socket.close();
            } catch (Exception e) {
            }
            
       }
    }
}