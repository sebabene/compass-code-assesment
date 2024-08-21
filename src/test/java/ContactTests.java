import com.contacts.findduplicates.models.Contact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTests {

    @Test
    public void testContactCreation() {
        Contact contact = new Contact("100,John,Doe,jdoe@gmail.com,12345,123 Main St");

        assertEquals(100, contact.getId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("jdoe@gmail.com", contact.getEmail());
        assertEquals("12345", contact.getZip());
        assertEquals("123 Main St", contact.getAddress());
    }

    @ParameterizedTest
    @MethodSource
    public void testCompareTo(String contact1Str, String contact2Str, Double expected) {
        Contact contact1 = new Contact(contact1Str);
        Contact contact2 = new Contact(contact2Str);

        assertEquals(expected, contact1.compareTo(contact2));
    }

    public static Stream<Arguments> testCompareTo() {
        return Stream.of(
                Arguments.of("111,John,Doe,jdoe@gmail.com,12345,123 Main St", "222,John,Doe,jdoe@gmail.com,12345,123 Main St", 1.0),
                Arguments.of("111,John,Doe,jdoe@gmail.com,12345,123 Main St", "222,J,D,jdoe@gmail.com,12345,123 Main St", 0.915),
                Arguments.of("111,John,Doe,jdoe@gmail.com,12345,123 Main St", "222,John,Doe,jdoe@hotmail.com,,123 Main St", 0.9735920329670329),
                Arguments.of("111,John,Doe,jdoe@gmail.com,12345,123 Main St", "222,John,D,jdoe@gmail.com,12345,", 0.95),
                Arguments.of("111,John,Doe,jdoe@gmail.com,12345,123 Main St", "222,J,Doe,johnd@gmail.com,12345,", 0.9226236263736264),
                Arguments.of("111,John,Doe,jdoe@gmail.com,12345,123 Main St", "222,John,Smith,,489633,123 Main Street", 0.6005555555555555),
                Arguments.of("111,John,Doe,jdoe@gmail.com,12345,123 Main St", "222,Jack,Smith,jsmith@gmail.com,,789 Another Street", 0.41258267195767195)
        );
    }
}
