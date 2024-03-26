package by.me.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class BookListDTO {
    private List<BookDTO> bookDTOList;
}
