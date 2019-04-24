package com.example.assignment_android.model;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FireBaseModel {

   private String PersonId;
   private String personName;
   private String personDesignation;
   private String personTeam;
   private String personImage;

    public FireBaseModel(){

    }

    public FireBaseModel(String personId, String personName, String personDesignation, String personTeam, String personImage) {
        this.PersonId = personId;
        this.personName = personName;
        this.personDesignation = personDesignation;
        this.personTeam = personTeam;
        this.personImage = personImage;
    }

    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonDesignation() {
        return personDesignation;
    }

    public void setPersonDesignation(String personDesignation) {
        this.personDesignation = personDesignation;
    }

    public String getPersonTeam() {
        return personTeam;
    }

    public void setPersonTeam(String personTeam) {
        this.personTeam = personTeam;
    }

    public String getPersonImage() {
        return personImage;
    }

    public void setPersonImage(String personImage) {
        this.personImage = personImage;
    }
}
