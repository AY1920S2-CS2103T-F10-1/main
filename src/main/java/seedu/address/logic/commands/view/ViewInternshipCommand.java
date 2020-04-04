package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;

/**
 * The command for viewing the details of an Internship item.
 */
public class ViewInternshipCommand extends ViewCommand {

    public ViewInternshipCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getInternshipSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Internship toView = model.getInternshipByIndex(targetIndex);
        model.setInternshipToDisplay();

        return new CommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewInternshipCommand // instanceof handles nulls
                && targetIndex.equals(((ViewInternshipCommand) other).targetIndex)); // state check
    }
}
