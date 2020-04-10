package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.EditCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * Edits an Internship Item in the resume book.
 */
public class EditInternshipCommand extends EditCommand {
    public static final String MESSAGE_FROM_TO_MISORDER = "\'from\' cannot be later than \'to\' field. ";
    public static final String MESSAGE_EDIT_INTERNSHIP_SUCCESS = "Edited Internship: %1$s";

    private static final String FIELDS = "Examples: "
            + COMMAND_WORD + " "
            + PREFIX_ITEM + "int "
            + "[" + PREFIX_NAME + "COMPANY NAME] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_FROM + "FROM] "
            + "[" + PREFIX_TO + "TO] "
            + "[" + PREFIX_DESCRIPTION + "DESC] "
            + "[" + PREFIX_TAG + "TAG]....\n";
    private static final String EXAMPLE = "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_ITEM + " int "
            + PREFIX_NAME + " Shopee "
            + PREFIX_FROM + " 05-2020 "
            + PREFIX_TO + " 08-2020 "
            + PREFIX_ROLE + " Backend Engineer"
            + PREFIX_DESCRIPTION + " I did some work "
            + PREFIX_TAG + " backend ";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n"
            + FIELDS
            + EXAMPLE;

    private EditInternshipDescriptor editInternshipDescriptor;

    /**
     * @param index                of the internship in the filtered internship list to edit
     * @param editInternshipDescriptor details to edit the internship with
     */
    public EditInternshipCommand(Index index, EditInternshipDescriptor editInternshipDescriptor) {
        super(index);
        this.editInternshipDescriptor = editInternshipDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getInternshipSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Internship toEdit = model.getInternshipByIndex(index);

        Internship editedInternship = createEditedInternship(toEdit, editInternshipDescriptor);

        if (editedInternship.getFrom().compareTo(editedInternship.getTo()) > 0) {
            throw new CommandException(MESSAGE_FROM_TO_MISORDER);
        }

        try {
            model.setInternship(toEdit, editedInternship);
            model.setInternshipToDisplay();
            model.commitResumeBook();
        } catch (DuplicateItemException e) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        return new EditCommandResult(editedInternship.toString(),
                String.format(MESSAGE_EDIT_INTERNSHIP_SUCCESS, editedInternship),
                model.getDisplayType());
    }

    /**
     * Creates the edited Internship item from the internship to be edited and the descriptor.
     * @param toEdit Internship item to be edited
     * @param editInternshipDescriptor Descriptor parsed from input of user
     * @return Edited Internship item.
     */
    public static Internship createEditedInternship(
            Internship toEdit, EditInternshipDescriptor editInternshipDescriptor) {
        Name updatedName = editInternshipDescriptor.getName().orElse(toEdit.getName());
        Time updatedFrom = editInternshipDescriptor.getFrom().orElse(toEdit.getFrom());
        Time updatedTo = editInternshipDescriptor.getTo().orElse(toEdit.getTo());
        String updatedDesc = editInternshipDescriptor.getDescription().orElse(toEdit.getDescription());
        String updatedRole = editInternshipDescriptor.getRole().orElse(toEdit.getRole());
        Set<Tag> updatedTags = editInternshipDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new Internship(updatedName, updatedRole, updatedFrom, updatedTo, updatedDesc, updatedTags, id);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipCommand)) {
            return false;
        }

        // state check
        EditInternshipCommand e = (EditInternshipCommand) other;
        return index.equals(e.index)
            && editInternshipDescriptor.equals(e.editInternshipDescriptor);
    }
}
