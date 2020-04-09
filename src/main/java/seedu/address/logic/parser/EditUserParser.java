package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;

import java.io.File;
import java.util.stream.Stream;

import seedu.address.logic.commands.edit.EditUserCommand;
import seedu.address.logic.commands.edit.EditUserDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.field.DisplayPicture;

/**
 * Parser for EditUserCommand.
 */
public class EditUserParser implements Parser<EditUserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditUserCommand
     * and returns an EditUserCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditUserCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DP, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_GITHUB, PREFIX_UNIVERSITY, PREFIX_MAJOR, PREFIX_FROM, PREFIX_TO, PREFIX_CAP);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserCommand.MESSAGE_USAGE));
        }

        if (args.split(" ").length == 1) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Me Command has no value!!")));
        }

        EditUserDescriptor editUserDescriptor = new EditUserDescriptor();

        if (argMultimap.getValue(PREFIX_DP).isPresent()) {
            String dpPath = argMultimap.getValue(PREFIX_DP).get();
            DisplayPicture displayProfile = new DisplayPicture(dpPath);
            if (isValidDisplayPicturePath(dpPath)) {
                editUserDescriptor.setDisplayPicture(displayProfile);
            } else {
                editUserDescriptor.setDisplayPicture(null);
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DisplayPicture.MESSAGE_CONSTRAINTS));
            }
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editUserDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editUserDescriptor.setDescription(ParserUtil.parseDescription(argMultimap.getValue(
                    PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editUserDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editUserDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_GITHUB).isPresent()) {
            editUserDescriptor.setGithub(ParserUtil.parseGithub(argMultimap.getValue(PREFIX_GITHUB).get()));
        }
        if (argMultimap.getValue(PREFIX_UNIVERSITY).isPresent()) {
            editUserDescriptor.setUni(ParserUtil.parseUniversity(argMultimap.getValue(PREFIX_UNIVERSITY).get()));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            editUserDescriptor.setMajor(ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get()));
        }
        if (argMultimap.getValue(PREFIX_FROM).isPresent()) {
            editUserDescriptor.setFrom(ParserUtil.parseTime(argMultimap.getValue(PREFIX_FROM).get()));
        }
        if (argMultimap.getValue(PREFIX_TO).isPresent()) {
            editUserDescriptor.setTo(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TO).get()));
        }
        if (argMultimap.getValue(PREFIX_CAP).isPresent()) {
            editUserDescriptor.setCap(ParserUtil.parseCap(argMultimap.getValue(PREFIX_CAP).get()));
        }
        return new EditUserCommand(editUserDescriptor);
    }

    private static boolean isValidDisplayPicturePath(String dpPath) {
        File file = new File(dpPath);
        return file.exists();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
