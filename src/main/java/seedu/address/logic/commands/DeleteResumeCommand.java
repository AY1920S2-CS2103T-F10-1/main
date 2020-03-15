package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Deletes a Resume item.
 */
public class DeleteResumeCommand extends DeleteCommand {

    public DeleteResumeCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Resume resumeToDelete = model.getResume(targetIndex);

        model.deleteResume(resumeToDelete);
        model.setResumeToDisplay();

        return new CommandResult(resumeToDelete.toString(),
                String.format(MESSAGE_DELETE_PERSON_SUCCESS, resumeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteResumeCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteResumeCommand) other).targetIndex)); // state check
    }
}
