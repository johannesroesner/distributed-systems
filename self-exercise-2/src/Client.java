import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
  public static void main(String[] args) {
    try{
      Socket socket = new Socket("localhost", 3000);
      Scanner scanner = new Scanner(System.in);

      InputStream in = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));

      OutputStream out = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(out);

      String answer;

      writer.println("ADDN#3#4");
      writer.flush();
      answer = reader.readLine();
      System.out.println(answer);

      writer.println("ADDR#5");
      writer.flush();
      answer = reader.readLine();
      System.out.println(answer);

      writer.println("MULR#3");
      writer.flush();
      answer = reader.readLine();
      System.out.println(answer);

      writer.println("EXT");
      writer.flush();


    } catch(Exception e){}
  }
}
