package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyResumeBook;
import seedu.address.model.ResumeBook;
import seedu.address.model.item.Resume;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Resume[] getSampleResumes() {
        return new Resume[] {
            new Resume(new Name("Resume 1"), getTagSet("SE")),
            new Resume(new Name("Resume 2"), getTagSet("UI")),
        };
    }

    public static ReadOnlyResumeBook getSampleAddressBook() {
        ResumeBook sampleAb = new ResumeBook();
        for (Resume sampleResume : getSampleResumes()) {
            sampleAb.addResume(sampleResume);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
