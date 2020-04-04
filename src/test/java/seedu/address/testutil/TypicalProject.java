package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEBSITE_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEBSITE_ORBITAL;

import seedu.address.model.item.Project;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProject {
    // Manually added - project's details found in {@code CommandTestUtil}
    public static final Project ORBITAL = new ProjectBuilder().withName(VALID_PROJECT_NAME_ORBITAL)
            .withTime(VALID_TIME_1).withWebsite(VALID_WEBSITE_ORBITAL)
            .withDescription(VALID_DESCRIPTION_ORBITAL).build();
    public static final Project DUKE = new ProjectBuilder().withName(VALID_PROJECT_NAME_DUKE)
            .withTime(VALID_TIME_2).withWebsite(VALID_WEBSITE_DUKE).withDescription(VALID_DESCRIPTION_DUKE).build();
}
