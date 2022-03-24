package com.example.linguspring;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Repository
public class EntryRepository {
    private final Random random;

    private List<Entry> entries;

    public EntryRepository(FileService fileService, Random random) {

        try {
            this.entries = fileService.readAllFile();
        } catch (IOException e){
            entries = new ArrayList<>();
        }
        this.random = random;
    }

    List<Entry> getAll() {
        return entries;
    }

    Set<Entry> getRandomEntry(int number) {
        Set<Entry> randomEntries = new HashSet<>();
        while(randomEntries.size() < number && randomEntries.size() < entries.size()) {
            randomEntries.add(entries.get(random.nextInt(entries.size())));
        }
        return randomEntries;
    }

    void add(Entry entry) {
        entries.add(entry);
    }

    boolean isEmpty() {
        return entries.isEmpty();
    }

    public int size() {
        return entries.size();
    }
}
