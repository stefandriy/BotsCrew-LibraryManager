package com.botscrew.librarymanager.view;

import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

@Component
public class ConsoleView implements View {
    private Scanner reader = new Scanner(System.in);
    private PrintStream writer = System.out;

    @Override
    public String input() {
        return reader.hasNextLine() ? reader.nextLine() : "";
    }

    @Override
    public void output(String s) {
        writer.println(s);
    }
}
