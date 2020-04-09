package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.logic.commands.view.ViewInternshipCommand;
import seedu.address.logic.commands.view.ViewNoteCommand;
import seedu.address.logic.commands.view.ViewProjectCommand;
import seedu.address.logic.commands.view.ViewResumeCommand;
import seedu.address.logic.commands.view.ViewSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.util.ItemUtil;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        // The code is actually identical to DeleteCommand
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ITEM);

        if (argMultimap.getPreamble().isEmpty() && !argMultimap.getValue(PREFIX_ITEM).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        if (!argMultimap.getValue(PREFIX_ITEM).isPresent()) {
            throw new ParseException(Item.MESSAGE_CONSTRAINTS);
        }

        String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());

        switch(itemType) {
        case ItemUtil.RESUME_ALIAS:
            return new ViewResumeCommand(index);
        case ItemUtil.INTERNSHIP_ALIAS:
            return new ViewInternshipCommand(index);
        case ItemUtil.PROJECT_ALIAS:
            return new ViewProjectCommand(index);
        case ItemUtil.SKILL_ALIAS:
            return new ViewSkillCommand(index);
        case ItemUtil.NOTE_ALIAS:
            return new ViewNoteCommand(index);
        default:
            // Should not have reached here
            // TODO: Use a better Exception here
            throw new ParseException("The item type is not detected! Something is wrong");
        }
    }
}
