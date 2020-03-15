package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.model.Model;

/**
 * Command to list all Resumes.
 */
public class ListResumeCommand extends ListCommand {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setResumeToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult("", String.format(MESSAGE_SUCCESS, "Resume"));
    }

}
