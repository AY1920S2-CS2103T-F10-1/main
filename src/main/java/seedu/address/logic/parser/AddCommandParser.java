package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddInternshipCommand;
import seedu.address.logic.commands.AddResumeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Resume;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_ITEM, PREFIX_FROM, PREFIX_TO,
                        PREFIX_ROLE, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(Item.MESSAGE_CONSTRAINTS);
        }

        String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());

        Name name;
        Set<Tag> tagList;
        String description;

        switch(itemType) {
        case ("res"):
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }

            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Resume resume = new Resume(name, tagList);

            return new AddResumeCommand(resume);
        case ("int"):
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_FROM, PREFIX_TO, PREFIX_ROLE, PREFIX_DESCRIPTION)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }

            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Time from = ParserUtil.parseTime(argMultimap.getValue(PREFIX_FROM).get());
            Time to = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TO).get());
            String role = argMultimap.getValue(PREFIX_ROLE).get().trim();
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Internship internship = new Internship(name, role, from.toString(), to.toString(), description, tagList);
            return new AddInternshipCommand(internship);
        default:
            // Should not have reached here
            // TODO: Use a better Exception here
            throw new ParseException("The item type is not detected! Something is wrong");
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
