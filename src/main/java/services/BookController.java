package services;

//import models.Book;
//import org.springframework.web.bind.annotation.*;
//import repositories.BookRepository;
//
//import java.util.List;
//import java.util.Optional;

//@RestController
public class BookController {
//    private BookRepository bookRepository;
//
//    public BookController(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//
//    @GetMapping("/books")
//    public List<Book> fetchBooks() {
//        return bookRepository.findAll();
//    }
//
//    @PostMapping("/books")
//    public String addBook(@RequestBody Book book) {
//        book = bookRepository.save(book);
//        System.out.println(book);
//        return "ok";
//    }
//
//    @PutMapping("/books/{bookId}")
//    public String updateBook(@RequestBody Book book, @PathVariable int bookId) {
//        book.setId(bookId);
//        book = bookRepository.save(book);
//        System.out.println(book);
//        return "ok";
//    }
//
//    @GetMapping("/books/{bookId}")
//    public Optional<Book> getBook(@PathVariable int bookId) {
//        Optional<Book> book = bookRepository.findById(bookId);
//        System.out.println(book.get());
//        return book;
//    }
//
//    @DeleteMapping("/books/{bookId}")
//    public String deleteBook(@PathVariable int bookId) {
//        bookRepository.deleteById(bookId);
//        return "ok";
//    }
//
//    // accessors
//
//    public BookRepository getBookRepository() {
//        return bookRepository;
//    }
//
//    public void setBookRepository(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
}