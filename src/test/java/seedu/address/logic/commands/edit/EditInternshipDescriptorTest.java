package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

public class EditInternshipDescriptorTest {
    public static final String[] DEFAULT_TAGS = {"java", "backend"};

    private Name name = new Name("name");
    private String role = "role";
    private Time from = new Time("10-2020");
    private Time to = new Time("12-2020");
    private String description = "description";
    private Set<Tag> tags = new HashSet<>();
    private EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();

    @Test
    public void equals() {
        editInternshipDescriptor.setName(name);
        editInternshipDescriptor.setRole(role);
        editInternshipDescriptor.setFrom(from);
        editInternshipDescriptor.setTo(to);
        editInternshipDescriptor.setDescription(description);
        editInternshipDescriptor.setTags(tags);
        tags.addAll(Arrays.stream(DEFAULT_TAGS).map(Tag::new).collect(Collectors.toList()));

        EditInternshipDescriptor editInternshipDescriptorToTest =
                new EditInternshipDescriptor(editInternshipDescriptor);

        // same name -> returns true
        assertEquals(editInternshipDescriptorToTest.getName().get(), name);

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getRole().get(), role);

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getFrom().get(), from);

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getTo().get(), to);

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getDescription().get(), description);
    }
}
