package uk.ac.nott.cs.comp2013.mentorapp.view;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uk.ac.nott.cs.comp2013.mentorapp.controller.LoginController;
import uk.ac.nott.cs.comp2013.mentorapp.controller.SupportRequestController;

public class MenteeView extends VBox implements ManagedView {

    private final ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange = new SimpleObjectProperty<>("onViewChange", null);
    private LoginController loginController;
    private SupportRequestController supportRequestController;


    public MenteeView(LoginController loginController, SupportRequestController supportRequestController) {

        this.loginController = loginController;
        this.supportRequestController = supportRequestController;

        // UI elements
        ObservableList<String> supportRequests = FXCollections.observableArrayList(supportRequestController.getSupportRequestTypes());

        ComboBox supportRequestComboBox = new ComboBox(supportRequests);

        Button btnLogout = new Button("Logout");

        Label label = new Label("Mentee");
        Label lblSupportRequests = new Label("Support Request Types: ");

        HBox hBoxTitle = new HBox(label, btnLogout);
        HBox hBoxSupportRequest = new HBox(lblSupportRequests, supportRequestComboBox);

        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        getChildren().addAll(hBoxTitle, hBoxSupportRequest);


        btnLogout.setOnAction((event) -> {
            this.loginController.onLogoutClick();
        });

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