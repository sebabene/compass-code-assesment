package com.contacts.findduplicates;

import com.contacts.findduplicates.models.Contact;
import com.contacts.findduplicates.models.DuplicateContact;
import com.contacts.findduplicates.services.ContactService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Contact> contacts = ContactService.readContacts();
            List<DuplicateContact> duplicates = ContactService.findDuplicates(contacts);
            ContactService.printDuplicates(duplicates);
        } catch (Exception e) {
            System.err.println("Error processing contact duplicates: " + e.getMessage());
        }
    }
}
