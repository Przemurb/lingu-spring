package com.example.linguspring;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Controller
public class LinguController {
    private static final int UNDEFINED = -1;
    private static final int ADD = 0;
    private static final int TEST = 1;
    private static final int EXIT = 2;

    private final EntryRepository entryRepository;
    private final FileService fileService;
    private final Scanner scanner;

    public LinguController(EntryRepository entryRepository, FileService fileService, Scanner scanner) {
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.scanner = scanner;
    }

    void mainLoop() {
        System.out.println("LINGU - program do nauki słówek");
        int option = UNDEFINED;
        while (option != EXIT) {
            printMenu();
            option = choseOption();
            executeOption(option);
        }
    }

    private void executeOption(int option) {
        switch (option) {
            case ADD -> addEntry();
            case TEST -> test();
            case EXIT -> exit();
            default -> System.out.println("Nie ma takiej opcji wyboru");
        }
    }

    private void exit() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            System.out.println("zapisano stan aplikacji");
        } catch (IOException e) {
            System.out.println("Nie udało sie zapisać zmian!");
        }
        System.out.println("Bye! Bye!");
    }

    private void test() {
        if(entryRepository.isEmpty()) {
            System.out.println("Baza jest pusta. Dodaj przynajmniej jedno wyrażenie!");
            return;
        }
        final int testSize = entryRepository.size() > 10 ? 10 : entryRepository.size();
        Set<Entry> randomEntries = entryRepository.getRandomEntry(testSize);
        int correct = 0;
        for (Entry entry : randomEntries) {
            System.out.printf("Podaj tłumaczenia dla: %s \n", entry.getOriginal());
            String translation = scanner.nextLine();
            if(translation.equals(entry.getTranslation())) {
                correct++;
                System.out.println("Odpowiedź poprawna");
            } else {
                System.out.println("Błędna odpowiedź - " + entry.getTranslation());
            }
        }
        System.out.printf("Twój wynik: %d/%d\n", correct, testSize);
    }

    private void addEntry() {
        System.out.println("Podaj oryginalne słowo lub frazę:");
        String original = scanner.nextLine();
        System.out.println("Podaj tłumaczenie:");
        String translated = scanner.nextLine();
        Entry entry = new Entry(original, translated);
        entryRepository.add(entry);
    }

    private int choseOption() {
        int option;
        try{
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            option = UNDEFINED;
        } finally {
            scanner.nextLine();
        }
        if(option > UNDEFINED && option <= EXIT) {
            return option;
        } else {
            option = UNDEFINED;
        }
        return option;
    }

    private void printMenu() {
        System.out.println("Wybierz opcję:");
        System.out.println("0 - Dodaj frazę");
        System.out.println("1 - Test");
        System.out.println("2 - Koniec programu");
    }
}
