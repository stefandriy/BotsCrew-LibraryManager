package com.botscrew.librarymanager;

import com.botscrew.librarymanager.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagerApplication implements CommandLineRunner {

    @Autowired
    BookController bookController;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}


    @Override
    public void run(String... args) {
        bookController.start();
    }
}
