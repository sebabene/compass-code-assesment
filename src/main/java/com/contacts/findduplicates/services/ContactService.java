package com.contacts.findduplicates.services;

import com.contacts.findduplicates.models.Contact;
import com.contacts.findduplicates.models.ContactDuplicateAccuracy;
import com.contacts.findduplicates.models.DuplicateContact;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContactService {

    public static List<Contact> readContacts() throws IOException {
        List<Contact> contacts = new ArrayList<>();

        // Load contacts file from resources
        InputStream contactsFileStream = ContactService.class.getResourceAsStream("/contacts.csv");
        if (contactsFileStream == null) {
            throw new FileNotFoundException("Error reading contacts file: file not found");
        }

        // Read all contacts and return them as a list
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(contactsFileStream))) {
            reader.readLine(); // Skip header
            String contact;
            while ((contact = reader.readLine()) != null) {
                contacts.add(new Contact(contact));
            }
        }

        return contacts;
    }

    public static List<DuplicateContact> findDuplicates(List<Contact> contacts) {
        List<DuplicateContact> duplicates = new ArrayList<>();

        // Iterate the list once and compare each contact with the ones with index greater than it
        for (int i = 0; i <= contacts.size(); i++) {
            for (int j = i + 1; j < contacts.size(); j++) {
                Double similarity = contacts.get(i).compareTo(contacts.get(j));
                DuplicateContact duplicate = new DuplicateContact(contacts.get(i).getId(), contacts.get(j).getId(), similarity);

                // We don't add results with less accuracy than Low
                if (duplicate.getAccuracy() != ContactDuplicateAccuracy.None) {
                    duplicates.add(duplicate);
                }
            }
        }

        return duplicates;
    }

    public static void printDuplicates(List<DuplicateContact> duplicates) {
        System.out.println("ContactID Source,ContactID Match,Accuracy");
        duplicates.forEach(System.out::println);
    }
}
