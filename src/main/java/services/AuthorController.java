package services;

import models.Author;
import models.Book;
import org.springframework.web.bind.annotation.*;
import repositories.AuthorRepository;

import java.util.List;

@RestController
public class AuthorController {
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository bookRepository) {
        this.authorRepository = bookRepository;
    }

    @GetMapping("/authors")
    @ResponseBody
    public List<Author> fetchAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping("/authors")
    @ResponseBody
    public String addAuthor(@RequestBody Author author) {
        author = authorRepository.save(author);
        System.out.println(author);
        return "ok";
    }

    // accessors

    public AuthorRepository getAuthorRepository() {
        return authorRepository;
    }

    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


}
