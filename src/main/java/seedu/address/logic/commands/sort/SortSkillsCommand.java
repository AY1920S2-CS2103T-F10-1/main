package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Skill;

/**
 * Sorts Skill items in the resume book.
 */
public class SortSkillsCommand extends SortCommand {
    private final Comparator<Skill> sortComparator;

    public SortSkillsCommand(String sortOrder, boolean reverse) {
        Comparator<Skill> baseComparator = (ski1, ski2) -> {
            if (sortOrder.equals("name")) {
                return ski1.getName().compareTo(ski2.getName());
            } else {
                return ski1.getLevel().compareTo(ski2.getLevel());
            }
        };
        sortComparator = reverse ? baseComparator.reversed() : baseComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortSkills(sortComparator);
        model.setSkillToDisplay();
        model.commitResumeBook();

        return new SortCommandResult(String.format(MESSAGE_SUCCESS, "Internship"), model.getDisplayType());
    }
}
