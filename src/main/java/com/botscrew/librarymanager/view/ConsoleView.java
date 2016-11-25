package com.botscrew.librarymanager.view;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ConsoleView implements View {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private PrintStream writer = System.out;

    @Override
    public String input() {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    @Override
    public void output(String s) {
        writer.println(s);
    }
}
