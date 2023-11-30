package csv;

import person.Client;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClientCsvCreator {
    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1, "Alice", "1234"));
        clients.add(new Client(2, "Bob", "5678"));
        clients.add(new Client(3, "Charlie", "9012"));

        String csvFilePath = "E:\\note\\clients.csv";

        writeClientsToCSV(clients, csvFilePath);
    }

    public static void writeClientsToCSV(List<Client> clients, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            for (Client client : clients) {
                writer.println(client.getPriority() + "," + client.getName() + "," + client.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

