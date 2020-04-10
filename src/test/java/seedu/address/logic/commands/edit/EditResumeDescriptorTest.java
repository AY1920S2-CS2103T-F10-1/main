package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Resume;
import seedu.address.testutil.ResumeBuilder;

public class EditResumeDescriptorTest {

    private Resume sampleResume = new ResumeBuilder().build();

    private EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();

    @Test
    public void equals() {
        editResumeDescriptor.setName(sampleResume.getName());
        editResumeDescriptor.setTags(sampleResume.getTags());

        EditResumeDescriptor editResumeDescriptorTest =
                new EditResumeDescriptor(editResumeDescriptor);

        // same name -> returns true
        assertEquals(editResumeDescriptor.getName().get(), sampleResume.getName());

        // same object -> returns true
        assertEquals(editResumeDescriptor.getTags().get(), sampleResume.getTags());

        // same name -> returns true
        assertEquals(editResumeDescriptorTest.getName().get(), editResumeDescriptor.getName().get());

        // same object -> returns true
        assertEquals(editResumeDescriptorTest.getTags().get(),
                editResumeDescriptor.getTags().get());

        // same descriptor object -> returns true
        assertEquals(editResumeDescriptor, editResumeDescriptorTest);

        // checks two descriptors if same -> returns true
        assertTrue(editResumeDescriptor.equals(editResumeDescriptorTest));
    }

    @Test
    public void field_isAnyNonNull_falseIfAllNull() {
        EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
        assertEquals(editResumeDescriptor.isAnyFieldEdited(), false);
    }
}

