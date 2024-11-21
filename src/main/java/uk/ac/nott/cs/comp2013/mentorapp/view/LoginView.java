package uk.ac.nott.cs.comp2013.mentorapp.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import uk.ac.nott.cs.comp2013.mentorapp.controller.LoginController;

public class LoginView extends VBox implements ManagedView {

  private final LoginController controller;
  protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
  private TextField txtUsername, txtPassword;
  // add:
  private Label errorLabel;
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
    // add:
    errorLabel = new Label();
    errorLabel.setVisible(false); // 默认不显示错误消息
    errorLabel.setStyle("-fx-text-fill: red;");
//
    Button btnLogin = new Button("Login");
//    btnLogin.setOnAction(e -> {
//      System.out.println("btnLogin#onAction");
//      System.out.printf("username:%s, password:%s%n", txtUsername.getText(), txtPassword.getText());
//      boolean success = controller.onLoginClick(txtUsername.getText(), txtPassword.getText());
//      System.out.printf("Login success: %s", success);
//      if (success) {
//        var eh = onViewChange.get();
//        if (eh != null) {
//          eh.handle(new ViewChangeEvent(ViewManager.ADMIN));
//
//        }
//      }
//    });
    // add:
    btnLogin.setOnAction(e -> {
      controller.onLoginClick(txtUsername.getText(), txtPassword.getText(), this);
    });
//

    getChildren().addAll(txtUsername, txtPassword, errorLabel, btnLogin);
  }
  // add:
  public void showError(String message) {
    errorLabel.setText(message);
    errorLabel.setVisible(true);
  }
  public void showMessage(String message) {
    errorLabel.setText(message);
    errorLabel.setStyle("-fx-text-fill: green;");
    errorLabel.setVisible(true);
  }
//

  @Override
  public EventHandler<? super ViewChangeEvent> getOnViewChange() {
    return onViewChange.get();
  }

  @Override
  public void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler) {
    onViewChange.set(eventHandler);
  }
}
