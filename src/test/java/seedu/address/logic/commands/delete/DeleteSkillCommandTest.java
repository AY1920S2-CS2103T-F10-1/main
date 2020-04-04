package seedu.address.logic.commands.delete;

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
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.item.Skill;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.SkillBuilder;

public class DeleteSkillCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteSkillCommand(null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Skill validItem = new SkillBuilder().build();
        ModelStub modelStub = new ModelStubWithSkill(validItem);
        Index invalidIndex = INDEX_THIRD_ITEM;
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(invalidIndex);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> deleteSkillCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_deleteSuccessful() throws CommandException {
        ModelStubContainingSkillDeleted modelStub = new ModelStubContainingSkillDeleted();
        Index validIndex = INDEX_FIRST_ITEM;
        Skill toDelete = modelStub.getSkillByIndex(validIndex);
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(validIndex);
        assertEquals(Arrays.asList(toDelete), modelStub.skills);

        CommandResult commandResult = deleteSkillCommand.execute(modelStub);
        assertEquals(String.format(DeleteSkillCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                toDelete.getType().getFullType()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);
        DeleteSkillCommand deleteACommand = new DeleteSkillCommand(indexA);
        DeleteSkillCommand deleteBCommand = new DeleteSkillCommand(indexB);

        // same object -> returns true
        assertTrue(deleteACommand.equals(deleteACommand));

        // same value -> returns true
        DeleteSkillCommand deleteACommandCopy = new DeleteSkillCommand(indexA);
        assertTrue(deleteACommand.equals(deleteACommandCopy));

        // different types -> returns false
        assertFalse(deleteACommand.equals(1));

        // null -> returns false
        assertFalse(deleteACommand.equals(null));

        // different index -> returns false
        assertFalse(deleteACommand.equals(deleteBCommand));
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
     * A Model stub that always contain the Skill being deleted.
     */
    private class ModelStubContainingSkillDeleted extends ModelStub {
        final ArrayList<Skill> skills = new ArrayList<>();
        ModelStubContainingSkillDeleted() {
            skills.add(new SkillBuilder().withName("Stub skill").build());
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
        public void deleteSkill(Skill skill) {
            skills.remove(skill);
        }
    }
}
