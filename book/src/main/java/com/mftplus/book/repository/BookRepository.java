package com.mftplus.book.repository;

import com.mftplus.book.entity.Book;
import com.mftplus.book.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query("update bookEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Book> findBookByDeletedFalse();

    Optional<Book> findBookByIdAndDeletedFalse(Long id);
    Optional<Book> findBookByISBN(String isbn);
    List<Book> findBookByAuthorAndDeletedFalse(String author);
//    List<Book> findBookByGenresAndDeletedFalse(Genre genre);
    //TODO:Make It Containing Title .
    List<Book> findBookByTitleAndDeletedFalse(String title);
    Long countByDeletedFalse();
 }
