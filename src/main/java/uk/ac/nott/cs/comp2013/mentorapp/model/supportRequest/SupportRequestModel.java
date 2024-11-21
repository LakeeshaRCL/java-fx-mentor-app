package uk.ac.nott.cs.comp2013.mentorapp.model.supportRequest;

import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentee;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupportRequestModel {

    private List<String> supportRequestTypes = new ArrayList<String>();
    private List<Mentor> mentors = new ArrayList<>();
    private List<MenteeSupportRequest> pendingSupportRequests = new ArrayList<MenteeSupportRequest>();
    private List<PairedSupportRequest> pairedSupportRequests = new ArrayList<PairedSupportRequest>();

    public SupportRequestModel() {
        this.setInitialValues();
    }


    private void setInitialValues(){
        // add support types
        this.supportRequestTypes.add("Exam support");
        this.supportRequestTypes.add("Coding support");
        this.supportRequestTypes.add("Relaxation support");
    }


    public void addPendingSupportRequest(Mentee mentee, String supportRequestType){
        this.pendingSupportRequests.add(new MenteeSupportRequest(mentee, supportRequestType));
    }

    public List<MenteeSupportRequest> getPendingSupportRequests() {
        return pendingSupportRequests;
    }


    public List<PairedSupportRequest> getPairedSupportRequests() {
        return pairedSupportRequests;
    }

    public void addPairedSupportRequest(PairedSupportRequest pairedSupportRequest){
        this.pairedSupportRequests.add(pairedSupportRequest);
    }


    public List<String> getSupportRequestTypes() {
        return supportRequestTypes;
    }


    public String getSupportRequestType(int i) {
        try{
            return supportRequestTypes.get(i);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }


    public void addMentor(Mentor mentor){
        this.mentors.add(mentor);
    }

    public void removeMentor(String username){
        this.mentors.removeIf(mentor -> mentor.getUsername().equals(username));
        System.out.println("Removing mentor: " + username);
        System.out.println("New list: " + this.getMentorNames().toString());
    }


    public List<Mentor> getMentors(){
        return this.mentors;
    }

    public List<String> getMentorNames(){
        return this.mentors.stream().map(Mentor::getUsername).collect(Collectors.toList());
    }

    public Mentor getMentor(String username){
        return this.mentors.stream().filter(m -> m.getUsername().equals(username)).findFirst().orElse(null);
    }

    public MenteeSupportRequest getMenteeSupportRequest(String username, String supportRequestType){
        return this.pendingSupportRequests.stream()
                .filter(sr -> sr.getMentee().getUsername().equals(username) &&
                        sr.getSupportRequestType().equals(supportRequestType)).findFirst().orElse(null);
    }
}
