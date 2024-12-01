package uk.ac.nott.cs.comp2013.mentorapp.controller;

import java.util.Optional;
import uk.ac.nott.cs.comp2013.mentorapp.model.Repository;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.User;
import uk.ac.nott.cs.comp2013.mentorapp.view.LoginView;
import uk.ac.nott.cs.comp2013.mentorapp.view.ViewChangeEvent;
import uk.ac.nott.cs.comp2013.mentorapp.view.ManagedView;
import uk.ac.nott.cs.comp2013.mentorapp.view.ViewManager;

public class LoginController {

  private final Repository<User, String> repo;
  private final ViewManager viewManager;

  public LoginController(Repository<User, String> model, ViewManager viewManager) {
    this.repo = model;
    this.viewManager = viewManager;
  }

  public void onLoginClick(String username, String password, ManagedView view) {

    Optional<User> user = repo.selectById(username);

    if (user.isEmpty()) {
      ((LoginView) view).showError("Empty username or password!");
      return ;
    }

    User u = user.get();

    if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
      String role = u.getRole().toString();
      switch (role) {
        case "MENTEE":
          System.out.println("Mentee!!!!!!!!!");
          view.getOnViewChange().handle(new ViewChangeEvent("mentee_view"));
          break;
        case "MENTOR":
          System.out.println("Mentor!!!!!!!!!");
          view.getOnViewChange().handle(new ViewChangeEvent("mentor_view"));
          break;
        case "ADMIN":
          System.out.println("Admin!!!!!!!!!");
          view.getOnViewChange().handle(new ViewChangeEvent("admin_view"));
          break;
        default:
          ((LoginView) view).showError("User role not recognized!");
          break;
      }

    } else {
      ((LoginView) view).showError("Incorrect username or password!");
    }
  }

  public void onLogoutClick() {
    viewManager.setStageView("login_view");
  }
}
