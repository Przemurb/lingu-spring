package com.example.linguspring;

import com.example.linguspring.consoleWriter.ConsoleOutputWriter;
import org.springframework.stereotype.Controller;

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
    private final ConsoleOutputWriter text;

    public LinguController(EntryRepository entryRepository, FileService fileService, Scanner scanner, ConsoleOutputWriter text) {
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.scanner = scanner;
        this.text = text;
    }

    void mainLoop() {
        text.print("LINGU - program do nauki słówek");
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
            default -> text.print("Nie ma takiej opcji wyboru");
        }
    }

    private void exit() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            text.print("Zapisano stan aplikacji");
        } catch (IOException e) {
            text.print("Nie udało sie zapisać zmian!");
        }
        text.print("Bye! Bye!");
    }

    private void test() {
        if(entryRepository.isEmpty()) {
            text.print("Baza jest pusta. Dodaj przynajmniej jedno wyrażenie!");
            return;
        }
        final int testSize = entryRepository.size() > 10 ? 10 : entryRepository.size();
        Set<Entry> randomEntries = entryRepository.getRandomEntry(testSize);
        int correct = 0;
        for (Entry entry : randomEntries) {
            text.print("Podaj tłumaczenia dla: " + entry.getOriginal());
            String translation = scanner.nextLine();
            if(translation.equals(entry.getTranslation())) {
                correct++;
                text.print("Odpowiedź poprawna");
            } else {
                text.print("Błędna odpowiedź - " + entry.getTranslation());
            }
        }
        text.print(String.format("Twój wynik: %d/%d\n", correct, testSize));
    }

    private void addEntry() {
        text.print("Podaj oryginalne słowo lub frazę:");
        String original = scanner.nextLine();
        text.print("Podaj tłumaczenie:");
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
        text.print("Wybierz opcję:");
        text.print("0 - Dodaj frazę");
        text.print("1 - Test");
        text.print("2 - Koniec programu");
    }
}
