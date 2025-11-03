import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class Worker implements Runnable {
  private Socket socket;
  private List<String> lines;

  public Worker(Socket socket){
    this.socket = socket;
    try{lines = Files.readAllLines(Paths.get("src/index.html"));} catch(Exception e){}
  }

  public void run(){
    try{
      OutputStream out = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(out);
 
      for(String line : lines){
        writer.println(line);
      }

      writer.flush();

    } catch(Exception e){}
  }
}