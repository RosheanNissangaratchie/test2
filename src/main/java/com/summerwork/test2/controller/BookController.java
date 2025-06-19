package com.summerwork.test2.controller;


import com.summerwork.test2.model.Book;
import com.summerwork.test2.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/findAll")
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/getById")
    public Optional<Book> findBook(@RequestParam int id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
    }

}
