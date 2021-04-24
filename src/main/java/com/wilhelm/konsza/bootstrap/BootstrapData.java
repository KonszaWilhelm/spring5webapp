package com.wilhelm.konsza.bootstrap;

import com.wilhelm.konsza.entities.Author;
import com.wilhelm.konsza.entities.Book;
import com.wilhelm.konsza.entities.Publisher;
import com.wilhelm.konsza.repositories.AuthorRepo;
import com.wilhelm.konsza.repositories.BookRepo;
import com.wilhelm.konsza.repositories.PublisherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final PublisherRepo publisherRepo;

    public BootstrapData(AuthorRepo authorRepo, BookRepo bookRepo, PublisherRepo publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book bookDdd = new Book("Domain Driven Design", "021555");
        eric.addBook(bookDdd);
        bookDdd.addAuthor(eric);

        authorRepo.save(eric);
        bookRepo.save(bookDdd);

        Author george = new Author("George", "Orwell");
        Book book1848 = new Book("1848", "32155");
        george.addBook(book1848);
        book1848.addAuthor(george);

        authorRepo.save(george);
        bookRepo.save(book1848);

        Author rod = new Author("Rod", "Johnson");
        Book bookNoEjb = new Book("J2EE Development without EJB", "32512155");
        rod.addBook(bookNoEjb);
        bookNoEjb.addAuthor(rod);

        authorRepo.save(rod);
        bookRepo.save(bookNoEjb);

        System.out.println("Started in Bootstrap");

        System.out.println("Books: ");
        bookRepo.findAll().forEach(System.out::println);
        System.out.println("Authors: ");
        authorRepo.findAll().forEach(System.out::println);

        Publisher corvina = new Publisher("Corvina Kőnyvkiadó");
        corvina.addBook(book1848);
        corvina.addBook(bookDdd);
        corvina.addBook(bookNoEjb);

        bookDdd.setPublisher(corvina);
        book1848.setPublisher(corvina);
        bookNoEjb.setPublisher(corvina);

        publisherRepo.save(corvina);
        bookRepo.save(bookDdd);
        bookRepo.save(book1848);
        bookRepo.save(bookNoEjb);

        System.out.println("Publisher: ");
        publisherRepo.findAll().forEach(System.out::println);
        System.out.println("Book Repo: ");
        bookRepo.findAll().forEach(System.out::println);
    }
}
