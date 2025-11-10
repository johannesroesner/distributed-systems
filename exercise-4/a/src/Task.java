import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Task implements Runnable {
  private MessageStore messageStore;
  private UserStore userStore;
  private Socket socket;

  public Task(MessageStore messageStore, UserStore userStore, Socket socket){
    this.messageStore = messageStore;
    this.userStore = userStore;
    this.socket = socket;
  }

  public void run(){
    try{
      InputStream in = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));

      OutputStream out = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(out);

      String input = reader.readLine();
      String actionToken = input.substring(0, 3);

      switch(actionToken) {
        case "REG":
          userStore.register(input.substring(3));
        break;

        case "SND":
          String inputContent =  input.substring(3);
          String[] parts = inputContent.split("#");
          if(!userStore.auth(parts[0]) || !userStore.auth(parts[1])) socket.close();
          messageStore.saveMessage(new Message(parts[0],parts[1],parts[2]));
        break;

        case "RCV":
          if(!userStore.auth(input.substring(3))) socket.close();
          List<Message> messages = messageStore.getMessagesForUsername(input.substring(3));
          for(int i = 0; i < messages.size(); i++){
            writer.println(messages.get(i).getFrom() + " : " + messages.get(i).getContent());
          }
          writer.flush();
        break;

        default:
          System.err.println("error: unvalid action");
        break;
      }
      socket.close();
    } catch(Exception e){}
  }
}