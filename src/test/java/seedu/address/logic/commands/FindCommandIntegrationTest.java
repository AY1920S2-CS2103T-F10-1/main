package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternship.NINJA_VAN;
import static seedu.address.testutil.TypicalInternship.PAYPAL;
import static seedu.address.testutil.TypicalProject.ORBITAL;
import static seedu.address.testutil.TypicalResume.ME_RESUME;
import static seedu.address.testutil.TypicalResume.SE_RESUME;
import static seedu.address.testutil.TypicalSkill.REACT;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.find.FindCommand;
import seedu.address.logic.commands.find.FindInternshipCommand;
import seedu.address.logic.commands.find.FindProjectCommand;
import seedu.address.logic.commands.find.FindResumeCommand;
import seedu.address.logic.commands.find.FindSkillCommand;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.FindCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.field.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalResumeBook;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        FindCommand findInternship = new FindInternshipCommand(predicate);
        FindCommand findProject = new FindProjectCommand(predicate);
        FindCommand findSkill = new FindSkillCommand(predicate);
        FindCommand findResume = new FindResumeCommand(predicate);

        assertNotEquals(findInternship, findProject);
        assertNotEquals(findInternship, findResume);
        assertNotEquals(findInternship, findSkill);
        assertNotEquals(findProject, findResume);
        assertNotEquals(findProject, findSkill);
        assertNotEquals(findResume, findSkill);
    }

    @Test
    public void execute_zeroKeywords_noInternshipFound() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setInternshipToDisplay();
        CommandResult expectedCommandResult = new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, 0, "Internships"),
                expectedModel.getDisplayType());
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindInternshipCommand command = new FindInternshipCommand(predicate);
        model.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    }

    @Test
    public void execute_multipleKeywords_multipleInternshipsFound() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setInternshipToDisplay();
        CommandResult expectedCommandResult = new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, 2, "Internships"),
                expectedModel.getDisplayType());
        NameContainsKeywordsPredicate predicate = preparePredicate("Van PayPal");
        FindInternshipCommand command = new FindInternshipCommand(predicate);
        model.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(NINJA_VAN, PAYPAL), model.getFilteredItemList());
    }

    @Test
    public void execute_zeroKeywords_noProjectFound() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setProjectToDisplay();
        CommandResult expectedCommandResult = new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, 0, "Projects"),
                expectedModel.getDisplayType());
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindProjectCommand command = new FindProjectCommand(predicate);
        model.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    }

    @Test
    public void execute_singleKeyword_singleProjectFound() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setProjectToDisplay();
        CommandResult expectedCommandResult = new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, 1, "Projects"),
                expectedModel.getDisplayType());
        NameContainsKeywordsPredicate predicate = preparePredicate("Orbital");
        FindProjectCommand command = new FindProjectCommand(predicate);
        model.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(ORBITAL), model.getFilteredItemList());
    }

    @Test
    public void execute_zeroKeywords_noSkillFound() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setSkillToDisplay();
        CommandResult expectedCommandResult = new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, 0, "Skills"),
                expectedModel.getDisplayType());
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindSkillCommand command = new FindSkillCommand(predicate);
        model.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    }

    @Test
    public void execute_singleKeyword_singleSkillFound() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setSkillToDisplay();
        CommandResult expectedCommandResult = new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, 1, "Skills"),
                expectedModel.getDisplayType());
        NameContainsKeywordsPredicate predicate = preparePredicate("React");
        FindSkillCommand command = new FindSkillCommand(predicate);
        model.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(REACT), model.getFilteredItemList());
    }

    @Test
    public void execute_zeroKeywords_noResumeFound() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setResumeToDisplay();
        CommandResult expectedCommandResult = new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, 0, "Resumes"),
                expectedModel.getDisplayType());
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindResumeCommand command = new FindResumeCommand(predicate);
        model.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    }

    @Test
    public void execute_singleKeyword_multipleResumesFound() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setResumeToDisplay();
        CommandResult expectedCommandResult = new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, 2, "Resumes"),
                expectedModel.getDisplayType());
        NameContainsKeywordsPredicate predicate = preparePredicate("Engineering");
        FindResumeCommand command = new FindResumeCommand(predicate);
        model.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(ME_RESUME, SE_RESUME), model.getFilteredItemList());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
