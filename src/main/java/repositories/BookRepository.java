package repositories;

import models.Book;
import models.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // custom query example using jpql
//    @Query("SELECT w FROM Widget w WHERE LOWER(w.widgetName) like LOWER(:widget_search_name)")
//    List<Widget> retrieveAllByName(@Param("widget_search_name") String widgetName);

    @Modifying
    @Query(value = "delete from authors_books WHERE book_id = :book_id", nativeQuery = true)
    void deleteAuthorBooksByBookId(@Param("book_id") int bookId);

}