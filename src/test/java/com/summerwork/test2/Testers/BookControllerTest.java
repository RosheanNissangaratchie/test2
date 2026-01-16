package com.summerwork.test2.Testers;

import com.summerwork.test2.controller.BookController;
import com.summerwork.test2.model.Book;
import com.summerwork.test2.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnAllBooks() {
        // Arrange
        Book book1 = new Book(1, "Book 1", 100, "Author 1");
        Book book2 = new Book(2, "Book 2", 200, "Author 2");
        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        // Act
        Iterable<Book> result = bookController.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, ((List<Book>) result).size());
        verify(bookRepository, times(1)).findAll();
    }
    @Test
    void findBook_WithValidId_ShouldReturnBook() {
        // Arrange
        Book book = new Book(1, "Test Book", 150, "Test Author");
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // Act
        Optional<Book> result = bookController.findBook(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Test Book", result.get().title());
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void findBook_WithInvalidId_ShouldReturnEmpty() {
        // Arrange
        when(bookRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Book> result = bookController.findBook(999);

        // Assert
        assertTrue(result.isEmpty());
        verify(bookRepository, times(1)).findById(999);
    }
    @Test
    void addBook_ShouldSaveAndReturnBook() {
        // Arrange
        Book newBook = new Book(null, "New Book", 200, "New Author");
        Book savedBook = new Book(1, "New Book", 200, "New Author");

        when(bookRepository.save(newBook)).thenReturn(savedBook);

        // Act
        Book result = bookController.addBook(newBook);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.id());
        verify(bookRepository, times(1)).save(newBook);
    }
    @Test
    void deleteBook_ShouldCallRepositoryDelete() {
        // Arrange - nothing needed since method is void

        // Act
        bookController.deleteBook(1);

        // Assert
        verify(bookRepository, times(1)).deleteById(1);
    }
    @Test
    void updateOrCreateBook_WithExistingId_ShouldUpdateBook() {
        // Arrange
        Book existingBook = new Book(1, "Old Title", 100, "Old Author");
        Book updatedBook = new Book(1, "New Title", 200, "New Author");

        when(bookRepository.findById(1)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        // Act
        ResponseEntity<Book> response = bookController.updateOrCreateBook(updatedBook);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New Title", response.getBody().title());
        verify(bookRepository, times(1)).findById(1);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void updateOrCreateBook_WithNewId_ShouldCreateBook() {
        // Arrange
        Book newBook = new Book(999, "New Book", 300, "New Author");

        when(bookRepository.findById(999)).thenReturn(Optional.empty());
        when(bookRepository.save(any(Book.class))).thenReturn(newBook);

        // Act
        ResponseEntity<Book> response = bookController.updateOrCreateBook(newBook);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Book", response.getBody().title());
        verify(bookRepository, times(1)).findById(999);
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}
