package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ResumeEditCommand;
import seedu.address.logic.commands.ResumePreviewCommand;
import seedu.address.logic.commands.TagPullCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditUserCommand;
import seedu.address.logic.commands.find.FindCommand;
import seedu.address.logic.commands.generate.GenerateResumeCommand;
import seedu.address.logic.commands.help.HelpCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.sort.SortCommand;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ResumeBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        //--------------Item manipulation commands-------------
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case GenerateResumeCommand.COMMAND_WORD:
            return new GenerateResumeCommandParser().parse(arguments);


        //-----------------Undo/Redo----------------------------

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();


        //-----------------Resume Editing-----------------------

        case ResumeEditCommand.COMMAND_WORD:
            return new ResumeEditCommandParser().parse(arguments);

        case TagPullCommand.COMMAND_WORD:
            return new TagPullCommandParser().parse(arguments);

        //-----------------Other commands-----------------------

        case ResumePreviewCommand.COMMAND_WORD:
            return new ResumePreviewCommandParser().parse(arguments);

        case EditUserCommand.COMMAND_WORD: //"me"
            return new EditUserParser().parse(arguments);

        //-----------------Other commands-----------------------

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments.trim());

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
