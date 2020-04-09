package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Project;

/**
 * Sorts Project items in the resume book.
 */
public class SortProjectsCommand extends SortCommand {
    private final Comparator<Project> sortComparator;

    public SortProjectsCommand(String sortOrder, boolean reverse) {
        Comparator<Project> baseComparator = (proj1, proj2) -> {
            if (sortOrder.equalsIgnoreCase("name")) {
                return proj1.getName().compareTo(proj2.getName());
            } else {
                return proj1.getTime().compareTo(proj2.getTime());
            }
        };
        sortComparator = reverse ? baseComparator.reversed() : baseComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortProjects(sortComparator);
        model.setProjectToDisplay();
        model.commitResumeBook();

        return new SortCommandResult(String.format(MESSAGE_SUCCESS, "Resume"), model.getDisplayType());
    }
}
