package by.me.libraryservice.service.impl;

import by.me.libraryservice.dto.LibraryDTO;
import by.me.libraryservice.dto.LibraryListDTO;
import by.me.libraryservice.exception.LibraryNotFoundException;
import by.me.libraryservice.model.Library;
import by.me.libraryservice.repository.LibraryRepository;
import by.me.libraryservice.service.LibraryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static by.me.libraryservice.util.Constant.LIBRARY_NOT_FOUND_BY_ID;

@AllArgsConstructor
@Service
public class DefaultLibraryService implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;

    public LibraryListDTO getFreeBooks() {
        return new LibraryListDTO(libraryRepository.findByDateBorrowedIsNull().
                stream().map((book) -> modelMapper.map(book, LibraryDTO.class)).
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
        return modelMapper.map(library, LibraryDTO.class);
    }
}
