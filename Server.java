import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    private static final int PORT = 8030;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            while (true) {
                try (Socket client = server.accept();
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
                    System.out.println("Подключился клиент: " + client.getInetAddress());

                    List<String> sonnets = readSonnets("sonnets.txt");
                    String randomSonnets = getRandSon(sonnets);
                    out.println(randomSonnets);
                } catch (IOException e) {
                    System.err.println("Ошибка при обработке клиента: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Не удалось запустить сервер: " + e.getMessage());
        }
    }

    private static List<String> readSonnets(String filePath) throws IOException {
        List<String> sonnets = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (sb.length() > 0) {
                        sonnets.add(sb.toString());
                        sb.setLength(0);
                    }
                } else {
                    sb.append(line).append("\n");
                }
            }
            if (sb.length() > 0) {
                sonnets.add(sb.toString());
            }
        }

        return sonnets;
    }

    private static String getRandSon(List<String> sonnets) {
        Random rand = new Random();
        return sonnets.get(rand.nextInt(sonnets.size()));
    }
}