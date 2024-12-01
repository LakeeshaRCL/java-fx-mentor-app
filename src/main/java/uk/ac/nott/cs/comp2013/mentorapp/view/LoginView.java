package uk.ac.nott.cs.comp2013.mentorapp.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import uk.ac.nott.cs.comp2013.mentorapp.controller.LoginController;

public class LoginView extends VBox implements ManagedView, ReloadableView {

    private final LoginController controller;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private TextField txtUsername, txtPassword;
    // add:
    private Label errorLabel, lblUsername, lblPassword;
//

    // constructor
    public LoginView(LoginController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    private void buildView() {
        txtUsername = new TextField();
        txtPassword = new TextField();

        errorLabel = new Label();
        errorLabel.setVisible(false); // 默认不显示错误消息
        errorLabel.setStyle("-fx-text-fill: red;");

        lblUsername = new Label("Username");
        lblPassword = new Label("Password");

        Button btnLogin = new Button("Login");

        btnLogin.setOnAction(e -> {
            controller.onLoginClick(txtUsername.getText(), txtPassword.getText(), this);
            txtPassword.setText("");
            txtUsername.setText("");
        });

        getChildren().addAll(lblUsername, txtUsername, lblPassword, txtPassword, errorLabel, btnLogin);
    }

    public void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public void showMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setStyle("-fx-text-fill: green;");
        errorLabel.setVisible(true);
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
