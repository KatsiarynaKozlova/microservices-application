package by.me.libraryservice.service;

import by.me.libraryservice.dto.LibraryDTO;
import by.me.libraryservice.dto.LibraryListDTO;
import by.me.libraryservice.exception.LibraryNotFoundException;

public interface LibraryService {
    LibraryListDTO getFreeBooks();

    LibraryDTO updateBook(Long id, LibraryDTO libraryDTO) throws LibraryNotFoundException;
}
