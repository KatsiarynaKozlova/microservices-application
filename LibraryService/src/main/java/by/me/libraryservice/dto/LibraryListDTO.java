package by.me.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LibraryListDTO {
    private List<LibraryDTO> libraryDTOList;
}
