package models;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "widgets")
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "widget_name", length = 100)
    @NotNull
    private String widgetName;

    @Column(name = "quantity")
    private Integer quantity;

     @OneToOne(cascade = CascadeType.ALL)
     @PrimaryKeyJoinColumn
     private ColorWidget colorWidget;

    // accessors

    @Override
    public String toString() {
        return "Widget{" +
                "id=" + id +
                ", widgetName='" + widgetName + '\'' +
                ", quantity=" + quantity +
                ", colorWidget=" + colorWidget +
                '}';
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ColorWidget getColorWidget() {
        return colorWidget;
    }

    public void setColorWidget(ColorWidget colorWidget) {
        this.colorWidget = colorWidget;
    }
}