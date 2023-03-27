package models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "color_widgets")
public class ColorWidget {
    @Id
    @Column(name = "widget_id", nullable = false)
    private Long widgetId;

    @Column(name = "color", nullable = true, length = 100)
    private String color;

    // accessors

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Long widget_id) {
        this.widgetId = widget_id;
    }

}
