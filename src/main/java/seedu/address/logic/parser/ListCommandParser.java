package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
            throw new ParseException("You are required to specify Item type!");
        }

        String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());

        return new ListCommand(itemType);
    }
}
