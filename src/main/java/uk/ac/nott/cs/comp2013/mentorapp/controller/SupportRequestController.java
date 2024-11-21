package uk.ac.nott.cs.comp2013.mentorapp.controller;

import uk.ac.nott.cs.comp2013.mentorapp.model.Repository;
import uk.ac.nott.cs.comp2013.mentorapp.model.supportRequest.SupportRequestModel;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentee;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SupportRequestController {

    private SupportRequestModel supportRequestModel;
    private Repository<User, String> repo = null;

    public SupportRequestController(Repository<User, String> model) {
        this.repo = model;
        this.supportRequestModel = new SupportRequestModel();

        this.initializeData();
    }


    private void initializeData(){
        Random rand = new Random();

        repo.selectAll().forEach(u -> {
            if(u instanceof Mentee){
                Mentee mentee = (Mentee) u;

                int randomIndex = rand.nextInt(0, 3);
                System.out.println("random index: " + randomIndex);

                this.supportRequestModel.addPendingSupportRequest(mentee, this.supportRequestModel.getSupportRequestType(randomIndex));

            }
        });
    }



    public String getSupportRequestType(int id){
        return this.supportRequestModel.getSupportRequestType(id);
    }


    public void addPendingSupportRequest(Mentee mentee, String supportRequestType) {
        this.supportRequestModel.addPendingSupportRequest(mentee, supportRequestType);
    }

    public List<String> getPendingSupportRequests(){
        List<String> supportRequests = new ArrayList<String>();

        this.supportRequestModel.getPendingSupportRequests()
                .forEach(supportRequest -> supportRequests.add(supportRequest.getDisplayName()));

        return supportRequests;
    }
}
