package com.botscrew.librarymanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String author;
    String name;

    public Book(String author, String name) {
        this.author = author;
        this.name = name;
    }

    @Override
    public String toString() {
        return author + " \"" + name + "\"";
    }
}
