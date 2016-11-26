package com.botscrew.librarymanager.controller;

import com.botscrew.librarymanager.entity.Book;
import com.botscrew.librarymanager.repository.BookRepository;
import com.botscrew.librarymanager.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    View view;

    @Autowired
    InputProcessor inputProcessor;

    private ResourceBundle rb = ResourceBundle.getBundle("text-resources");

    public void start() {
        greet();
        int result = inputProcessor.processInput(input());
        while (result == -1) {
            output(rb.getString("unrecognized_command"));
            result = inputProcessor.processInput(input());
        }
    }

    public String input() {
        return view.input();
    }

    public int inputInteger() {
        try {
            return Integer.parseInt(view.input());
        } catch (NumberFormatException e) {
            output(rb.getString("not_integer"));
            return inputInteger();
        }
    }

    public void output(String output) {
        view.output(output);
    }

    public void greet() {
        output(rb.getString("hello"));
        output(rb.getString("type_help"));
    }

    public void add(Book book) {
        bookRepository.save(book);
        output(String.format(rb.getString("book_was_added"), book));
    }

    public void edit(String name) {
        List<Book> books = (List<Book>) bookRepository.findByName(name);
        if (books.size() > 1) {
            output(rb.getString("choose_book"));
            for (int i = 0; i < books.size(); i++) {
                output(i + 1 + ". " + books.get(i).toString());
            }
            int number = inputInteger();
            if (number > 0 && number < books.size()) {
                bookRepository.delete(books.get(number - 1));
                output(String.format(rb.getString("book_was_removed"), books.get(number - 1)));
            } else {
                output(String.format(rb.getString("wrong_number"), number));
                remove(name);
            }
        } else {
            bookRepository.delete(books);
            output(String.format(rb.getString("book_was_removed"), books.get(0)));
        }
    }

    public void remove(String name) {
        List<Book> books = (List<Book>) bookRepository.findByName(name);
        if (books.size() > 1) {
            output(rb.getString("choose_book"));
            for (int i = 0; i < books.size(); i++) {
                output(i + 1 + ". " + books.get(i).toString());
            }
            int number = inputInteger();
            if (number > 0 && number < books.size()) {
                Book book = books.get(number - 1);
                output(String.format(rb.getString("enter_new_name"), book));
                book.setName(input());
                bookRepository.save(book);
                output(String.format(rb.getString("book_was_updated"), book));
            } else {
                output(String.format(rb.getString("wrong_number"), number));
                remove(name);
            }
        } else {
            bookRepository.delete(books);
            Book book = books.get(0);
            output(String.format(rb.getString("enter_new_name"), book));
            book.setName(input());
            bookRepository.save(book);
            output(String.format(rb.getString("book_was_updated"), book));
        }
    }

    public void findAll() {
        List<Book> books = bookRepository.findAll();
        Collections.sort(books, (b1, b2) -> b1.getName().compareTo(b2.getName()));
        for (Book book : books) {
            output(book.toString());
        }
    }

    public void help() {
        output(Commands.getHelp());
    }

    public void exit() {
        output(rb.getString("good_bye"));
        System.exit(0);
    }
}
