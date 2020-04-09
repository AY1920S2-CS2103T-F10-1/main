package seedu.address.model.item.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DisplayPictureTest {
    private DisplayPicture pic1 = new DisplayPicture("/Users/nhamquochung/Desktop/test.png");
    private DisplayPicture pic2 = new DisplayPicture("/images/duke.png");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DisplayPicture(null));
    }

    @Test
    public void testToString() {
        assertEquals(pic1.toString(), "/Users/nhamquochung/Desktop/test.png");
    }

    @Test
    public void equal() {
        assertNotEquals(pic2, null);
        assertNotEquals(pic2, 5);
        assertNotEquals(pic2, pic1);
        assertEquals(pic2, new DisplayPicture("/images/duke.png"));
    }
}
