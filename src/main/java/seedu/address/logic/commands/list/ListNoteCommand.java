package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;

/**
 * List all notes currently in the list.
 */
public class ListNoteCommand extends ListCommand {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult("", String.format(MESSAGE_SUCCESS, "Note"), model.getDisplayType());
    }
}
