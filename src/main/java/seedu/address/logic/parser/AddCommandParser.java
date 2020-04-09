package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEBSITE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.add.AddInternshipCommand;
import seedu.address.logic.commands.add.AddNoteCommand;
import seedu.address.logic.commands.add.AddProjectCommand;
import seedu.address.logic.commands.add.AddResumeCommand;
import seedu.address.logic.commands.add.AddSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Note;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

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
                        PREFIX_ROLE, PREFIX_DESCRIPTION, PREFIX_WEBSITE, PREFIX_LEVEL);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(Item.MESSAGE_CONSTRAINTS);
        }

        String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());

        Name name;
        Set<Tag> tagList;
        String description;

        switch (itemType) {
        case (ItemUtil.RESUME_ALIAS):
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddResumeCommand.MESSAGE_USAGE));
            }

            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Resume resume = new Resume(name, tagList);
            return new AddResumeCommand(resume);

        case (ItemUtil.INTERNSHIP_ALIAS):
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_FROM, PREFIX_TO, PREFIX_ROLE, PREFIX_DESCRIPTION)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddInternshipCommand.MESSAGE_USAGE));
            }
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Time from = ParserUtil.parseTime(argMultimap.getValue(PREFIX_FROM).get());
            Time to = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TO).get());

            if (from.compareTo(to) > 0) {
                throw new ParseException(AddInternshipCommand.MESSAGE_FROM_TO_MISORDER);
            }

            String role = argMultimap.getValue(PREFIX_ROLE).get().trim();
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            // TODO: handle this toString() thingy better
            Internship internship = new Internship(name, role, from, to, description, tagList);
            return new AddInternshipCommand(internship);

        case (ItemUtil.PROJECT_ALIAS):
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TIME, PREFIX_WEBSITE, PREFIX_DESCRIPTION)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddProjectCommand.MESSAGE_USAGE));
            }
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
            Website website = ParserUtil.parseWebsite(argMultimap.getValue(PREFIX_WEBSITE).get());
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Project project = new Project(name, time, website, description, tagList);
            return new AddProjectCommand(project);

        case(ItemUtil.SKILL_ALIAS):
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LEVEL) || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddSkillCommand.MESSAGE_USAGE));
            }
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Level level = ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get());
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Skill skill = new Skill(name, level, tagList);
            return new AddSkillCommand(skill);

        case(ItemUtil.NOTE_ALIAS):
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddNoteCommand.MESSAGE_USAGE));
            }

            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TIME)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddNoteCommand.MESSAGE_USAGE));
            }

            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Time noteTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Note note = new Note(name, noteTime, tagList);
            return new AddNoteCommand(note);

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
