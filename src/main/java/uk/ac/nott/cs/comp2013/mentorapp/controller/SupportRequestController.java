package uk.ac.nott.cs.comp2013.mentorapp.controller;

import javafx.collections.ObservableList;
import uk.ac.nott.cs.comp2013.mentorapp.model.Repository;
import uk.ac.nott.cs.comp2013.mentorapp.model.supportRequest.PairedSupportRequest;
import uk.ac.nott.cs.comp2013.mentorapp.model.supportRequest.SupportRequestModel;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentee;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.Mentor;
import uk.ac.nott.cs.comp2013.mentorapp.model.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SupportRequestController {

    private SupportRequestModel supportRequestModel;
    private Repository<User, String> repo = null;

    public SupportRequestController(Repository<User, String> model) {
        this.repo = model;
        this.supportRequestModel = new SupportRequestModel();

        // this.initializeData();
        this.initializeMentors();
    }


    private void initializeData() {
        Random rand = new Random();
        int maxRecords = 10;
        AtomicInteger currentRecord = new AtomicInteger(1);


        // add support request types
        repo.selectAll().forEach(u -> {
            if (u.getRole().toString().equals("MENTEE")) {

                if (currentRecord.get() <= maxRecords) {
                    Mentee mentee = (Mentee) u;

                    int randomIndex = rand.nextInt(0, 3);
                    System.out.println("random index: " + randomIndex);

                    this.supportRequestModel.addPendingSupportRequest(mentee, this.supportRequestModel.getSupportRequestType(randomIndex));
                }
                currentRecord.getAndIncrement();

            }
        });

    }


    private void initializeMentors() {
        repo.selectAll().forEach(u -> {
            if (u.getRole().toString().equals("MENTOR")) {
                Mentor mentor = (Mentor) u;
                this.supportRequestModel.addMentor(mentor);
            }
        });
    }


    public String getSupportRequestType(int id) {
        return this.supportRequestModel.getSupportRequestType(id);
    }


    public List<String> getSupportRequestTypes() {
        return this.supportRequestModel.getSupportRequestTypes();
    }


    public void addPendingSupportRequest(Mentee mentee, String supportRequestType) {
        this.supportRequestModel.addPendingSupportRequest(mentee, supportRequestType);
    }

    public List<String> getPendingSupportRequests() {
        List<String> supportRequests = new ArrayList<String>();

        this.supportRequestModel.getPendingSupportRequests()
                .forEach(supportRequest -> supportRequests.add(supportRequest.getDisplayName()));

        return supportRequests;
    }


    public List<String> getPairedSupportRequests() {
        List<String> pairedSupportRequests = new ArrayList<>();

        this.supportRequestModel.getPairedSupportRequests().forEach(psr -> pairedSupportRequests
                .add(psr.getPairedRequestDisplayName()));

        return pairedSupportRequests;
    }


    public void addPairedSupportRequest(String mentorName, String menteeSupportRequest) {
        String[] menteeSrSplit = menteeSupportRequest.split(" by ");

        System.out.println("Split mentee sr : " + Arrays.toString(menteeSrSplit));

        PairedSupportRequest pairedSupportRequest =
                new PairedSupportRequest(this.supportRequestModel.getMentor(mentorName), this.supportRequestModel.
                        getMenteeSupportRequest(menteeSrSplit[1], menteeSrSplit[0]));
        this.supportRequestModel.addPairedSupportRequest(pairedSupportRequest);
    }

    public List<String> getMentors() {
        return this.supportRequestModel.getMentorNames();
    }

    public void removeMentor(String mentorName) {
        this.supportRequestModel.removeMentor(mentorName);
    }

}
