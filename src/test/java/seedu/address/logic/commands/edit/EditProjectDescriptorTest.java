package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Project;
import seedu.address.testutil.ProjectBuilder;

public class EditProjectDescriptorTest {
    private Project sampleProject = new ProjectBuilder().build();
    private EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();

    @Test
    public void equals() {
        editProjectDescriptor.setName(sampleProject.getName());
        editProjectDescriptor.setTime(sampleProject.getTime());
        editProjectDescriptor.setWebsite(sampleProject.getWebsite());
        editProjectDescriptor.setDescription(sampleProject.getDescription());
        editProjectDescriptor.setTags(sampleProject.getTags());

        EditProjectDescriptor editProjectDescriptorToTest =
                new EditProjectDescriptor(editProjectDescriptor);

        // same name -> returns true
        assertEquals(editProjectDescriptor.getName().get(), sampleProject.getName());

        // same object -> returns true
        assertEquals(editProjectDescriptor.getTime().get(), sampleProject.getTime());

        // same object -> returns true
        assertEquals(editProjectDescriptor.getWebsite().get(), sampleProject.getWebsite());

        // same object -> returns true
        assertEquals(editProjectDescriptor.getDescription().get(), sampleProject.getDescription());

        // same object -> returns true
        assertEquals(editProjectDescriptor.getTags().get(), sampleProject.getTags());

        // same name -> returns true
        assertEquals(editProjectDescriptorToTest.getName().get(), editProjectDescriptor.getName().get());

        // same object -> returns true
        assertEquals(editProjectDescriptorToTest.getTime().get(), editProjectDescriptor.getTime().get());

        // same object -> returns true
        assertEquals(editProjectDescriptorToTest.getWebsite().get(), editProjectDescriptor.getWebsite().get());

        // same object -> returns true
        assertEquals(editProjectDescriptorToTest.getDescription().get(),
                editProjectDescriptor.getDescription().get());

        // same object -> returns true
        assertEquals(editProjectDescriptorToTest.getTags().get(),
                editProjectDescriptor.getTags().get());

        // same descriptor object -> returns true
        assertEquals(editProjectDescriptor, editProjectDescriptorToTest);

        // checks two descriptors if same -> returns true
        assertTrue(editProjectDescriptor.equals(editProjectDescriptorToTest));
    }

    @Test
    public void field_isAnyNonNull_falseIfAllNull() {
        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        assertEquals(editProjectDescriptor.isAnyFieldEdited(), false);
    }
}

