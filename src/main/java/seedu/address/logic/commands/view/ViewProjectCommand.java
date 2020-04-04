package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Project;

/**
 * The command for viewing the details of a Project item.
 */
public class ViewProjectCommand extends ViewCommand {

    public ViewProjectCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getProjectSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Project toView = model.getProjectByIndex(targetIndex);
        model.setProjectToDisplay();

        return new CommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewProjectCommand // instanceof handles nulls
                && targetIndex.equals(((ViewProjectCommand) other).targetIndex)); // state check
    }
}
