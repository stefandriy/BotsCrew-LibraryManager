package com.botscrew.librarymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InputProcessor {

    @Autowired
    private BookController bookController;

    @Autowired
    private InputParser parser;

    public int processInput(String input) {
        try {
            switch (parser.parseCommand(input)) {
                case ADD:
                    bookController.add(parser.parseBook(input));
                    break;
                case EDIT:
                    bookController.edit(parser.parseBookName(input));
                    break;
                case REMOVE:
                    bookController.remove(parser.parseBookName(input));
                    break;
                case ALL_BOOKS:
                    bookController.findAll();
                    break;
                case HELP:
                    bookController.help();
                    break;
                case EXIT:
                    bookController.exit();
                    break;
                default:
                    return -1;
            }
        } catch (Exception e) {
            return -1;
        }
        return processInput(bookController.input());
    }
}
