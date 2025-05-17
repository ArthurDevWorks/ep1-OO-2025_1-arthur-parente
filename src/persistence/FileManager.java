package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void saveToFile(String filename, List<String> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static List<String> readFromFile(String filename) throws IOException {
        List<String> data = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) {
            return data;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    data.add(line);
                }
            }
        }
        return data;
    }
}
