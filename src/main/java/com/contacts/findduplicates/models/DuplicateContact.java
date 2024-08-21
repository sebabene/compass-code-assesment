package com.contacts.findduplicates.models;

public class DuplicateContact {
    private final int contactSourceId;
    private final int contactMatchId;
    private final Double similarity;

    public DuplicateContact(int contactSourceId, int contactMatchId, Double accuracy) {
        this.contactSourceId = contactSourceId;
        this.contactMatchId = contactMatchId;
        this.similarity = accuracy;
    }

    public int getContactSourceId() {
        return contactSourceId;
    }
    public int getContactMatchId() {
        return contactMatchId;
    }
    public ContactDuplicateAccuracy getAccuracy() {
        return similarity >= 0.85 ? ContactDuplicateAccuracy.High :
                similarity >= 0.77 ? ContactDuplicateAccuracy.Medium :
                similarity >= 0.70 ? ContactDuplicateAccuracy.Low : ContactDuplicateAccuracy.None;
    }

    public String toString() {
        return contactSourceId + "," + contactMatchId + "," + getAccuracy();
    }
}