package seedu.address.logic.commands.view;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Skill;

/**
 * The command for viewing the details of a Skill item.
 */
public class ViewSkillCommand extends ViewCommand {

    public ViewSkillCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getSkillSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Skill toView = model.getSkillByIndex(targetIndex);
        model.setSkillToDisplay();

        return new CommandResult(toView.toString(),
                String.format(MESSAGE_VIEW_SUCCESS, toView), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewSkillCommand // instanceof handles nulls
                && targetIndex.equals(((ViewSkillCommand) other).targetIndex)); // state check
    }
}
