package by.me.bookservice.controller;

import by.me.bookservice.dto.BookDTO;
import by.me.bookservice.dto.BookListDTO;
import by.me.bookservice.exceptions.BookNotFoundException;
import by.me.bookservice.service.impl.DefaultBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final DefaultBookService bookService;

    public BookController(DefaultBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BookListDTO> getBooks() {
        return ResponseEntity.ok().body(bookService.getBooks());
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookDTO savedBook = bookService.addBook(bookDTO);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) {
        try {
            return ResponseEntity.ok().body(bookService.getBookByIsbn(isbn));
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getAllBookByIsbn(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(bookService.getBookById(id));
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBookById(id);
            return HttpStatus.NO_CONTENT;
        } catch (BookNotFoundException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBookById(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        try {
            return ResponseEntity.ok().body(bookService.updateBook(id, bookDTO));
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
