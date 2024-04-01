package by.me.libraryservice.service;

import by.me.libraryservice.dto.LibraryDTO;
import by.me.libraryservice.dto.LibraryListDTO;

public interface LibraryService {
    LibraryListDTO getFreeBooks();

    LibraryDTO updateBook(Long id, LibraryDTO libraryDTO);
}
