import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Server {
  public static void main(String[] args) {
    int port = 3000;
    MessageStore messageStore = new MessageStore();
    UserStore userStore = new UserStore();
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    try(ServerSocket serverSocket = new ServerSocket(port)){
      while(true){
          System.out.println("waiting for connection");

          Socket socket = serverSocket.accept();
          executorService.execute(new Task(messageStore, userStore, socket));
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}