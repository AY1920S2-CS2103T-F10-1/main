package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ViewCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;

/**
 * Views the details of an Internship item.
 */
public class ViewInternshipCommand extends ViewCommand {

    public ViewInternshipCommand(Index targetIndex) {
        super(targetIndex);
    }

    /**
     * Views internship at {@code targetIndex}.
     *
     * @param model {@code Model} where internship will be viewed.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws      CommandException if {@code targetIndex} is out of bounds.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getInternshipSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Internship toView = model.getInternshipByIndex(targetIndex);
        model.setInternshipToDisplay();

        return new ViewCommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewInternshipCommand // instanceof handles nulls
                && targetIndex.equals(((ViewInternshipCommand) other).targetIndex)); // state check
    }
}
