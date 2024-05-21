package com.mftplus.book.controller;

import com.mftplus.book.entity.Book;
import com.mftplus.book.exception.NoBookException;
import com.mftplus.book.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {


    private final BookService service;
    public BookController(BookService service){
        this.service = service;
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> save(@Valid Book book , BindingResult result){
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        service.save(book);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Book Saved\n" + book.toString());

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> edit(@Valid Book book , BindingResult result)throws NoBookException{
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        service.update(book);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Book Edited\n" + book.toString());
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove( @PathVariable Long id) throws NoBookException {
        service.logicalRemoveWithReturn(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Book Removed\n" + id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Book>> findById(@PathVariable Long id) throws NoBookException {

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findBookByIdAndDeletedFalse(id));
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAll() {

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findBookByDeletedFalse());
    }
}
