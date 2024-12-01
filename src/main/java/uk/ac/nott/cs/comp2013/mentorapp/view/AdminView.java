package uk.ac.nott.cs.comp2013.mentorapp.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uk.ac.nott.cs.comp2013.mentorapp.controller.LoginController;
import uk.ac.nott.cs.comp2013.mentorapp.controller.SupportRequestController;

public class AdminView extends VBox implements ManagedView, ReloadableView {

    private final SupportRequestController supportRequestController;
    private final LoginController loginController;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;

    private ObservableList<String> mentorNames;
    private ObservableList<String> pendingSupportRequests;
    private ObservableList<String> pairedSupportRequests;

    public AdminView(SupportRequestController supportRequestController, LoginController loginController) {
        this.supportRequestController = supportRequestController;
        this.loginController = loginController;

        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    private void buildView() {

        // clear existing view
        getChildren().clear();

        // mentors list view
        this.mentorNames = FXCollections
                .observableArrayList(this.supportRequestController.getMentors());
        ListView<String> mentorsListView = new ListView<>(mentorNames);


        // pending support request list view
        this.pendingSupportRequests = FXCollections
                .observableArrayList(this.supportRequestController.getPendingSupportRequests());
        ListView<String> supportRequestsListView = new ListView<>(pendingSupportRequests);


        // paired support requests
        this.pairedSupportRequests = FXCollections
                .observableArrayList(this.supportRequestController.getPairedSupportRequests());
        ListView<String> pairedSupportRequestsListView = new ListView<>(pairedSupportRequests);


        // labels
        Label lblAdmin = new Label("Admin");
        Label lblMentors = new Label("Mentors");
        Label lblSupportRequests = new Label("Pending Support Requests");
        Label lblPairedSupportRequests = new Label("Paired Support Requests");

        String labelStyle = "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10px;";
        lblAdmin.setStyle(labelStyle);
        lblMentors.setStyle(labelStyle);
        lblSupportRequests.setStyle(labelStyle);
        lblPairedSupportRequests.setStyle(labelStyle);


        // buttons
        Button btnPair = new Button("Pair");
        Button btnLogout = new Button("Logout");

        // hboxes
        HBox headingHbox = new HBox(lblAdmin, btnLogout);

        btnPair.setOnAction(e -> {
            this.handlePair(mentorsListView, supportRequestsListView);
        });


        btnLogout.setOnAction(e -> {
            this.loginController.onLogoutClick(ViewManager.ADMIN);
        });

        getChildren().addAll(headingHbox, lblMentors, mentorsListView, lblSupportRequests, supportRequestsListView,
                lblPairedSupportRequests, pairedSupportRequestsListView, btnPair);
    }


    private void handlePair(ListView<String> mentorsListView, ListView<String> supportRequestsListView) {

        String selectedMentor = mentorsListView.getSelectionModel().getSelectedItem();
        String selectedSupportRequest = supportRequestsListView.getSelectionModel().getSelectedItem();

        System.out.println("Selected mentor: " + selectedMentor);
        System.out.println("Selected support request: " + selectedSupportRequest);

        // remove from mentors
        if (selectedMentor != null && selectedSupportRequest != null) {

            // register the pair
            this.supportRequestController.addPairedSupportRequest(selectedMentor, selectedSupportRequest);

            // update lists
            this.mentorNames.remove(selectedMentor);
            this.pendingSupportRequests.remove(selectedSupportRequest);
            this.pairedSupportRequests.clear();
            this.pairedSupportRequests.addAll(this.supportRequestController.getPairedSupportRequests())

            ;
        }
    }

    private void updateData() {

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
        this.buildView();
    }
}
