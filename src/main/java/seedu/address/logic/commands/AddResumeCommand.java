package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Adds a Resume Item to the address book.
 */
public class AddResumeCommand extends AddCommand {

    private final Resume toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddResumeCommand(Resume item) {
        requireNonNull(item);
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasResume(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addResume(toAdd);
        model.setResumeToDisplay();

        return new CommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddResumeCommand // instanceof handles nulls
                && toAdd.equals(((AddResumeCommand) other).toAdd));
    }
}
