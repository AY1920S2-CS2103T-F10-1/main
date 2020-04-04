package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.item.Skill;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.SkillBuilder;

public class AddSkillCommandTest {
    @Test
    public void constructor_nullSkill_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSkillCommand(null));
    }

    @Test
    public void execute_skillAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingSkillAdded modelStub = new ModelStubAcceptingSkillAdded();
        Skill validItem = new SkillBuilder().build();

        CommandResult commandResult = new AddSkillCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddSkillCommand.MESSAGE_SUCCESS, validItem.getType().getFullType()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.skills);
    }

    @Test
    public void execute_duplicateSkill_throwsCommandException() {
        Skill validItem = new SkillBuilder().build();
        AddSkillCommand addCommand = new AddSkillCommand(validItem);
        ModelStub modelStub = new ModelStubWithSkill(validItem);

        assertThrows(CommandException.class,
                AddSkillCommand.MESSAGE_DUPLICATE_ITEM, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Skill java = new SkillBuilder().withName("NameA").build();
        Skill c = new SkillBuilder().withName("NameB").build();
        AddSkillCommand addJavaCommand = new AddSkillCommand(java);
        AddSkillCommand addCCommand = new AddSkillCommand(c);

        // same object -> returns true
        assertTrue(addJavaCommand.equals(addJavaCommand));

        // same values -> returns true
        AddSkillCommand addJavaCommandCopy = new AddSkillCommand(java);
        assertTrue(addJavaCommand.equals(addJavaCommandCopy));

        // different types -> returns false
        assertFalse(addJavaCommand.equals(1));

        // null -> returns false
        assertFalse(addJavaCommand.equals(null));

        // different skill -> returns false
        assertFalse(addJavaCommand.equals(addCCommand));
    }

    /**
     * A Model stub that contains a single skill.
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
     * A Model stub that always accept the skill being added.
     */
    private class ModelStubAcceptingSkillAdded extends ModelStub {
        final ArrayList<Skill> skills = new ArrayList<>();

        @Override
        public void addSkill(Skill skill) {
            skills.add(skill);
        }
    }

}
