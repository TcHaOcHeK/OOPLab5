import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8030;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Подключено к серверу");

            String sonnets;
            while ((sonnets = in.readLine()) != null) {
                System.out.println(sonnets);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при подключении к серверу: " + e.getMessage());
        }
    }
}