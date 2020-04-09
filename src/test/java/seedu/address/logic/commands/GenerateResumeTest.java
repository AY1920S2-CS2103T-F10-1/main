package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.generate.GenerateResumeCommand.MESSAGE_GENERATE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.generate.GenerateResumeCommand;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Person;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.ResumeBuilder;
import seedu.address.testutil.SkillBuilder;

public class GenerateResumeTest {
    private Person user = new PersonBuilder().build();
    private Internship internship = new InternshipBuilder().build();
    private Project project = new ProjectBuilder().build();
    private Skill skill = new SkillBuilder().build();
    private ResumeBuilder builder = new ResumeBuilder().withInternship(internship)
            .withProject(project).withSkill(skill);
    private Resume resume = builder.build();

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GenerateResumeCommand(null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {

        ModelStub modelStub = new GenerateResumeTest.ModelStubWithResume(user, resume);
        Index invalidIndex = INDEX_THIRD_ITEM;
        GenerateResumeCommand generateResumeCommand = new GenerateResumeCommand(invalidIndex);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> generateResumeCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_generateSuccessful() throws CommandException {
        Person user = new PersonBuilder().build();
        Resume validItem = new ResumeBuilder().build();
        ModelStub modelStub = new GenerateResumeTest.ModelStubWithResume(user, validItem);
        Index validIndex = INDEX_FIRST_ITEM;
        Resume toGenerate = modelStub.getResumeByIndex(validIndex);
        GenerateResumeCommand generateResumeCommand = new GenerateResumeCommand(validIndex);

        CommandResult commandResult = generateResumeCommand.execute(modelStub);
        assertEquals(String.format(MESSAGE_GENERATE_SUCCESS, toGenerate.getName().toString(),
                toGenerate.getName().toString(), modelStub.getDisplayType()), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);
        GenerateResumeCommand generateACommand = new GenerateResumeCommand(indexA);
        GenerateResumeCommand generateBCommand = new GenerateResumeCommand(indexB);

        // same object -> returns true
        assertTrue(generateACommand.equals(generateACommand));

        // same value -> returns true
        GenerateResumeCommand generateACommandCopy = new GenerateResumeCommand(indexA);
        assertTrue(generateACommand.equals(generateACommandCopy));

        // different types -> returns false
        assertFalse(generateACommand.equals(1));

        // null -> returns false
        assertFalse(generateACommand.equals(null));

        // different index -> returns false
        assertFalse(generateACommand.equals(generateBCommand));
    }

    /**
     * A Model stub that contains a single user and a single Resume.
     */
    private class ModelStubWithResume extends ModelStub {
        private final Person user;
        private final Resume item;

        ModelStubWithResume(Person user, Resume item) {
            requireAllNonNull(user, item);
            this.user = user;
            this.item = item;
        }

        @Override
        public Person getUser() {
            return this.user;
        }

        @Override
        public boolean hasResume(Resume item) {
            requireNonNull(item);
            return this.item.isSame(item);
        }

        @Override
        public int getResumeSize() {
            return 1;
        }


        @Override
        public Resume getResumeByIndex(Index index) {
            return item;
        }

        @Override
        public boolean hasInternshipId(int id) {
            return id == internship.getId();
        }

        @Override
        public Internship getInternshipById(int id) {
            return internship;
        }

        @Override
        public boolean hasProjectId(int id) {
            return id == project.getId();
        }

        @Override
        public Project getProjectById(int id) {
            return project;
        }

        @Override
        public boolean hasSkillId(int id) {
            return id == skill.getId();
        }

        @Override
        public Skill getSkillById(int id) {
            return skill;
        }
    }
}
