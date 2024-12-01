package uk.ac.nott.cs.comp2013.mentorapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import uk.ac.nott.cs.comp2013.mentorapp.controller.LoginController;
import uk.ac.nott.cs.comp2013.mentorapp.controller.SupportRequestController;
import uk.ac.nott.cs.comp2013.mentorapp.model.Repository;
import uk.ac.nott.cs.comp2013.mentorapp.model.RepositoryFactory;
import uk.ac.nott.cs.comp2013.mentorapp.model.session.SupportSession;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.User;
import uk.ac.nott.cs.comp2013.mentorapp.view.AdminView;
import uk.ac.nott.cs.comp2013.mentorapp.view.LoginView;
import uk.ac.nott.cs.comp2013.mentorapp.view.MenteeView;
import uk.ac.nott.cs.comp2013.mentorapp.view.ViewManager;

public class MentorApp extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  private Repository<User, String> loadMockData() throws IOException {
    RepositoryFactory builder = new RepositoryFactory();
    return builder.userRepositoryFromCsv("/MOCK_DATA.csv");
  }

  private LoginView createLoginView(LoginController controller) {

    return new LoginView(controller);
  }

  private AdminView createAdminView(SupportRequestController controller) {

    return new AdminView(controller);
  }


  private MenteeView createMenteeView(LoginController controller, SupportRequestController requestController) {
    return new MenteeView(controller, requestController);
  }

  @Override
  public void start(Stage stage) throws Exception {
    // add : set the size of stage
    stage.setWidth(800);
    stage.setHeight(600);

    // dependencies
    ViewManager vm = new ViewManager(stage);
    Repository<User, String> mockData = loadMockData();
    SupportSession supportSession = new SupportSession();

    // controllers
    LoginController loginController = new LoginController(mockData, vm, supportSession);
    SupportRequestController supportRequestController = new SupportRequestController(mockData);



    vm.addView(ViewManager.LOGIN, createLoginView(loginController));
    vm.addView(ViewManager.ADMIN, createAdminView(supportRequestController));
    vm.addView(ViewManager.MENTEE, createMenteeView(loginController, supportRequestController));

    vm.setStageView(ViewManager.LOGIN);
    stage.show();
  }
}
