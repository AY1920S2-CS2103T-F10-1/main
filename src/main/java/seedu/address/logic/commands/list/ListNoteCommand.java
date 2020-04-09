package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ListCommandResult;
import seedu.address.model.Model;

/**
 * Lists all notes currently in the list.
 */
public class ListNoteCommand extends ListCommand {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_ITEMS);
        return new ListCommandResult("", String.format(MESSAGE_SUCCESS, "Note"), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListNoteCommand;
    }
}
