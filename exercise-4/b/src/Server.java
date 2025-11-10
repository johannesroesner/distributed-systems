import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Server {
  static List<Connection> connectionList = new ArrayList<>();

  public static void main(String[] args) {
    int port = 3000;

    try(ServerSocket serverSocket = new ServerSocket(port)){
      Connection newConnection;
      while(true){
          System.out.println("waiting for connection");
          Socket socket = serverSocket.accept();
          newConnection = new Connection(socket, UUID.randomUUID());
          connectionList.add(newConnection);
          newConnection.start();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public static void broadcast(String username, String message, UUID uuid){
      for(Connection connection: connectionList){
        if(connection.getUuid().toString().equals(uuid.toString())) continue;
        connection.sendMessage("SHOW#" + username +"#" + message );
      }
  }
  public static void notifyAboutNewUser(String username, UUID uuid){
    for(Connection connection: connectionList){
      if(connection.getUuid().toString().equals(uuid.toString())) continue;
      connection.sendMessage("ADMN#" + username + " has entered the chat." );
    }
  }
}