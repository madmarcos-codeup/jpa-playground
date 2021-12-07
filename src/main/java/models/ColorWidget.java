package models;

import javax.persistence.*;

@Entity
@Table(name = "color_widgets")
public class ColorWidget {
    @Id
    @Column(name = "widget_id", nullable = false)
    private Integer widgetId;

    @Column(name = "color", nullable = true, length = 100)
    private String color;

    // accessors

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Integer widget_id) {
        this.widgetId = widget_id;
    }
}
