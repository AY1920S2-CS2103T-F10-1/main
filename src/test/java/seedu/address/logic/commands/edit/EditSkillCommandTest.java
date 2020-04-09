package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.edit.EditSkillCommand.MESSAGE_EDIT_SKILL_SUCCESS;
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
import seedu.address.model.item.Skill;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.SkillBuilder;
import seedu.address.testutil.TypicalSkill;

public class EditSkillCommandTest {

    private EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();

    Skill sampleEditedSkill = TypicalSkill.GIT;

    @Test
    public void constructor_nullSkill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditSkillCommand(null, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Skill validItem = new SkillBuilder().build();
        ModelStub modelStub = new ModelStubWithSkill(validItem);
        Index invalidIndex = INDEX_THIRD_ITEM;
        setEditSkillDescriptor();
        EditSkillCommand editSkillCommand = new EditSkillCommand(invalidIndex, editSkillDescriptor);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> editSkillCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_editSuccessful() throws CommandException {
        ModelStubContainingSkillEdited modelStub = new ModelStubContainingSkillEdited(sampleEditedSkill);
        setEditSkillDescriptor();
        Index validIndex = INDEX_FIRST_ITEM;

        Skill toEdit = modelStub.getSkillByIndex(validIndex);
        EditSkillCommand editSkillCommand = new EditSkillCommand(validIndex, editSkillDescriptor);
        assertEquals(Arrays.asList(toEdit), modelStub.skills);

        CommandResult commandResult = editSkillCommand.execute(modelStub);

        assertEquals(String.format(MESSAGE_EDIT_SKILL_SUCCESS, sampleEditedSkill),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void create_withNullDescriptorField_EditedSkill() {
        editSkillDescriptor.setTags(null);
        editSkillDescriptor.setName(null);
        Index validIndex = INDEX_FIRST_ITEM;
        EditSkillCommand editSkillCommand = new EditSkillCommand(validIndex, editSkillDescriptor);
        assertEquals(sampleEditedSkill,
                editSkillCommand.createEditedSkill(sampleEditedSkill, editSkillDescriptor));
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);

        setEditSkillDescriptor();

        EditSkillCommand editACommand = new EditSkillCommand(indexA, editSkillDescriptor);
        EditSkillCommand editBCommand = new EditSkillCommand(indexB, editSkillDescriptor);

        // same object -> returns true
        assertTrue(editACommand.equals(editACommand));

        // same value -> returns true
        EditSkillCommand editACommandCopy = new EditSkillCommand(indexA, editSkillDescriptor);
        assertTrue(editACommand.equals(editACommandCopy));

        // different types -> returns false
        assertFalse(editACommand.equals(1));

        // null -> returns false
        assertFalse(editACommand.equals(null));

        // different index -> returns false
        assertFalse(editACommand.equals(editBCommand));
    }


    /**
     * A method to set fields in the edit skill descriptor.
     */
    public void setEditSkillDescriptor() {
        editSkillDescriptor.setName(sampleEditedSkill.getName());
        editSkillDescriptor.setLevel(sampleEditedSkill.getLevel());
        editSkillDescriptor.setTags(sampleEditedSkill.getTags());
    }

    /**
     * A Model stub that contains a single Skill.
     */
    private class ModelStubWithSkill extends ModelStub {
        private final Skill item;

        ModelStubWithSkill(Skill item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasSkill(Skill item) {
            requireNonNull(item);
            return this.item.isSame(item);
        }
    }

    /**
     * A Model stub that always contain the Skill being edited.
     */
    private class ModelStubContainingSkillEdited extends ModelStub {
        final ArrayList<Skill> skills = new ArrayList<>();
        private final Skill item;

        ModelStubContainingSkillEdited(Skill item) {
            requireNonNull(item);
            this.item = item;
            skills.add(item);
        }

        @Override
        public Skill getSkillByIndex(Index index) {
            return skills.get(index.getZeroBased());
        }

        @Override
        public int getSkillSize() {
            return skills.size();
        }

        @Override
        public void addSkill(Skill skill) {
            skills.add(skill);
        }

        @Override
        public void setSkill(Skill target, Skill editedSkill) {
            int index = skills.indexOf(target);
            skills.set(index, editedSkill);
        }

        @Override
        public boolean hasSkill(Skill item) {
            requireNonNull(item);
            return this.item.isSame(item);
        }
    }
}

