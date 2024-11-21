package uk.ac.nott.cs.comp2013.mentorapp.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import uk.ac.nott.cs.comp2013.mentorapp.controller.SupportRequestController;

public class AdminView extends VBox implements ManagedView {

    private final SupportRequestController supportRequestController;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;

    
    public AdminView(SupportRequestController supportRequestController) {
        this.supportRequestController = supportRequestController;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    private void buildView(){
        Button press = new Button("Press");
        // add:
        Label label = new Label("Admin");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10px;");
//
        ObservableList<String> mentorNames = FXCollections.observableArrayList("Julia", "Ian", "Sue", "Matthew",
                "Hannah", "Stephan", "Denise");
        ListView<String> mentors = new ListView<>(mentorNames);


        ObservableList<String> pendingSupportRequests = FXCollections.observableArrayList(this.supportRequestController.getPendingSupportRequests());
        ListView<String> supportRequests = new ListView<>(pendingSupportRequests);
        getChildren().addAll(mentors, supportRequests, press);
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
