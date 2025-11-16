
import java.net.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;


public class Server{
    ServerSocket serverSocket;
    volatile ArrayList<LocalDateTime> connectedTimes =new ArrayList<>();

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);

        } catch (Exception e) {
            
        }

    }

    public void serve(int count){
        for (int i =0; i< count; i++){
            try {

                Socket socket = serverSocket.accept();
                
                synchronized(this)
                {
                connectedTimes.add(LocalDateTime.now());
                }
                

                new ClientHandler(socket).start();
                    
                
            } catch (Exception e) {

            }

        }
    }

    public void disconnect(){
        try{
            serverSocket.close();
        }
        catch(Exception e){

        }
    }

    public  ArrayList getConnectedTimes(){
        synchronized (this) {
             Collections.sort(connectedTimes);
        }
       
        return connectedTimes;
    }

}

