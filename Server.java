
import java.net.*;


public class Server{
    ServerSocket serverSocket;

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

}

