package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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

        if (targetIndex.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Internship internshipToDelete = model.getInternship(targetIndex);

        model.deleteInternship(internshipToDelete);

        return new CommandResult(internshipToDelete.toString(),
                String.format(MESSAGE_DELETE_PERSON_SUCCESS, internshipToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInternshipCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInternshipCommand) other).targetIndex)); // state check
    }
}
