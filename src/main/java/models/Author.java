package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

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

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            targetEntity = Address.class)
    private Address address;

    /*
    ManyToMany attributes:
        fetch LAZY will try to avoid loading child objects until they are needed like a toString()
            this is a good default until you know you need EAGER
        cascade determines what happens when the parent side of an M:M relationship changes (e.g., deleted)
            Baeldung:
                CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.

                Cascade Type PERSIST propagates the persist operation from a parent to a child entity. When we save the person entity, the address entity will also get saved.

                The detach operation removes the entity from the persistent context. When we use CascadeType.DETACH, the child entity will also get removed from the persistent context.

                Refresh operations reread the value of a given instance from the database. In some cases, we may change an instance after persisting in the database, but later we need to undo those changes.
                    In that kind of scenario, this may be useful. When we use this operation with Cascade Type REFRESH, the child entity also gets reloaded from the database whenever the parent entity is refreshed.

    @JoinTable provides info about the join table. might not be necesary if you create the table manually
    Baeldung
    */
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            targetEntity = Book.class)
    @JoinTable(
            name="authors_books",
            joinColumns={@JoinColumn(name="author_id")},
            inverseJoinColumns={@JoinColumn(name="book_id")}
    )
    @JsonIgnoreProperties("authors")
    private List<Book> books;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", address='" + address + '\'' +
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}