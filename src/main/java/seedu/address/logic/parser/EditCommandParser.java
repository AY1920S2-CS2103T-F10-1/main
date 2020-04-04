package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditInternshipCommand;
import seedu.address.logic.commands.edit.EditInternshipDescriptor;
import seedu.address.logic.commands.edit.EditProjectCommand;
import seedu.address.logic.commands.edit.EditProjectDescriptor;
import seedu.address.logic.commands.edit.EditResumeCommand;
import seedu.address.logic.commands.edit.EditResumeDescriptor;
import seedu.address.logic.commands.edit.EditSkillCommand;
import seedu.address.logic.commands.edit.EditSkillDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_ITEM, PREFIX_FROM, PREFIX_TO,
                        PREFIX_ROLE, PREFIX_DESCRIPTION, PREFIX_WEBSITE, PREFIX_LEVEL);

        Index index;

        // TODO: Better error handling
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_ITEM).isPresent()) {
            throw new ParseException(Item.MESSAGE_CONSTRAINTS);
        }

        String itemType = ParserUtil.parseItemType(argMultimap.getValue(PREFIX_ITEM).get());

        switch (itemType) {
        case ItemUtil.RESUME_ALIAS:
            EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
            // TODO: Not sure if I should create a method inside the respective descriptors for this checking.
            // Considerations: If I add a method inside the descriptor, then potentially need dependencies
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editResumeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editResumeDescriptor::setTags);

            if (!editResumeDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditResumeCommand.MESSAGE_NOT_EDITED);
            }

            return new EditResumeCommand(index, editResumeDescriptor);
        case ItemUtil.INTERNSHIP_ALIAS:
            EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editInternshipDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
                editInternshipDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get().trim()));
            }
            if (argMultimap.getValue(PREFIX_FROM).isPresent()) {
                editInternshipDescriptor.setFrom(ParserUtil.parseTime(argMultimap.getValue(PREFIX_FROM).get().trim()));
            }
            if (argMultimap.getValue(PREFIX_TO).isPresent()) {
                editInternshipDescriptor.setTo(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TO).get().trim()));
            }
            if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                editInternshipDescriptor.setDescription(ParserUtil.parseDescription(
                        argMultimap.getValue(PREFIX_DESCRIPTION).get().trim()));
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editInternshipDescriptor::setTags);

            if (!editInternshipDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditInternshipCommand.MESSAGE_NOT_EDITED);
            }

            return new EditInternshipCommand(index, editInternshipDescriptor);
        case ItemUtil.PROJECT_ALIAS:
            EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editProjectDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                editProjectDescriptor.setDescription(
                        ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get().trim()));
            }
            if (argMultimap.getValue(PREFIX_WEBSITE).isPresent()) {
                editProjectDescriptor.setWebsite(ParserUtil
                        .parseWebsite(argMultimap.getValue(PREFIX_WEBSITE).get().trim()));
            }
            if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
                editProjectDescriptor.setTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get().trim()));
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editProjectDescriptor::setTags);

            if (!editProjectDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditProjectCommand.MESSAGE_NOT_EDITED);
            }

            return new EditProjectCommand(index, editProjectDescriptor);
        case ItemUtil.SKILL_ALIAS:
            EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editSkillDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            if (argMultimap.getValue(PREFIX_LEVEL).isPresent()) {
                editSkillDescriptor.setLevel(ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get()));
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editSkillDescriptor::setTags);

            if (!editSkillDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditSkillCommand.MESSAGE_NOT_EDITED);
            }

            return new EditSkillCommand(index, editSkillDescriptor);
        default:
            // Should not have reached here
            // TODO: Use a better Exception here
            throw new ParseException("The item type is not detected! Something is wrong");
        }

    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
