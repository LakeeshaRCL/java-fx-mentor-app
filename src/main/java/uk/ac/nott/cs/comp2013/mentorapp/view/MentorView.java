package uk.ac.nott.cs.comp2013.mentorapp.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class MentorView extends VBox implements ManagedView {

    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;

    public MentorView() {
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);

        Label label = new Label();
        label.setText("Mentor View");

        getChildren().addAll(label);
    }

    @Override
    public EventHandler<? super ViewChangeEvent> getOnViewChange() {
        return onViewChange.get();
    }

    @Override
    public void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler) {
        onViewChange.set(eventHandler);
    }
}
