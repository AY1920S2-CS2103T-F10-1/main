package seedu.address.logic.commands.sort;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Note;

/**
 * Sorts Note items in the resume book.
 */
public class SortNotesCommand extends SortCommand {

    private final Comparator<Note> sortComparator;
    public SortNotesCommand(String sortOrder, boolean reverse) {
        Comparator<Note> baseComparator = (note1, note2) -> {
            if (sortOrder.equalsIgnoreCase("name")) {
                return note1.getName().compareTo(note2.getName());
            } else {
                return note1.getTime().compareTo(note2.getTime());
            }
        };
        sortComparator = reverse ? baseComparator.reversed() : baseComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortNotes(sortComparator);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_ITEMS);
        model.commitResumeBook();

        return new SortCommandResult(String.format(MESSAGE_SUCCESS, "Note"), model.getDisplayType());
    }
}
