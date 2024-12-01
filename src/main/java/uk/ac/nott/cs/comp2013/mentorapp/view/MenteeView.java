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
import uk.ac.nott.cs.comp2013.mentorapp.model.session.SupportSession;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentee;

public class MenteeView extends VBox implements ManagedView, ReloadableView {

    private final ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private LoginController loginController;
    private SupportRequestController supportRequestController;
    private SupportSession supportSession;


    public MenteeView(LoginController loginController, SupportRequestController supportRequestController, SupportSession supportSession) {

        this.loginController = loginController;
        this.supportRequestController = supportRequestController;
        this.supportSession = supportSession;

        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        this.buildView();
    }


    private void buildView() {
        // UI elements
        ObservableList<String> supportRequests = FXCollections.observableArrayList(supportRequestController.getSupportRequestTypes());

        ComboBox supportRequestComboBox = new ComboBox(supportRequests);

        Button btnLogout = new Button("Logout");
        Button btnSubmit = new Button("Submit Support Request");

        Label label = new Label("Mentee");
        Label lblSupportRequests = new Label("Support Request Types: ");

        HBox hBoxTitle = new HBox(label, btnLogout);
        HBox hBoxSupportRequest = new HBox(lblSupportRequests, supportRequestComboBox);
        HBox hBoxBottom = new HBox(btnSubmit);

        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        getChildren().addAll(hBoxTitle, hBoxSupportRequest, hBoxBottom);


        btnLogout.setOnAction((event) -> {
            this.loginController.onLogoutClick(ViewManager.MENTEE);
        });


        btnSubmit.setOnAction((event) -> {
            this.addSupportRequest(supportRequestComboBox);
        });
    }


    private void addSupportRequest(ComboBox<String> comboBox) {
        if (supportSession.getLoggedInUser() != null && comboBox.getSelectionModel().getSelectedItem() != null) {
            this.supportRequestController.addPendingSupportRequest((Mentee) supportSession.getLoggedInUser(), comboBox.getSelectionModel().getSelectedItem());
        } else {
            System.out.println("Unable to add support request. Logged in user or, selected request type is not available");
        }

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