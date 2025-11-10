import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class UserStore {
  private List<String> userStore = new ArrayList<>();

  public void register(String username){
    userStore.add(username);
  }

  public boolean auth(String username) {
    return userStore.stream()
                    .anyMatch(user -> user.equals(username));
  }
}