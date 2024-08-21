import com.contacts.findduplicates.models.Contact;
import com.contacts.findduplicates.models.ContactDuplicateAccuracy;
import com.contacts.findduplicates.models.DuplicateContact;
import com.contacts.findduplicates.services.ContactService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactServiceTests {

    @Test
    public void testReadContacts() throws IOException {
        List<Contact> contacts = ContactService.readContacts();

        assertEquals(2, contacts.size());
        assertEquals(1, contacts.get(0).getId());
        assertEquals("Ciara", contacts.get(0).getFirstName());
        assertEquals("French", contacts.get(0).getLastName());
        assertEquals("", contacts.get(0).getEmail());
        assertEquals("39746", contacts.get(0).getZip());
        assertEquals("449-6990 Tellus. Rd.", contacts.get(0).getAddress());
        assertEquals(2, contacts.get(1).getId());
        assertEquals("Charles", contacts.get(1).getFirstName());
        assertEquals("Pacheco", contacts.get(1).getLastName());
        assertEquals("nulla.eget@protonmail.couk", contacts.get(1).getEmail());
        assertEquals("76837", contacts.get(1).getZip());
        assertEquals("", contacts.get(1).getAddress());
    }
    @Test
    public void testFindDuplicates() {
        Contact contact1 = new Contact("1001,C,F,mollis.lectus.pede@outlook.net,,449-6990 Tellus. Rd.");
        Contact contact2 = new Contact("1002,C,French,mollis.lectus.pede@outlook.net,39746,449-6990 Tellus. Rd.");
        Contact contact3 = new Contact("1003,Ciara,F,non.lacinia.at@zoho.ca,39746,");

        List<DuplicateContact> duplicates = ContactService.findDuplicates(Arrays.asList(contact1, contact2, contact3));

        assertEquals(3, duplicates.size());
        assertEquals(1001, duplicates.get(0).getContactSourceId());
        assertEquals(1002, duplicates.get(0).getContactMatchId());
        assertEquals(ContactDuplicateAccuracy.High, duplicates.get(0).getAccuracy());
        assertEquals(1001, duplicates.get(1).getContactSourceId());
        assertEquals(1003, duplicates.get(1).getContactMatchId());
        assertEquals(ContactDuplicateAccuracy.Low, duplicates.get(1).getAccuracy());
        assertEquals(1002, duplicates.get(2).getContactSourceId());
        assertEquals(1003, duplicates.get(2).getContactMatchId());
        assertEquals(ContactDuplicateAccuracy.Low, duplicates.get(2).getAccuracy());
    }
}
