package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for done command.
 */
public class DoneCommandParser implements Parser<DoneCommand> {

    /**
     * Parse argument to return a DoneCommand.
     * @param args
     * @return
     * @throws ParseException
     */
    public DoneCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(args);

        return new DoneCommand(index);
    }
}
