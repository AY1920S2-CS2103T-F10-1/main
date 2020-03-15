package seedu.address.logic.commands.stubs;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * A stub class for AddResumeCommand.
 */
public class AddResumeCommandStub extends AddCommand {
    private Resume resume;

    public AddResumeCommandStub(Resume resume) {
        this.resume = resume;
    }

    public Resume getResume() {
        return resume;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
