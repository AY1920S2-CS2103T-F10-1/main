package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Command to list all Skills.
 */
public class ListSkillCommand extends ListCommand {
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSkillToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult("", String.format(MESSAGE_SUCCESS, "Skill"), model.getDisplayType());
    }
}
