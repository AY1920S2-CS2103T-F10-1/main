package seedu.address.logic.parser;

import seedu.address.logic.commands.help.HelpCommand;
import seedu.address.logic.commands.help.HelpStartCommand;
import seedu.address.logic.commands.help.HelpSummaryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        switch (args) {
        case "command":
            return new HelpSummaryCommand();
        case "start":
            return new HelpStartCommand();
        default:
            throw new ParseException(HelpCommand.MESSAGE_INVALID_OPTION);
        }
    }
}
