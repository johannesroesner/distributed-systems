import java.io.*;
import java.net.*;

public class Server {
  public static void main(String[] args) {
    int port = 3000;

    try(ServerSocket serverSocket = new ServerSocket(port)){
      while(true){
        try{
          System.out.println("waiting for connection");

          Socket socket = serverSocket.accept();

          InputStream in = socket.getInputStream();
          BufferedReader reader = new BufferedReader(new InputStreamReader(in));

          OutputStream out = socket.getOutputStream();
          PrintWriter writer = new PrintWriter(out);

          String input = reader.readLine();
          System.out.println("client sent = " +input);

          writer.println("#"+input+"#");
          writer.flush();

        } catch(Exception e){
          e.printStackTrace();
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}