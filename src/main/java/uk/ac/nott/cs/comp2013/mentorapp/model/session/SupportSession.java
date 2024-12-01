package uk.ac.nott.cs.comp2013.mentorapp.model.session;

import uk.ac.nott.cs.comp2013.mentorapp.model.user.User;

public class SupportSession {
    private User loggedInUser;


    public SupportSession() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void clearLoggedInUser() {
        loggedInUser = null;
    }
}
