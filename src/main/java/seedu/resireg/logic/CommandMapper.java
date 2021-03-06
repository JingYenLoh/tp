package seedu.resireg.logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.resireg.logic.commands.AddCommand;
import seedu.resireg.logic.commands.AllocateCommand;
import seedu.resireg.logic.commands.ClearCommand;
import seedu.resireg.logic.commands.Command;
import seedu.resireg.logic.commands.DeallocateCommand;
import seedu.resireg.logic.commands.DeleteCommand;
import seedu.resireg.logic.commands.EditCommand;
import seedu.resireg.logic.commands.ExitCommand;
import seedu.resireg.logic.commands.FindCommand;
import seedu.resireg.logic.commands.Help;
import seedu.resireg.logic.commands.HelpCommand;
import seedu.resireg.logic.commands.ListCommand;
import seedu.resireg.logic.commands.ListRoomCommand;
import seedu.resireg.logic.commands.ReallocateCommand;
import seedu.resireg.logic.parser.AddCommandParser;
import seedu.resireg.logic.parser.AddressBookParser;
import seedu.resireg.logic.parser.AllocateCommandParser;
import seedu.resireg.logic.parser.DeallocateCommandParser;
import seedu.resireg.logic.parser.DeleteCommandParser;
import seedu.resireg.logic.parser.EditCommandParser;
import seedu.resireg.logic.parser.FindCommandParser;
import seedu.resireg.logic.parser.ListRoomCommandParser;
import seedu.resireg.logic.parser.Parser;
import seedu.resireg.logic.parser.ReallocateCommandParser;

/**
 * Manages the mapping between command words and parsers, and command words and their help messages.
 *
 * Developers can add new commands by modifying the constructor for this class. This class ensures that all
 * commands (1) are bound to their parsing logic and (2) their documentation can be checked using the help command.
 */
public class CommandMapper {

    private CommandMap commandMap;
    private AddressBookParser parser;

    /**
     * Creates a new CommandMapper with all the commands supported by the application bound appropriately.
     * Developers who want to add new commands need to modify this constructor.
     */
    public CommandMapper() {
        commandMap = new CommandMap();
        // note: new Parser()::parse assumes that Parser does not depend on state
        commandMap.addCommand(AddCommand.COMMAND_WORD, AddCommand.HELP, new AddCommandParser()::parse);
        commandMap.addCommand(ClearCommand.COMMAND_WORD, ClearCommand.HELP, unused -> new ClearCommand());
        commandMap.addCommand(DeleteCommand.COMMAND_WORD, DeleteCommand.HELP, new DeleteCommandParser()::parse);
        commandMap.addCommand(EditCommand.COMMAND_WORD, EditCommand.HELP, new EditCommandParser()::parse);
        commandMap.addCommand(ExitCommand.COMMAND_WORD, ExitCommand.HELP, unused -> new ExitCommand());
        commandMap.addCommand(FindCommand.COMMAND_WORD, FindCommand.HELP, new FindCommandParser()::parse);
        commandMap.addCommand(HelpCommand.COMMAND_WORD, HelpCommand.HELP, HelpCommand::new);
        commandMap.addCommand(ListCommand.COMMAND_WORD, ListCommand.HELP, unused -> new ListCommand());
        commandMap.addCommand(ListRoomCommand.COMMAND_WORD, ListRoomCommand.HELP, new ListRoomCommandParser()::parse);
        commandMap.addCommand(AllocateCommand.COMMAND_WORD, AllocateCommand.HELP, new AllocateCommandParser()::parse);
        commandMap.addCommand(DeallocateCommand.COMMAND_WORD, DeallocateCommand.HELP,
                new DeallocateCommandParser()::parse);
        commandMap.addCommand(ReallocateCommand.COMMAND_WORD, ReallocateCommand.HELP,
                new ReallocateCommandParser()::parse);

        parser = new AddressBookParser(commandMap.getCommandWordToParserMap());
    }

    public Map<String, Help> getCommandWordToHelpMap() {
        return commandMap.getCommandWordToHelpMap();
    }

    /**
     * Returns a parser which parses user inputs and returns the appropriate Command.
     *
     * @return Parser.
     */
    AddressBookParser getParser() {
        return parser;
    }

    private static class CommandMap {
        private HashMap<String, Help> commandWordToHelp;
        private HashMap<String, Parser<Command>> commandWordToParser;

        private CommandMap() {
            commandWordToHelp = new HashMap<>();
            commandWordToParser = new HashMap<>();
        }

        void addCommand(String commandWord, Help help, Parser<Command> parser) {
            commandWordToHelp.put(commandWord, help);
            commandWordToParser.put(commandWord, parser);
        }

        Map<String, Help> getCommandWordToHelpMap() {
            return Collections.unmodifiableMap(commandWordToHelp);
        }

        Map<String, Parser<Command>> getCommandWordToParserMap() {
            return Collections.unmodifiableMap(commandWordToParser);
        }
    }
}



