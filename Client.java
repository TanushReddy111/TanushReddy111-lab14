import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client extends Thread{

    Socket socket;
    PrintWriter pw;
     Scanner scanner ;

    public Client(String serverIp, int port){
      
        try {
            socket = new Socket(serverIp,port);
            pw = new PrintWriter(socket.getOutputStream(), true);
           scanner = new Scanner(socket.getInputStream());
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
                scanner.close();
                pw.close();
                socket.close();
            } catch (Exception e) {
            }
            
       }
    }


    public String request(String number){
        pw.println(number);
        pw.flush();

        try {
            if(scanner.hasNextLine()){
                return scanner.nextLine();
            }
            
        } catch (Exception e) {
            
        }
        return "There was an exception on the server";
        
    }
}