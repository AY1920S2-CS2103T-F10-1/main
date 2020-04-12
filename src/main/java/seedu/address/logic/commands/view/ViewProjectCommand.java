package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Project;

/**
 * Views the details of a Project item.
 */
public class ViewProjectCommand extends ViewCommand {

    public ViewProjectCommand(Index targetIndex) {
        super(targetIndex);
    }

    /**
     * Views project at {@code targetIndex}.
     *
     * @param model {@code Model} where project will be viewed.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws      CommandException if {@code targetIndex} is out of bounds.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getProjectSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Project toView = model.getProjectByIndex(targetIndex);
        model.setProjectToDisplay();

        return new ViewCommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewProjectCommand // instanceof handles nulls
                && targetIndex.equals(((ViewProjectCommand) other).targetIndex)); // state check
    }
}
