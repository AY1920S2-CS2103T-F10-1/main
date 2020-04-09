package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ListCommandResult;
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
        return new ListCommandResult("",
                String.format(MESSAGE_SUCCESS, "Resume"),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListResumeCommand;
    }
}
