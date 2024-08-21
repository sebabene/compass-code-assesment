package com.contacts.findduplicates.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;

public class Contact {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String zip;
    private final String address;

    public Contact(String contact) {
        String[] parts = contact.split(",");
        id = Integer.parseInt(parts[0]);
        firstName = parts[1];
        lastName = parts[2];
        email = parts[3];
        zip = parts[4];

        // If address not provided the split array is of length 5
        address = parts.length < 6 ? "" : parts[5];
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getZip() {
        return zip;
    }
    public String getAddress() {
        return address;
    }
    
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + email + "," + zip + "," + address;
    }
    
    public Double compareTo(Contact other) {
        // Using Jaro-Winkler distance to test string similarity (https://en.wikipedia.org/wiki/Jaro-Winkler_distance)
        JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();

        // If both current and the contact to compare have value for the field compare similarity
        Double similarityTotal = 0.0;
        int similarityCriteriaCount = 0;

        if (StringUtils.isNotEmpty(this.getFirstName()) && StringUtils.isNotEmpty(other.getFirstName())) {
            similarityTotal += similarity.apply(this.getFirstName(), other.getFirstName());
            similarityCriteriaCount++;
        }
        if (StringUtils.isNotEmpty(this.getLastName()) && StringUtils.isNotEmpty(other.getLastName())) {
            similarityTotal += similarity.apply(this.getLastName(), other.getLastName());
            similarityCriteriaCount++;
        }
        if (StringUtils.isNotEmpty(this.getEmail()) && StringUtils.isNotEmpty(other.getEmail())) {
            similarityTotal += similarity.apply(this.getEmail(), other.getEmail());
            similarityCriteriaCount++;
        }
        if (StringUtils.isNotEmpty(this.getZip()) && StringUtils.isNotEmpty(other.getZip())) {
            similarityTotal += similarity.apply(this.getZip(), other.getZip());
            similarityCriteriaCount++;
        }
        if (StringUtils.isNotEmpty(this.getAddress()) && StringUtils.isNotEmpty(other.getAddress())) {
            similarityTotal += similarity.apply(this.getAddress(), other.getAddress());
            similarityCriteriaCount++;
        }

        // Return average similarity based on which fields we compared
        return similarityTotal / similarityCriteriaCount;
    }
}
