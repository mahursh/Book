package com.mftplus.book.service;

import com.mftplus.book.entity.Book;
import com.mftplus.book.exception.NoBookException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Book update(Book book) throws NoBookException;

    @Transactional
    void logicalRemove(Long id) throws NoBookException;

    List<Book> findAll();

    Optional<Book> findById(Long id) throws NoBookException;

    Long getBooksCount();

    Book logicalRemoveWithReturn(Long id) throws NoBookException;

    List<Book> findBookByDeletedFalse();

    Optional<Book> findBookByIdAndDeletedFalse(Long id) throws NoBookException;

    Optional<Book> findBookByISBN(String isbn)throws NoBookException;

    List<Book> findBookByAuthorAndDeletedFalse(String author);

    List<Book> findBookByTitleAndDeletedFalse(String title);

    Long countByDeletedFalse();

}
