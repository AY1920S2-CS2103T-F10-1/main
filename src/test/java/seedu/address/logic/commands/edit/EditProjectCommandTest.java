package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.item.Project;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TypicalProject;

public class EditProjectCommandTest {

    private Project sampleEditedProject = TypicalProject.ORBITAL;

    private EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditProjectCommand(null, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Project validItem = new ProjectBuilder().build();
        ModelStubWithProject modelStub = new ModelStubWithProject(validItem);
        Index invalidIndex = INDEX_THIRD_ITEM;
        setEditProjectDescriptor();
        EditProjectCommand editProjectCommand = new EditProjectCommand(invalidIndex, editProjectDescriptor);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> editProjectCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_editSuccessful() throws CommandException {
        ModelStubContainingProjectEdited modelStub = new ModelStubContainingProjectEdited();
        setEditProjectDescriptor();
        Index validIndex = INDEX_FIRST_ITEM;

        Project toEdit = modelStub.getProjectByIndex(validIndex);
        EditProjectCommand editProjectCommand = new EditProjectCommand(validIndex, editProjectDescriptor);
        assertEquals(Arrays.asList(toEdit), modelStub.projects);

        CommandResult commandResult = editProjectCommand.execute(modelStub);

        assertEquals(String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS, sampleEditedProject),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void create_withNullDescriptorField_editedProject() {
        editProjectDescriptor.setTags(null);
        editProjectDescriptor.setName(null);
        assertEquals(sampleEditedProject,
                EditProjectCommand.createEditedProject(sampleEditedProject, editProjectDescriptor));
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);

        setEditProjectDescriptor();

        EditProjectCommand editACommand = new EditProjectCommand(indexA, editProjectDescriptor);
        EditProjectCommand editBCommand = new EditProjectCommand(indexB, editProjectDescriptor);

        // same object -> returns true
        assertTrue(editACommand.equals(editACommand));

        // same value -> returns true
        EditProjectCommand editACommandCopy = new EditProjectCommand(indexA, editProjectDescriptor);
        assertTrue(editACommand.equals(editACommandCopy));

        // different types -> returns false
        assertFalse(editACommand.equals(1));

        // null -> returns false
        assertFalse(editACommand.equals(null));

        // different index -> returns false
        assertFalse(editACommand.equals(editBCommand));
    }


    /**
     * A method to set fields in the edit project descriptor.
     */
    public void setEditProjectDescriptor() {
        editProjectDescriptor.setName(sampleEditedProject.getName());
        editProjectDescriptor.setWebsite(sampleEditedProject.getWebsite());
        editProjectDescriptor.setTime(sampleEditedProject.getTime());
        editProjectDescriptor.setDescription(sampleEditedProject.getDescription());
        editProjectDescriptor.setTags(sampleEditedProject.getTags());
    }

    /**
     * A Model stub that contains a single Project.
     */
    private class ModelStubWithProject extends ModelStub {
        private final Project item;

        ModelStubWithProject(Project item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasProject(Project item) {
            requireNonNull(item);
            return this.item.isSame(item);
        }
    }

    /**
     * A Model stub that always contain the Project being edited.
     */
    private class ModelStubContainingProjectEdited extends ModelStub {
        final ArrayList<Project> projects = new ArrayList<>();

        ModelStubContainingProjectEdited() {
            projects.add(new ProjectBuilder().build());
        }

        @Override
        public Project getProjectByIndex(Index index) {
            return projects.get(index.getZeroBased());
        }

        @Override
        public int getProjectSize() {
            return projects.size();
        }

        @Override
        public void setProject(Project target, Project editedProject) {
            int index = projects.indexOf(target);
            projects.set(index, editedProject);
        }
    }
}

