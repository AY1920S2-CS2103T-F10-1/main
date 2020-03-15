package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListInternshipCommand;
import seedu.address.logic.commands.ListResumeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;

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
            throw new ParseException(Item.MESSAGE_CONSTRAINTS);
        }

        String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());

        switch(itemType) {
        case ("res"):
            return new ListResumeCommand();
        case ("int"):
            return new ListInternshipCommand();
        default:
            // Should not have reached here
            // TODO: Use a better Exception here
            throw new ParseException("The item type is not detected! Something is wrong");
        }
    }
}
