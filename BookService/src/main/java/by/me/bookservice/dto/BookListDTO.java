package by.me.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class BookListDTO {
    private List<BookDTO> bookDTOList;
}
