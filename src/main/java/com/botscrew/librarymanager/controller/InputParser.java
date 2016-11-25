package com.botscrew.librarymanager.controller;

import com.botscrew.librarymanager.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class InputParser {

    public Commands parseCommand(String input) {
        try {
            return Commands.valueOf(input.trim().split(" ")[0].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Book parseBook(String input) {
        return new Book(parseAuthor(input), parseBookName(input));
    }

    public String parseAuthor(String input) {
        return input.substring(parseCommand(input).toString().length(), input.indexOf('"')).trim();
    }

    public String parseBookName(String input) {
        if (input.contains("\"")) {
            return input.substring(input.indexOf('"') + 1, input.lastIndexOf('"')).trim();
        } else {
            return input.substring(parseCommand(input).toString().length()).trim();
        }
    }
}
