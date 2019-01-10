package socket.server;

import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)
            throws IOException,
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InstantiationException,
            InvocationTargetException {

        String methodName = null;
        String className = null;
        String parameter = null;
        ServerSocket listener = new ServerSocket(7458);
        System.out.println("S: Server is waiting...");
        Socket socket = listener.accept();
        System.out.println("S: Client connected!");
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object messageObj = ois.readObject();

            if (messageObj != null && messageObj instanceof JSONObject) {
                JSONObject message = (JSONObject) messageObj;
                className = (String) message.get("className");
                methodName = (String) message.get("methodName");
                parameter = (String) message.get("parameter");
            }

            Class clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod(methodName, String.class);
            method.setAccessible(true);
            String answer = (String) method.invoke(obj, parameter);

            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter out = new PrintWriter(osw);
            out.println(answer);
            out.flush();
            System.out.println("Answered to Client!");

            ois.close();
            out.close();
        } finally {
            socket.close();
            listener.close();
        }
    }
}
