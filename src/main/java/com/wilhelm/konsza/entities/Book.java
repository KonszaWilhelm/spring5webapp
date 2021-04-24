package com.wilhelm.konsza.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private final Set<Author> authors = new HashSet<>();
    @ManyToOne
    private Publisher publisher;

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public boolean addAuthor(Author author) {
        if (author != null) {
            authors.add(author);
            return true;
        } else return false;
    }

    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public boolean setPublisher(Publisher publisher) {
        boolean result;
        if (this.publisher == null && publisher != null) {
            this.publisher = publisher;
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors + '\'' +
                '}';
    }

}
