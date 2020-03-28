package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;

/**
 * Deletes an Internship item.
 */
public class DeleteInternshipCommand extends DeleteCommand {

    public DeleteInternshipCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getInternshipSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Internship toDelete = model.getInternship(targetIndex);

        model.deleteInternship(toDelete);
        model.setInternshipToDisplay();
        model.commitResumeBook();

        return new CommandResult(toDelete.toString(),
                String.format(MESSAGE_DELETE_ITEM_SUCCESS, toDelete.getType().getFullType()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInternshipCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInternshipCommand) other).targetIndex)); // state check
    }
}
