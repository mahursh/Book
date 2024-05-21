package com.mftplus.book.service.impl;

import com.mftplus.book.entity.Book;
import com.mftplus.book.exception.NoBookException;
import com.mftplus.book.repository.BookRepository;
import com.mftplus.book.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    public BookServiceImpl(BookRepository repository){
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);

    }

    @Override
    public Book update(Book book) throws NoBookException {
        repository.findBookByIdAndDeletedFalse(book.getId()).orElseThrow(
                () ->new NoBookException("No Active Book Was Found With Id : " +book.getId()+" To Update !")
        );
        return repository.save(book);
    }

    @Override
    public void logicalRemove(Long id) throws NoBookException {
        repository.findBookByIdAndDeletedFalse(id).orElseThrow(
                () ->new NoBookException("No Active Book Was Found With Id : " +id+" To Update !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) throws NoBookException {
        Optional<Book> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoBookException("No Book Was Found With Id : " + id );
        }
    }

    @Override
    public Long getBooksCount() {
        return repository.count();
    }

    @Override
    public Book logicalRemoveWithReturn(Long id) throws NoBookException {

        Book book = repository.findBookByIdAndDeletedFalse(id).orElseThrow(
                () ->new NoBookException("No Active Book Was Found With Id : " +id+" To Remove !")
        );

        book.setDeleted(true);
        return repository.save(book);
    }

    @Override
    public List<Book> findBookByDeletedFalse() {
        return repository.findBookByDeletedFalse();
    }

    @Override
    public Optional<Book> findBookByIdAndDeletedFalse(Long id) throws NoBookException {
        Optional<Book> optional = repository.findBookByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoBookException("No Active Book Was Found With Id : " + id );
        }
    }

    @Override
    public Optional<Book> findBookByISBN(String isbn) throws NoBookException{
        Optional<Book> optional = repository.findBookByISBN(isbn);
        if (optional.isPresent()){
            return optional;
        } else {
            throw new NoBookException("No  Book Was Found With ISBN : " + isbn );
        }
    }

    @Override
    public List<Book> findBookByAuthorAndDeletedFalse(String author) {
        return repository.findBookByAuthorAndDeletedFalse(author);
    }

    @Override
    public List<Book> findBookByTitleAndDeletedFalse(String title) {
        return repository.findBookByTitleAndDeletedFalse(title);
    }

    @Override
    public Long countByDeletedFalse() {
        return repository.countByDeletedFalse();
    }
}
