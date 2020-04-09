package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVERSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.stream.Stream;

import seedu.address.logic.commands.sort.SortCommand;
import seedu.address.logic.commands.sort.SortInternshipsCommand;
import seedu.address.logic.commands.sort.SortNotesCommand;
import seedu.address.logic.commands.sort.SortProjectsCommand;
import seedu.address.logic.commands.sort.SortResumesCommand;
import seedu.address.logic.commands.sort.SortSkillsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.util.ItemUtil;

/**
 * Parses input arguments and create a SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments and returns a SortCommand object for execution.
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ITEM, PREFIX_SORT_ORDER, PREFIX_REVERSE);
        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(Item.MESSAGE_CONSTRAINTS);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_SORT_ORDER)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());
        String sortOrder = argMultimap.getValue(PREFIX_SORT_ORDER).get();
        boolean reverse = ParserUtil.parseReverse(argMultimap.getValue(PREFIX_REVERSE).orElse(null));

        if (!sortOrder.equalsIgnoreCase("name")
            && !sortOrder.equalsIgnoreCase("time")
            && !sortOrder.equalsIgnoreCase("level")) {
            throw new ParseException(SortCommand.MESSAGE_SORT_NOT_EXISTED);
        }

        switch (itemType) {
        case ItemUtil.RESUME_ALIAS:
            if (!sortOrder.equalsIgnoreCase("name")) {
                throw new ParseException(SortCommand.MESSAGE_INAPPLICABLE_SORT);
            } else {
                return new SortResumesCommand(sortOrder, reverse);
            }

        case ItemUtil.INTERNSHIP_ALIAS:
            if (sortOrder.equalsIgnoreCase("level")) {
                throw new ParseException(SortCommand.MESSAGE_INAPPLICABLE_SORT);
            } else {
                return new SortInternshipsCommand(sortOrder, reverse);
            }

        case ItemUtil.PROJECT_ALIAS:
            if (sortOrder.equalsIgnoreCase("level")) {
                throw new ParseException(SortCommand.MESSAGE_INAPPLICABLE_SORT);
            } else {
                return new SortProjectsCommand(sortOrder, reverse);
            }

        case ItemUtil.SKILL_ALIAS:
            if (sortOrder.equalsIgnoreCase("time")) {
                throw new ParseException(SortCommand.MESSAGE_INAPPLICABLE_SORT);
            } else {
                return new SortSkillsCommand(sortOrder, reverse);
            }

        case ItemUtil.NOTE_ALIAS:
            if (sortOrder.equalsIgnoreCase("level")) {
                throw new ParseException(SortCommand.MESSAGE_INAPPLICABLE_SORT);
            } else {
                return new SortNotesCommand(sortOrder, reverse);
            }


        default:
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
