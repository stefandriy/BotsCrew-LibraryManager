package com.botscrew.librarymanager.repository;

import com.botscrew.librarymanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Collection<Book> findByName(String name);
}
