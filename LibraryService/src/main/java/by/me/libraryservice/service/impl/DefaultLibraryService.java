package by.me.libraryservice.service.impl;

import by.me.libraryservice.dto.LibraryDTO;
import by.me.libraryservice.dto.LibraryListDTO;
import by.me.libraryservice.model.Library;
import by.me.libraryservice.repository.LibraryRepository;
import by.me.libraryservice.service.LibraryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultLibraryService implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;

    public DefaultLibraryService(LibraryRepository libraryRepository, ModelMapper modelMapper) {
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
    }

    public LibraryListDTO getFreeBooks() {
        return new LibraryListDTO(libraryRepository.findByDateBorrowedIsNull().
                stream().map((book) -> modelMapper.map(book, LibraryDTO.class)).
                collect(Collectors.toList()));
    }

    @Override
    public LibraryDTO updateBook(Long id, LibraryDTO libraryDTO) {
        Optional<Library> opt_library = libraryRepository.findById(id);
        if (opt_library.isPresent()) {
            opt_library.get().setBookId(libraryDTO.getBookId());
            opt_library.get().setDateBorrowed(libraryDTO.getDateBorrowed());
            opt_library.get().setDateToReturn(libraryDTO.getDateBorrowed());
            return modelMapper.map(opt_library.get(), LibraryDTO.class);
        }
        return null;
    }
}
