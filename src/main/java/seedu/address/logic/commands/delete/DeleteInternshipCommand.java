package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.DeleteCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;

/**
 * Deletes an Internship item.
 */
public class DeleteInternshipCommand extends DeleteCommand {

    public DeleteInternshipCommand(Index targetIndex) {
        super(targetIndex);
    }

    /**
     * Deletes internship at {@code targetIndex}.
     *
     * @param model {@code Model} that the internship will be deleted from.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws      CommandException if {@code targetIndex} is out of bounds.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getInternshipSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Internship toDelete = model.getInternshipByIndex(targetIndex);

        model.deleteInternship(toDelete);
        model.setInternshipToDisplay();
        model.commitResumeBook();

        return new DeleteCommandResult(toDelete.toString(),
                String.format(MESSAGE_DELETE_ITEM_SUCCESS, toDelete.getType().getFullType()),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInternshipCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInternshipCommand) other).targetIndex)); // state check
    }
}
