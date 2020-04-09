package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalResume.ME_RESUME;
import static seedu.address.testutil.TypicalResume.SE_RESUME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ResumeBuilder;

public class ResumeTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Resume resume = new ResumeBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> resume.getTags().remove(0));
    }

    @Test
    public void equal() {
        // same object -> returns true
        assertTrue(SE_RESUME.equals(SE_RESUME));

        // null -> returns false
        assertFalse(SE_RESUME.equals(null));

        // different object -> return false
        assertFalse(SE_RESUME.equals(ME_RESUME));
    }
}
