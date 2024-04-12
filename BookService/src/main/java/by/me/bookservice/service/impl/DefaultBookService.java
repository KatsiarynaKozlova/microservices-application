package by.me.bookservice.service.impl;

import by.me.bookservice.dto.BookDTO;
import by.me.bookservice.dto.BookListDTO;
import by.me.bookservice.mapper.BookMapper;
import by.me.bookservice.model.Book;
import by.me.bookservice.repository.BookRepository;
import by.me.bookservice.service.BookService;
import by.me.bookservice.exceptions.BookNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static by.me.bookservice.util.Constants.BOOK_NOT_FOUND_BY_ID;
import static by.me.bookservice.util.Constants.BOOK_NOT_FOUND_BY_ISBN;

@RequiredArgsConstructor
@Service
public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookListDTO getBooks() {
        return new BookListDTO(bookRepository.findAll().stream()
                .map((book) -> bookMapper.toBookDTO(book))
                .collect(Collectors.toList()));
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = bookMapper.toBookModel(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toBookDTO(savedBook);
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) throws BookNotFoundException {
        Book opt_book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(String.format(BOOK_NOT_FOUND_BY_ISBN, isbn)));
        return bookMapper.toBookDTO(opt_book);
    }

    @Override
    public BookDTO getBookById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format(BOOK_NOT_FOUND_BY_ID, id)));
        return bookMapper.toBookDTO(book);
    }

    @Override
    public void deleteBookById(Long id) throws BookNotFoundException {
        if(!bookRepository.existsById(id)) {
            throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_BY_ID, id));
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO book) throws BookNotFoundException {
        Book opt_book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format(BOOK_NOT_FOUND_BY_ID, id)));
        opt_book.setAuthor(book.getAuthor());
        opt_book.setTitle(book.getTitle());
        opt_book.setGenre(book.getGenre());
        opt_book.setIsbn(book.getIsbn());
        opt_book.setDescription(book.getDescription());
        bookRepository.save(opt_book);
        return bookMapper.toBookDTO(opt_book);
    }
}
