package com.summerwork.test2.repository;

import com.summerwork.test2.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Integer> {

    // Custom query example (optional)
    @Query("SELECT * FROM book WHERE author = :author")
    Iterable<Book> findByAuthor(@Param("author") String author);
}