package seedu.address.logic.commands.view;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;

/**
 * Views the details of an item in the ResuMe application.
 */
public abstract class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_VIEW_SUCCESS = "Viewing this item!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " INDEX i/ TYPE\n"
            + "Example: view 1 i/int";

    protected final Index targetIndex;

    // This default constructor is needed for the stub
    public ViewCommand() {
        this.targetIndex = null;
    }

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

}
