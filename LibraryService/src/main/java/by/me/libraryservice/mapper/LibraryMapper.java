package by.me.libraryservice.mapper;

import by.me.libraryservice.dto.LibraryDTO;
import by.me.libraryservice.model.Library;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryMapper {
    LibraryDTO toLibraryDTO(Library library);
    Library toLibraryModel(LibraryDTO libraryDTO);
}
