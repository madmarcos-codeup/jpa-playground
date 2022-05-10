package docrob.springjpatutorial;

import models.Address;
import models.Author;
import models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import repositories.AddressRepository;
import repositories.AuthorRepository;
import repositories.BookRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional(rollbackFor = { SQLException.class })
@Rollback(false)
class AuthorBookTests {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void fetchAuthors() {
        List<Author> authors = authorRepository.findAll();
        System.out.println(authors.get(0));
        assert true;
    }

    @Test
    void fetchBooks() {
        List<Book> books = bookRepository.findAll();
        System.out.println(books.get(0));
        assert true;
    }

    @Test
    void addAuthor() {
        Author author = new Author();
        author.setAuthorName("Tom Jones");
        author = authorRepository.save(author);
        System.out.println(author);
        assert true;
    }

    @Test
    void updateAuthorAddress() {
        Author author = authorRepository.getById(1);
        Address address = new Address();
        address.setAddress("555 Main St.");
        address.setCity("San Antonio");
        address.setState("TX");
        address.setZip("78555");
        address.setAuthor(author);
        address = addressRepository.save(address);

        author.setAddress(address);
        author = authorRepository.save(author);
        System.out.println(author);
        assert true;
    }

    @Test
    void addBook() {
        Book book = new Book();
        book.setTitle("A Book");
        book = bookRepository.save(book);
        System.out.println(book);
        assert true;
    }

    @Test
    void addBookToAuthor() {
        Author author = authorRepository.getById(1);
        Book book = bookRepository.getById(1);
        List<Book> myBooks = new ArrayList<>();
        myBooks.add(book);
        author.setBooks(myBooks);
        author = authorRepository.save(author);

        System.out.println(author);
        assert true;
    }

    @Test
    void addAll() {
        Author author = new Author();
        author.setAuthorName("Tom Jones");
        author = authorRepository.save(author);
        Book book = new Book();
        book.setTitle("A Book");
        book = bookRepository.save(book);
        List<Book> myBooks = new ArrayList<>();
        myBooks.add(book);
        author.setBooks(myBooks);
        author = authorRepository.save(author);
        System.out.println(author);
        assert true;
    }

    @Test
    void deleteAuthor() {
        // this will succeed prolly because author has the M:M annotations
        // WARNING: also deletes books associated with the author_book records
        // might be able to fix that with a CascadeType setting
        addressRepository.deleteById(1);
        authorRepository.deleteById(1);
        assert true;
    }

    @Test
    void deleteBook() {
        // this will fail due to fk constraint
        // and no mapping inside Book to authors_books
        bookRepository.deleteById(1);
        assert true;
    }

    @Test
    void deleteBook2() {
        // this works. so if you have a non-annotated M:M relationship,
        // this is an ok way of deleting the primary key record by first deleting the dependent records
        // note that I put the dependent deletion method inside the book repository
        // since author_books does not have an Entity, there is no repo for it, nor does their need to be one
        bookRepository.deleteAuthorBooksByBookId(1);
        bookRepository.deleteById(1);
        assert true;
    }

    @Test
    void contextLoads() {
    }

}
