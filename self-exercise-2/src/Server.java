import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

/* Create a server and a protocol that covers all basic arithmetic operations
 * and where each socket connection also has a temporary storage.
 * 
 * For example:
 * first request 3 + 4;
 * server responds 7;
 * second request * 3;
 * server responds 21 ;
 */


public class Server {
  public static void main(String[] args) {
    int port = 3000;

    try(ServerSocket serverSocket = new ServerSocket(port)){
      while(true){
        System.out.println("waiting for connection");
        Socket socket = serverSocket.accept();

        new Thread(() -> {
          try{
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            String input, token="init";
            char flag;
            String[] parts = new String[3];
            int a = 0, b = 0, result = 0;

            while(!token.equals("EXT")){
              input = reader.readLine();
              parts = input.split("#");
              token = parts[0].substring(0,3);
              flag = parts[0].charAt(3);

              if(flag == 'N'){
                a = Integer.parseInt(parts[1]);
                b = Integer.parseInt(parts[2]);
              } else if (flag == 'R') {
                a = result;
                b = Integer.parseInt(parts[1]);
              }

              switch(token){
                case "ADD":
                  result = a + b;
                  writer.println(result);
                break;

                case "SUB":
                  result = a - b;
                  writer.println(result);
                break;

                case "MUL":
                  result = a * b;
                  writer.println(result);
                break;

                case "DIV":
                  if(b == 0) {
                    writer.println("error");
                  } else {
                  result = a/b;
                  writer.println(result);
                  }
                break;

                default:
                  writer.println("error");
                break;
              }
              writer.flush();
            }
            socket.close();
          }catch(Exception e){}
        }).start();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}