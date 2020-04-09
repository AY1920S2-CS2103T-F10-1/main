package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.EditUserCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Person;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * Edits user profile information.
 */
public class EditUserCommand extends Command {

    public static final String COMMAND_WORD = "me";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the user profile in our resuMeme. "
            + "Parameters: "
            + PREFIX_DP + "DISPLAY PROFILE "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GITHUB + "GITHUB "
            + PREFIX_UNIVERSITY + "UNIVERSITY "
            + PREFIX_MAJOR + "MAJOR "
            + PREFIX_FROM + "FROM "
            + PREFIX_TO + "TO "
            + PREFIX_CAP + "CAP\n"
            + "Example: \n" + COMMAND_WORD + " "
            + PREFIX_DP + "/Users/nhamquochung/Desktop/test.png "
            + PREFIX_NAME + "HUNG "
            + PREFIX_PHONE + "91648888 "
            + PREFIX_EMAIL + "nhamhung.gttn@gmail.com "
            + PREFIX_GITHUB + "nhamhung "
            + PREFIX_UNIVERSITY + "NUS "
            + PREFIX_MAJOR + "CS "
            + PREFIX_FROM + "2018 "
            + PREFIX_TO + "2022 "
            + PREFIX_CAP + "5.0 ";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited User Profile: %1$s";

    private EditUserDescriptor editUserDescriptor;

    /**
     * @param editUserDescriptor details to edit the person with.
     */
    public EditUserCommand(EditUserDescriptor editUserDescriptor) {
        this.editUserDescriptor = editUserDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person userToEdit = model.getUser();

        Person editedUser = createEditedUser(userToEdit, editUserDescriptor);
        model.setUser(editedUser);
        model.commitResumeBook();

        return new EditUserCommandResult(editedUser.toString(),
                String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedUser),
                model.getDisplayType());
    }

    /**
     * Create the user after edited.
     * @param toEdit
     * @param editUserDescriptor
     * @return
     */
    private static Person createEditedUser(Person toEdit, EditUserDescriptor editUserDescriptor) {
        DisplayPicture displayPicture = editUserDescriptor.getDisplayPicture().orElse(toEdit.getDisplayPicture());
        Name name = editUserDescriptor.getName().orElse(toEdit.getName());
        Phone phone = editUserDescriptor.getPhone().orElse(toEdit.getPhone());
        Email email = editUserDescriptor.getEmail().orElse(toEdit.getEmail());
        Github github = editUserDescriptor.getGithub().orElse(toEdit.getGithub());
        String university = editUserDescriptor.getUniversity().orElse(toEdit.getUniversity());
        String major = editUserDescriptor.getMajor().orElse(toEdit.getMajor());
        Time from = editUserDescriptor.getFrom().orElse(toEdit.getFrom());
        Time to = editUserDescriptor.getTo().orElse(toEdit.getTo());
        double cap = editUserDescriptor.getCap().orElse(toEdit.getCap());
        return new Person(displayPicture, name, phone, email, github, university, major, from, to, cap);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserCommand)) {
            return false;
        }

        // state check
        EditUserCommand e = (EditUserCommand) other;
        return editUserDescriptor.equals(e.editUserDescriptor);
    }
}
