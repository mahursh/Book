package com.mftplus.book.entity;

import com.mftplus.book.enums.Genre;
import com.mftplus.book.base.Base;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "bookEntity")
@Table(name = "book_tbl")
public class Book extends Base {

    @Id
    @SequenceGenerator(name = "bookSeq", sequenceName = "book_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSeq")
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_title",  columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Title")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String title;

    @Column(name = "book_isbn", length = 13)
    @Pattern(regexp = "^[0-9]{10,13}$", message = "Invalid ISBN ")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @NotBlank(message = "Should Not Be Null")
    private String ISBN;

    @Column(name = "book_author",  columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Author Name")
    @Size(min = 3, max = 50, message = "Author Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String author;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "genre")
    @Column(name = "book_genre")
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres = new HashSet<>();

    @Column(name = "book-publish-year")
    @Past(message = "Invalid Publish Year")
    private LocalDate publishYear;

}
