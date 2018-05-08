package fr.adbonnin.xtra.jcommander;

import com.beust.jcommander.JCommander;

public class XtraJCommander {

    public static <T> T parseCommand(JCommander jc, T emptyCommand, String... args) {
        jc.parse(args);

        final String parsedAlias = jc.getParsedAlias();
        final JCommander parsedCommand = jc.getCommands().get(parsedAlias);
        return parsedCommand == null ? emptyCommand : (T) parsedCommand.getObjects().get(0);
    }

    public static <T> T parseCommand(JCommander jc, String... args) {
        return parseCommand(jc, null, args);
    }
}
