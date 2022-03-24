package com.example.linguspring;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    private final String filename = "data.csv";

    List<Entry> readAllFile() throws IOException {
        return Files.readAllLines(Paths.get(filename))
                .stream()
                .map(CsvConverter::convert)
                .collect(Collectors.toList());
    }

    void saveEntries(List<Entry> entries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Entry entry : entries) {
            writer.write(entry.toString());
            writer.newLine();
        }
        writer.close();
    }

    private static class CsvConverter {
        static Entry convert (String text) {
            String[] split = text.split(";");
            String original = split[0];
            String translated = split[1];
            return new Entry(original, translated);
        }
    }
}
