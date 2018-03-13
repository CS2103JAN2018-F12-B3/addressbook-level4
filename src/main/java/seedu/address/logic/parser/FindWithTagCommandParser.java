package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindWithTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindWithTagCommand object
 */
public class FindWithTagCommandParser implements Parser<FindWithTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindWithTagCommand
     * and returns an FindWithTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindWithTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindWithTagCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split("\\s+");

        return new FindWithTagCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }
}
