package services;

import models.ColorWidget;
import models.Widget;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.WidgetRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class WidgetController {

    private WidgetRepository widgetRepository;

    public WidgetController(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    @GetMapping("/widgets")
    @ResponseBody
    public List<Widget> fetchWidgets(@RequestParam(required = false) String widgetName) {
        // widgetName is optional. if not provided then fetch all
        if(widgetName != null) {
            // can do either one of these (one is auto-query in repository, other is custom query in repository)
//            return widgetRepository.findAllByWidgetName(widgetName);
            return widgetRepository.retrieveAllByName(widgetName);
        } else
            return widgetRepository.findAll();
    }

    @GetMapping("/widgets/{widgetId}")
    @ResponseBody
    public Optional<Widget> fetchWidgets(@PathVariable("widgetId") int widgetId) {
        // get the list of cat models from the orm's repository
//        List<Cat> cats = catRepository.findAll();
//        return new ResponseEntity<>(cats, HttpStatus.valueOf(200));
        return widgetRepository.findById(widgetId);
    }

    @PutMapping("/widgets/{widgetId}")
    @ResponseBody
    public String updateWidgets(@PathVariable("widgetId") int widgetId, @RequestBody Widget widget) {
        // we don't expect the incoming json to have the widget's id, so we set it
        widget.setId(widgetId);
        // we need to also be sure to set the widgetId in the 1:1
        ColorWidget color = widget.getColorWidget();
        if(color != null)
            widget.getColorWidget().setWidgetId(widgetId);

        System.out.println(widget);

        widgetRepository.save(widget);
        return "ok";
    }

    @PostMapping("/widgets")
    @ResponseBody
    public String addWidget(@RequestBody Widget widget) {
        // save off the incoming widget's color if any
        ColorWidget color = widget.getColorWidget();
        // null the widget's color since we don't yet know the new id
        widget.setColorWidget(null);
        // save the widget
        widget = widgetRepository.save(widget);
        if(color != null) {
            color.setWidgetId(widget.getId());
            widget.setColorWidget(color);
            widgetRepository.save(widget);
        }
        return "ok";
    }

}
