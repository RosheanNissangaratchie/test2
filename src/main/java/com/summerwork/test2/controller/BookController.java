package com.summerwork.test2.controller;


import com.summerwork.test2.model.Book;
import com.summerwork.test2.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/update")
    public ResponseEntity<Book> updateOrCreateBook(@RequestBody Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(updatedBook.id());
        if (existingBook.isPresent()) {

            Book bookToUpdate = existingBook.get();
            bookToUpdate = new Book(
                    updatedBook.id(),
                    updatedBook.title(),
                    updatedBook.pages(),
                    updatedBook.author()
            );
            Book savedBook = bookRepository.save(bookToUpdate);
            return ResponseEntity.ok(savedBook);
        } else {
            Book newBook = new Book(
                    null,
                    updatedBook.title(),
                    updatedBook.pages(),
                    updatedBook.author()
            );
            Book savedBook = bookRepository.save(newBook);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        }
    }
}
