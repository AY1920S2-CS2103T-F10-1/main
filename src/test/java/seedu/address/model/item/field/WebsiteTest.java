package seedu.address.model.item.field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WebsiteTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Website(null));
    }

    @Test
    public void constructor_invalidWebsite_throwsIllegalArgumentException() {
        String invalidWebsite = "";
        assertThrows(IllegalArgumentException.class, () -> new Website(invalidWebsite));
    }

    @Test
    public void isValidWebsite() {
        // null website
        assertThrows(NullPointerException.class, () -> new Website(null));

        // blank website
        assertFalse(Website.isValidWebsite("")); // empty string
        assertFalse(Website.isValidWebsite(" ")); // spaces only

        // missing parts
        assertFalse(Website.isValidWebsite("orbital")); // missing domain
        assertFalse(Website.isValidWebsite("orbital.")); // missing domain name

        // invalid parts
        assertFalse(Website.isValidWebsite("orbital//www.orbital.com")); // invalid http
        assertFalse(Website.isValidWebsite("https://www.orbital--orbital.com")); // invalid name

        // valid website
        assertTrue(Website.isValidWebsite("https://www.orbital.com"));
        assertTrue(Website.isValidWebsite("www.orbital.com"));
        assertTrue(Website.isValidWebsite("orbital.com"));
    }
}
