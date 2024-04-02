package by.me.bookservice.controller;

import by.me.bookservice.dto.BookDTO;
import by.me.bookservice.dto.BookListDTO;
import by.me.bookservice.exceptions.BookNotFoundException;
import by.me.bookservice.service.impl.DefaultBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final DefaultBookService bookService;

    @GetMapping
    public ResponseEntity<BookListDTO> getBooks() {
        return ResponseEntity.ok().body(bookService.getBooks());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addBook(@RequestBody BookDTO bookDTO) {
        bookService.addBook(bookDTO);
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) throws BookNotFoundException {
        bookService.deleteBookById(id);
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
