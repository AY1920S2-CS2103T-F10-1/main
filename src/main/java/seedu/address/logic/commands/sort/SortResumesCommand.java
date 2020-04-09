package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Sorts Resume items in the resume book.
 */
public class SortResumesCommand extends SortCommand {
    private final Comparator<Resume> sortComparator;
    public SortResumesCommand(String sortOrder, boolean reverse) {
        Comparator<Resume> baseComparator = (res1, res2) -> res1.getName().compareTo(res2.getName());
        sortComparator = reverse ? baseComparator.reversed() : baseComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortResumes(sortComparator);
        model.setResumeToDisplay();
        model.commitResumeBook();

        return new SortCommandResult(String.format(MESSAGE_SUCCESS, "Resume"), model.getDisplayType());
    }
}
