import java.io.*;
import java.net.*;
import java.util.*;
public class ClientHandler extends Thread{
    Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            if(isValidClient(scanner)){

                //System.out.println(true);
                if(scanner.hasNextLine()){
                    String toWrite= factor(scanner.nextLine());

                    PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
                    pw.println(toWrite);
                    pw.flush();
                    //pw.close();
                }
            }
            else{
                PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
                    pw.println("couldn't handshake");
                    pw.flush();
            }
            //scanner.close();
            
        } catch (Exception e) {
        }
        finally{
            try {

                socket.close();
                
            } catch (Exception e) {
            }
            
        }

                
}

    public boolean isValidClient(Scanner scanner){
        try {
            //System.out.println("before has Next Line");
            if(scanner.hasNextLine()){
                //System.out.println("has Next Line");
                if(scanner.nextLine().equals("12345")){
                    return true;
                }
            }

            //System.out.println("no Next Line");

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            
        }
        
    }
    
    public String factor(String num){
        //System.out.println(num);
        int count=0;
        int number = Integer.parseInt(num);


        for (int i =1; i<number+1; i++ ){
            if(number%i == 0){
                count++;
            }
        }

        return "The number "+num+" has "+count+" factors";

    }
}