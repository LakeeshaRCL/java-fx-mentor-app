package uk.ac.nott.cs.comp2013.mentorapp.model.supportRequest;

import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentor;

public class PairedSupportRequest {
    private Mentor mentor;
    private MenteeSupportRequest menteeSupportRequest;

    public PairedSupportRequest(Mentor mentor, MenteeSupportRequest menteeSupportRequest) {
        this.mentor = mentor;
        this.menteeSupportRequest = menteeSupportRequest;
    }


    public Mentor getMentor() {
        return mentor;
    }

    public MenteeSupportRequest getMenteeSupportRequest() {
        return menteeSupportRequest;
    }

    public String getPairedRequest() {
        return "Support request: " + this.menteeSupportRequest.getSupportRequestType()  +
                " by "+ this.menteeSupportRequest.getMentee().getUsername() +
                " allocated to the "+ this.mentor.getUsername() + " mentor";
    }
}
