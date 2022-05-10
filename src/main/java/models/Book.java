package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

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
    // Baeldung: On the target side, we only have to provide the name of the field, which maps the relationship.
    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private List<Author> authors;


    public Book() {
    }

    @Override
    public String toString() {
        String output = "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors = [ ";
        if(this.authors != null) {
            for (Author author : this.authors) {
                output += author.getAuthorName() + ", ";
            }
        }
        return output + " ] }";
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