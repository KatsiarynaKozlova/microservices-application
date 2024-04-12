package by.me.libraryservice.service.impl;

import by.me.libraryservice.dto.LibraryDTO;
import by.me.libraryservice.dto.LibraryListDTO;
import by.me.libraryservice.exception.LibraryNotFoundException;
import by.me.libraryservice.mapper.LibraryMapper;
import by.me.libraryservice.model.Library;
import by.me.libraryservice.repository.LibraryRepository;
import by.me.libraryservice.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static by.me.libraryservice.util.Constant.LIBRARY_NOT_FOUND_BY_ID;

@RequiredArgsConstructor
@Service
public class DefaultLibraryService implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final LibraryMapper libraryMapper;

    public LibraryListDTO getFreeBooks() {
        return new LibraryListDTO(libraryRepository.findByDateBorrowedIsNull().
                stream().map((book) -> libraryMapper.toLibraryDTO(book)).
                collect(Collectors.toList()));
    }

    @Override
    public LibraryDTO updateBook(Long id, LibraryDTO libraryDTO) throws LibraryNotFoundException {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException(String.format(LIBRARY_NOT_FOUND_BY_ID, id)));
        library.setBookId(libraryDTO.getBookId());
        library.setDateBorrowed(libraryDTO.getDateBorrowed());
        library.setDateToReturn(libraryDTO.getDateBorrowed());
        libraryRepository.save(library);
        return libraryMapper.toLibraryDTO(library);
    }
}
