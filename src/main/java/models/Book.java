package models;

import javax.persistence.*;
import java.util.List;

@Table(name = "books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    // make it a bi-directional relationship
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="authors_books",
            joinColumns={@JoinColumn(name="book_id")},
            inverseJoinColumns={@JoinColumn(name="author_id")}
    )
    private List<Author> authors;


    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}