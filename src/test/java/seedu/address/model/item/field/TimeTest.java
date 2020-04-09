package seedu.address.model.item.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));
    }

    @Test
    public void isValidTime() {
        // blank time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only

        // invalid pattern
        assertFalse(Time.isValidTime("09-01-1998")); // extra date
        assertFalse(Time.isValidTime("1998")); // missing month
        assertFalse(Time.isValidTime("01")); // missing year
        assertFalse(Time.isValidTime("09/01/1998")); // wrong format
        assertFalse(Time.isValidTime("duongpham")); // wrong format, not parsable

        // valid time
        assertTrue(Time.isValidTime("01-1998"));
    }

    @Test
    public void format_successful() {
        Time validTime = new Time("01-1998");
        assertEquals(validTime.format(), "Jan 1998");
    }
}
