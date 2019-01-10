package socket.client;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args)
            throws IOException {
        String ip = "localhost";
        int port = 7458;
        Socket socket = new Socket(ip, port);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Class Name:"); //Sample
        String className = scanner.next();
        System.out.println("Method Name:"); //print
        String methodName = scanner.next();
        System.out.println("Parameter:"); //a String input you wish
        String parameter = scanner.next();
        className = "ir.socket.server." + className;

        JSONObject message = new JSONObject();
        message.put("className", className);
        message.put("methodName", methodName);
        message.put("parameter", parameter);

        try {
            //send request to server
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            oos.flush();

            //receive answer
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String ans = br.readLine();
            System.out.println(ans);

            oos.close();
            br.close();
        } finally {
            socket.close();
        }
    }
}
