package by.me.bookservice.service.impl;

import by.me.bookservice.dto.BookDTO;
import by.me.bookservice.model.Book;
import by.me.bookservice.repository.BookRepository;
import by.me.bookservice.service.BookService;
import by.me.bookservice.exceptions.BookNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public DefaultBookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map((book) -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.findByIsbn(isbn);
        if (opt_book.isPresent()) {
            return modelMapper.map(opt_book.get(), BookDTO.class);
        } else throw new BookNotFoundException("book with isbn '" + isbn + "' not found");
    }

    @Override
    public BookDTO getBookById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(" book with id " + id + " not found"));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public void deleteBookById(Long id) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.findById(id);
        if (opt_book.isPresent()) {
            bookRepository.delete(opt_book.get());
        } else throw new BookNotFoundException("book with id '" + id + "' not found");
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO book) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.findById(id);
        if (opt_book.isPresent()) {
            opt_book.get().setAuthor(book.getAuthor());
            opt_book.get().setTitle(book.getTitle());
            opt_book.get().setGenre(book.getGenre());
            opt_book.get().setIsbn(book.getIsbn());
            opt_book.get().setDescription(book.getDescription());
            bookRepository.save(opt_book.get());
            return modelMapper.map(opt_book.get(), BookDTO.class);
        } else throw new BookNotFoundException("book with id '" + id + "' not found");
    }

}
