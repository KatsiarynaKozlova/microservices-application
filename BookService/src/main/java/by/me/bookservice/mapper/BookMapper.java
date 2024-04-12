package by.me.bookservice.mapper;

import by.me.bookservice.dto.BookDTO;
import by.me.bookservice.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toBookDTO(Book book);
    Book toBookModel(BookDTO bookDTO);
}
