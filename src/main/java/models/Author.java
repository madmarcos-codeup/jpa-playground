package models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "authors")
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "author_name", nullable = false, length = 100)
    private String authorName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="authors_books",
            joinColumns={@JoinColumn(name="author_id")},
            inverseJoinColumns={@JoinColumn(name="book_id")}
    )
    private List<Book> books;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", books=" + books +
                '}';
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}