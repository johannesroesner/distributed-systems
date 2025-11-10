import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Connection extends Thread {
  private Socket socket;
  private BufferedReader reader;
  private PrintWriter writer;
  private UUID uuid;

  public Connection(Socket socket, UUID uuid){
    this.socket = socket;
    this.uuid =uuid;
  }

  public void run(){
    try{

      InputStream in = socket.getInputStream();
      reader = new BufferedReader(new InputStreamReader(in));

      OutputStream out = socket.getOutputStream();
      writer = new PrintWriter(out);

      String username = "", input, token, message;
      String[] parts = new String[2];

        while(true){
          input = reader.readLine();
          token = input.substring(0,4);

          switch(token){
            case "OPEN":
              parts = input.split("#");
              username = parts[1];
              Server.notifyAboutNewUser(username, uuid);
            break;

            case "EXIT":
              socket.close();
            break;

            case "PUBL":
              parts = input.split("#");
              Server.broadcast(username, parts[1], uuid);
            break;

            default:
              System.out.println("error");
              socket.close();
            break;
          }
        }
    } catch (Exception e){}
  }

  public void sendMessage(String message) {
    writer.println(message);
    writer.flush();
  }


  public UUID getUuid(){
    return uuid;
  }
}