package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;

/**
 * Adds a Internship Item to the address book.
 */
public class AddInternshipCommand extends AddCommand {
    private final Internship toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}.
     */
    public AddInternshipCommand(Internship item) {
        requireNonNull(item);
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addInternship(toAdd);
        model.setInternshipToDisplay();

        return new CommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInternshipCommand // instanceof handles nulls
                && toAdd.equals(((AddInternshipCommand) other).toAdd));
    }
}
