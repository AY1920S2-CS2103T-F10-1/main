package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;

/**
 * Edits the details of an existing item in the address book.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    // TODO: Refactor this into a nice message centre
    public static final String MESSAGE_DUPLICATE_ITEM = "Item with the same name already exists in the resume book.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the item identified "
            + "by the index number used in the displayed item list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + PREFIX_ITEM + " TYPE "
            + "[ PREFIX/ OTHER PREFIX DATA]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ITEM + " int "
            + PREFIX_NAME + " Google "
            + PREFIX_FROM + " 03-2019 "
            + PREFIX_TO + " 06-2019 "
            + PREFIX_ROLE + " Intern "
            + PREFIX_DESCRIPTION + " Do things and get paid. "
            + PREFIX_TAG + " frontend ";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    protected final Index index;

    /**
     * @param index of the item in the filtered item list to edit
     */
    public EditCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index);
    }
}
