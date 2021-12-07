package repositories;

import models.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends JpaRepository<Widget, Integer> {
    // simple additional query that jpa can automatically figure out
    List<Widget> findAllByWidgetName(String widgetName);

    // custom query using sql
    // @Query(value = "SELECT * FROM widget u WHERE lcase(w.widget_name) like lcase(:widget_search_name)", nativeQuery = true)
    // custom query example using jpql
    @Query("SELECT w FROM Widget w WHERE LOWER(w.widgetName) like LOWER(:widget_search_name)")
    List<Widget> retrieveAllByName(@Param("widget_search_name") String widgetName);
}