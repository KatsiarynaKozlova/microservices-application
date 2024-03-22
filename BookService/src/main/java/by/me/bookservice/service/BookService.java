package by.me.bookservice.service;

import by.me.bookservice.dto.BookDTO;
import by.me.bookservice.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks();

    BookDTO addBook(BookDTO bookDTO);

    BookDTO getBookByIsbn(String isbn) throws BookNotFoundException;

    BookDTO getBookById(Long id) throws BookNotFoundException;

    void deleteBookById(Long id) throws BookNotFoundException;

    BookDTO updateBook(Long id, BookDTO bookDTO) throws BookNotFoundException;
}
