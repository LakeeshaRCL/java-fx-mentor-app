package uk.ac.nott.cs.comp2013.mentorapp.view;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uk.ac.nott.cs.comp2013.mentorapp.controller.LoginController;

public class MenteeView extends VBox implements ManagedView {
    private final ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange = new SimpleObjectProperty<>("onViewChange", null);

    private LoginController loginController;

    public MenteeView(LoginController loginController) {

        this.loginController = loginController;

        Button btnLogout = new Button("Logout");

        Label label = new Label("Mentee");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        getChildren().addAll(label, btnLogout);


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