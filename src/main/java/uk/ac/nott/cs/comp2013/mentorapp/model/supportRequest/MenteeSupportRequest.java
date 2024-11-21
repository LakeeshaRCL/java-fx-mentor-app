package uk.ac.nott.cs.comp2013.mentorapp.model.supportRequest;

import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentee;

public class MenteeSupportRequest {
    private Mentee mentee;
    private String supportRequestType;

    public MenteeSupportRequest(Mentee mentee, String supportRequestType) {
        this.mentee = mentee;
        this.supportRequestType = supportRequestType;
    }


    public Mentee getMentee() {
        return mentee;
    }

    public String getSupportRequestType() {
        return supportRequestType;
    }


    public String getDisplayName() {
        return this.supportRequestType + " by "+ this.mentee.getUsername();
    }
}
