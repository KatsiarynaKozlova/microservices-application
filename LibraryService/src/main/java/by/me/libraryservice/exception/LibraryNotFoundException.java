package by.me.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LibraryNotFoundException extends Exception {
    public LibraryNotFoundException(String s) {
        super(s);
    }
}