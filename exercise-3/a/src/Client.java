import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
  public static void main(String[] args) {
    try{
      Scanner scanner = new Scanner(System.in);
      Socket socket = new Socket("localhost",3000);

      InputStream in = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));

      OutputStream out = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(out);


      System.out.println("please enter a word:");
      String input = scanner.nextLine();

      writer.println(input);
      writer.flush();

      String answer = reader.readLine();
      System.out.println("server answered = " + answer);

    } catch(Exception e){}
  }
}