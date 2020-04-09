package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.generate.GenerateResumeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.field.Name;

/**
 * Parses input arguments and creates a new GenerateResumeCommand object
 */
public class GenerateResumeCommandParser implements Parser<GenerateResumeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GenerateResumeCommand
     * and returns a GenerateResumeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GenerateResumeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenerateResumeCommand.MESSAGE_USAGE));
        }
        String preamble = argMultimap.getPreamble();
        Index index = ParserUtil.parseIndex(preamble);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Name outputName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            return new GenerateResumeCommand(index, outputName);
        }

        return new GenerateResumeCommand(index);
    }
}
