package uk.ac.nott.cs.comp2013.mentorapp.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MentorView extends VBox implements ManagedView, ReloadableView {
    private final ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange = new SimpleObjectProperty<>("onViewChange", null);

    public MentorView() {
        Label label = new Label("Mentor");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        getChildren().add(label);
    }

    @Override
    public EventHandler<? super ViewChangeEvent> getOnViewChange() {
        return onViewChange.get();
    }

    @Override
    public void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler) {
        onViewChange.set(eventHandler);
    }

    @Override
    public void reloadData() {

    }
}