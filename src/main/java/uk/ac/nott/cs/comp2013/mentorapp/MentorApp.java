package uk.ac.nott.cs.comp2013.mentorapp;

import javafx.application.Application;
import javafx.stage.Stage;
import uk.ac.nott.cs.comp2013.mentorapp.controller.LoginController;
import uk.ac.nott.cs.comp2013.mentorapp.model.HashMapRepository;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.Administrator;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentee;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentor;
import uk.ac.nott.cs.comp2013.mentorapp.view.*;

public class MentorApp extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  private LoginView createLoginView() {

    HashMapRepository hashMapRepository = new HashMapRepository<>();
    hashMapRepository.insert(new Mentor("mentor", "123"));
    hashMapRepository.insert(new Mentee("mentee", "123"));
    hashMapRepository.insert(new Administrator("admin", "123"));

    LoginController controller = new LoginController(hashMapRepository);
    return new LoginView(controller);
  }

  private MentorView createMentorView() {
    return new MentorView();
  }

  private MenteeView createMenteeView() {
    return new MenteeView();
  }

  private AdministratorView createAdministratorView() {
    return new AdministratorView();
  }

  @Override
  public void start(Stage stage) throws Exception {
    ViewManager sm = new ViewManager(stage);

    sm.addView(ViewManager.LOGIN, createLoginView());
    sm.addView(ViewManager.MENTOR, createMentorView());
    sm.addView(ViewManager.MENTEE, createMenteeView());
    sm.addView(ViewManager.ADMIN, createAdministratorView());

    sm.setStageView(ViewManager.LOGIN);
    stage.show();
  }
}
