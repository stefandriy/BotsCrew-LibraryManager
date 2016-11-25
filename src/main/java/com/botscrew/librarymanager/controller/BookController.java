package com.botscrew.librarymanager.controller;

import com.botscrew.librarymanager.entity.Book;
import com.botscrew.librarymanager.repository.BookRepository;
import com.botscrew.librarymanager.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    View view;

    @Autowired
    InputProcessor inputProcessor;

    public void start() {
        greet();
        inputProcessor.processInput(input());
    }

    public String input() {
        return view.input();
    }

    public int inputInteger() {
        try {
            return Integer.parseInt(view.input());
        } catch (NumberFormatException e) {
            output("Wrong number. Try again:");
            return inputInteger();
        }
    }

    public void output(String output) {
        view.output(output);
    }

    public void greet() {
        output("Hello!");
        output("If you don't know what to do type 'help'");
    }

    public void add(Book book) {
        bookRepository.save(book);
        output("Book " + book + " was added.");
    }

    public void edit(String name) {
        List<Book> books = (List<Book>) bookRepository.findByName(name);
        if (books.size() > 1) {
            output("We have few books with such name. Please, choose one by typing a number of book:");
            for (int i = 0; i < books.size(); i++) {
                output(i + 1 + ". " + books.get(i).toString());
            }
            int number = inputInteger();
            if (number > 0) {
                bookRepository.delete(books.get(number - 1));
                output("Book " + books.get(number - 1) + " was removed.");
            } else {
                output("Wrong number: " + number);
                remove(name);
            }
        } else {
            bookRepository.delete(books);
            output("Book " + books.get(0) + " was removed.");
        }
    }

    public void remove(String name) {
        List<Book> books = (List<Book>) bookRepository.findByName(name);
        if (books.size() > 1) {
            output("We have few books with such name. Please, choose one by typing a number of book:");
            for (int i = 0; i < books.size(); i++) {
                output(i + 1 + ". " + books.get(i).toString());
            }
            int number = inputInteger();
            if (number > 0) {
                Book book = books.get(number - 1);
                output("Enter new name for book " + book);
                book.setName(input());
                bookRepository.save(book);
                output("Book " + book + " was updated.");
            } else {
                output("Wrong number: " + number);
                remove(name);
            }
        } else {
            bookRepository.delete(books);
            Book book = books.get(0);
            output("Enter new name for book " + book);
            book.setName(input());
            bookRepository.save(book);
            output("Book " + book + " was updated.");
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
        output("Good bye!");
        System.exit(0);
    }
}
