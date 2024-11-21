package uk.ac.nott.cs.comp2013.mentorapp.model.supportRequest;

import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentee;

import java.util.ArrayList;
import java.util.List;

public class SupportRequestModel {

    private List<String> supportRequestTypes = new ArrayList<String>();
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

}
