package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;

import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListInternshipCommand;
import seedu.address.logic.commands.list.ListNoteCommand;
import seedu.address.logic.commands.list.ListProjectCommand;
import seedu.address.logic.commands.list.ListResumeCommand;
import seedu.address.logic.commands.list.ListSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.ItemUtil;


/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ITEM);

        if (!argMultimap.getValue(PREFIX_ITEM).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());

        switch(itemType) {

        case ItemUtil.RESUME_ALIAS:
            return new ListResumeCommand();
        case ItemUtil.INTERNSHIP_ALIAS:
            return new ListInternshipCommand();
        case ItemUtil.PROJECT_ALIAS:
            return new ListProjectCommand();
        case ItemUtil.SKILL_ALIAS:
            return new ListSkillCommand();
        case ItemUtil.NOTE_ALIAS:
            return new ListNoteCommand();
        default:
            // Should not have reached here
            // TODO: Use a better Exception here
            throw new ParseException("The item type is not detected! Something is wrong");
        }
    }
}
