package by.me.libraryservice.controller;

import by.me.libraryservice.dto.LibraryDTO;
import by.me.libraryservice.dto.LibraryListDTO;
import by.me.libraryservice.exception.LibraryNotFoundException;
import by.me.libraryservice.service.impl.DefaultLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/library")
public class LibraryController {
    private final DefaultLibraryService libraryService;

    @GetMapping("/free")
    public ResponseEntity<LibraryListDTO> getFreeBooks() {
        return ResponseEntity.ok().body(libraryService.getFreeBooks());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody LibraryDTO libraryDTO) throws LibraryNotFoundException {
        libraryService.updateBook(id, libraryDTO);
    }
}
