package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Internship;
import seedu.address.testutil.InternshipBuilder;

public class EditInternshipDescriptorTest {
    private Internship sampleInternship = new InternshipBuilder().build();
    private EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();

    @Test
    public void equals() {
        editInternshipDescriptor.setName(sampleInternship.getName());
        editInternshipDescriptor.setRole(sampleInternship.getRole());
        editInternshipDescriptor.setFrom(sampleInternship.getFrom());
        editInternshipDescriptor.setTo(sampleInternship.getTo());
        editInternshipDescriptor.setDescription(sampleInternship.getDescription());
        editInternshipDescriptor.setTags(sampleInternship.getTags());

        EditInternshipDescriptor editInternshipDescriptorToTest =
                new EditInternshipDescriptor(editInternshipDescriptor);

        // same name -> returns true
        assertEquals(editInternshipDescriptor.getName().get(), sampleInternship.getName());

        // same object -> returns true
        assertEquals(editInternshipDescriptor.getRole().get(), sampleInternship.getRole());

        // same object -> returns true
        assertEquals(editInternshipDescriptor.getFrom().get(), sampleInternship.getFrom());

        // same object -> returns true
        assertEquals(editInternshipDescriptor.getTo().get(), sampleInternship.getTo());

        // same object -> returns true
        assertEquals(editInternshipDescriptor.getDescription().get(), sampleInternship.getDescription());

        // same object -> returns true
        assertEquals(editInternshipDescriptor.getTags().get(), sampleInternship.getTags());

        // same name -> returns true
        assertEquals(editInternshipDescriptorToTest.getName().get(), editInternshipDescriptor.getName().get());

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getRole().get(), editInternshipDescriptor.getRole().get());

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getFrom().get(), editInternshipDescriptor.getFrom().get());

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getTo().get(), editInternshipDescriptor.getTo().get());

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getDescription().get(),
                editInternshipDescriptor.getDescription().get());

        // same object -> returns true
        assertEquals(editInternshipDescriptorToTest.getTags().get(), editInternshipDescriptor.getTags().get());

        // same descriptor object -> returns true
        assertEquals(editInternshipDescriptor, editInternshipDescriptorToTest);

        // checks two descriptors if same -> returns true
        assertTrue(editInternshipDescriptor.equals(editInternshipDescriptorToTest));
    }

    @Test
    public void field_isAnyNonNull_falseIfAllNull() {
        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        assertEquals(editInternshipDescriptor.isAnyFieldEdited(), false);
    }
}


