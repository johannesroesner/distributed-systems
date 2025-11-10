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

        String username;

        System.out.println("menu: 1=register a user name 2=send message 3=get all messages 0=end");
        int key = Integer.parseInt(scanner.nextLine());

        switch(key){
          case 1:
            System.out.println("set username:");
            username = scanner.nextLine();
            writer.println("REG" + username);
            writer.flush();
          break;
          case 2:
            System.out.println("your username:");
            username = scanner.nextLine();

            System.out.println("who will receive this message:");
            String to =  scanner.nextLine();

            System.out.println("enter message:");
            String message = scanner.nextLine();

            writer.println("SND" + username + "#" + to + "#" + message);
            writer.flush();
          break;
          case 3:
            System.out.println("your username:");
            username = scanner.nextLine();

            writer.println("RCV" + username);
            writer.flush();

            String answer = reader.readLine();
            System.out.println(answer);
          break;
        }

    } catch(Exception e){}
  }

}