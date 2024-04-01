package by.me.libraryservice.controller;

import by.me.libraryservice.dto.LibraryDTO;
import by.me.libraryservice.dto.LibraryListDTO;
import by.me.libraryservice.service.impl.DefaultLibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {
    private final DefaultLibraryService libraryService;

    public LibraryController(DefaultLibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/free")
    public ResponseEntity<LibraryListDTO> getFreeBooks() {
        return ResponseEntity.ok().body(libraryService.getFreeBooks());
    }

    @PostMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateBook(@PathVariable Long id, @RequestBody LibraryDTO libraryDTO) {
        return ResponseEntity.ok().body(libraryService.updateBook(id, libraryDTO));
    }
}
