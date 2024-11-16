package uk.ac.nott.cs.comp2013.mentorapp.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uk.ac.nott.cs.comp2013.mentorapp.controller.LoginController;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.User;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.UserRole;

public class LoginView extends VBox implements ManagedView {

  private final LoginController controller;
  protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;

  private TextField txtUsername, txtPassword;
  private Button btnLogin;
  private Label lblUsername, lblPassword;

  public LoginView(LoginController controller) {
    this.controller = controller;
    this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
    buildView();
  }

  private void buildView() {

    txtUsername = new TextField();
    txtPassword = new TextField();
    btnLogin = new Button("Login");

    lblUsername = new Label("Username");
    lblPassword = new Label("Password");



    btnLogin.setOnAction(e -> {
      boolean success = controller.onLoginClick(txtUsername.getText(), txtPassword.getText());

      if (success) {

        var eh = onViewChange.get();
        if (eh != null) {

          // get logged in user
          User loggedInUser = controller.getLoggedInUser();

          if (loggedInUser != null) {

            String view = "";

            switch (loggedInUser.getRole()){
              case UserRole.MENTOR -> view = ViewManager.MENTOR;
              case UserRole.MENTEE -> view = ViewManager.MENTEE;
              case UserRole.ADMIN -> view = ViewManager.ADMIN;
            }

            onViewChange.get().handle(new ViewChangeEvent(view));
          }

        }
      }
    });

    setAlignment(Pos.CENTER);
    getChildren().addAll(lblUsername, txtUsername, lblPassword, txtPassword, btnLogin);
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
