import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class MessageStore {
   private List<Message> messageStore = new ArrayList<>();

   public synchronized void saveMessage(Message newMessage){
     messageStore.add(newMessage);
   }

  public synchronized List<Message> getMessagesForUsername(String username) {
     return messageStore.stream()
                        .filter(message -> message.getTo().equals(username))
                        .collect(Collectors.toList());
  }
}