package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Views the details of a Resume item.
 */
public class ViewResumeCommand extends ViewCommand {

    public ViewResumeCommand(Index targetIndex) {
        super(targetIndex);
    }

    /**
     * Views resume at {@code targetIndex}.
     *
     * @param model {@code Model} where resume will be viewed.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws      CommandException if {@code targetIndex} is out of bounds.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Resume toView = model.getResumeByIndex(targetIndex);
        model.setResumeToDisplay();

        return new ViewCommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewResumeCommand // instanceof handles nulls
                && targetIndex.equals(((ViewResumeCommand) other).targetIndex)); // state check
    }
}
