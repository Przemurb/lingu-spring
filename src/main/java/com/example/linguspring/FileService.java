package com.example.linguspring;

import com.example.linguspring.config.AppConfiguration;
import com.example.linguspring.encryption.Encryption;
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
    private final AppConfiguration config;
    private final Encryption encryption;

    public FileService(AppConfiguration config, Encryption encryption) {
        this.config = config;
        this.encryption = encryption;
    }

    List<Entry> readAllFile() throws IOException {
        return Files.readAllLines(Paths.get(config.getFilename()))
                .stream()
                .map(encryption::decrypt)
                .map(CsvConverter::convert)
                .collect(Collectors.toList());
    }

    void saveEntries(List<Entry> entries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(config.getFilename()));
        for (Entry entry : entries) {
            writer.write(encryption.encrypt(entry.toString()));
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
