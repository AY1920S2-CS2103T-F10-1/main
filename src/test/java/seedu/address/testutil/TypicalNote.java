package seedu.address.testutil;

import seedu.address.model.item.Note;

/**
 * A utility class containing a list of {@code Note} objects to be used in tests.
 */
public class TypicalNote {
    public static final Note FINISH_HOMEWORK = new NoteBuilder()
            .withName("Finish Homework")
            .withTime("03-2020")
            .withTags()
            .build();
    public static final Note FINISH_CS_2103 = new NoteBuilder()
            .withName("Finish CS2103")
            .withTime("12-2020")
            .withTags()
            .build();
}
