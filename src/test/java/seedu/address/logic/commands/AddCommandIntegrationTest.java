package seedu.address.logic.commands;

/*import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersonalDetails.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;*/

import seedu.address.model.Model;
/*import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.PersonalDetail;
import seedu.address.testutil.PersonBuilder;*/

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    /*@BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }*/

    /*@Test
    public void execute_newPerson_success() {
        PersonalDetail validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getResumeBook(), new UserPrefs());
        expectedModel.addItem(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }*/

    /*@Test
    public void execute_duplicatePerson_throwsCommandException() {
        PersonalDetail personInList = model.getResumeBook().getItemToDisplayList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }
*/
}
