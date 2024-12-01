package uk.ac.nott.cs.comp2013.mentorapp.controller;

import java.util.Optional;

import uk.ac.nott.cs.comp2013.mentorapp.model.Repository;
import uk.ac.nott.cs.comp2013.mentorapp.model.session.SupportSession;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.User;
import uk.ac.nott.cs.comp2013.mentorapp.view.LoginView;
import uk.ac.nott.cs.comp2013.mentorapp.view.ViewChangeEvent;
import uk.ac.nott.cs.comp2013.mentorapp.view.ManagedView;
import uk.ac.nott.cs.comp2013.mentorapp.view.ViewManager;

public class LoginController {

    private final Repository<User, String> repo;
    private final ViewManager viewManager;
    private SupportSession supportSession;

    public LoginController(Repository<User, String> model, ViewManager viewManager, SupportSession supportSession) {
        this.repo = model;
        this.viewManager = viewManager;
        this.supportSession = supportSession;
    }

    public void onLoginClick(String username, String password, ManagedView view) {

        Optional<User> user = repo.selectById(username);

        if (user.isEmpty()) {
            ((LoginView) view).showError("Empty username or password!");
            return;
        }

        User u = user.get();

        if (u.getUsername().equals(username) && u.getPassword().equals(password)) {

            String role = u.getRole().toString();
            supportSession.setLoggedInUser(u); // set logged in user

            switch (role) {
                case "MENTEE":
                    System.out.println("Mentee!!!!!!!!!");
                    viewManager.setStageView(ViewManager.MENTEE);
                    break;
                case "MENTOR":
                    System.out.println("Mentor!!!!!!!!!");
                    viewManager.setStageView(ViewManager.MENTOR);
                    break;
                case "ADMIN":
                    System.out.println("Admin!!!!!!!!!");
                    viewManager.setStageView(ViewManager.ADMIN);
                    break;
                default:
                    ((LoginView) view).showError("User role not recognized!");
                    break;
            }

        } else {
            ((LoginView) view).showError("Incorrect username or password!");
        }
    }

    public void onLogoutClick(String requestingView) {
        viewManager.removeView(requestingView);
        viewManager.setStageView(ViewManager.LOGIN);
    }
}
