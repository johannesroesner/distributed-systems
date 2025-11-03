import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
  public static void main(String[] args) {
    int port = 80;
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    startServer(port, executorService);
  }

  public static void startServer(int port, ExecutorService executorService){
    try(ServerSocket serverSocket = new ServerSocket(port)){
      while(true){
        try{
          System.out.println("waiting for connection");

          Socket socket = serverSocket.accept();

          Worker worker = new Worker(socket);
          executorService.execute(worker);

        } catch(Exception e){
          e.printStackTrace();
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}