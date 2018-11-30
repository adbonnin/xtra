package fr.adbonnin.xtra.bukkit.command;

import fr.adbonnin.xtra.base.XtraNumber;

import static java.util.Objects.requireNonNull;

public class CommandArgs {

    private final String[] args;

    public CommandArgs(String... args) {
        this.args = requireNonNull(args);
    }

    public String[] getArgs() {
        return args;
    }

    public boolean isOutOfBounds(int index) {
        return index >= args.length || index < 0;
    }

    public String getString(int index, String defaultValue) {
        return isOutOfBounds(index) ? defaultValue : args[index];
    }

    public Integer getInteger(int index, Integer defaultValue) {
        return isOutOfBounds(index) ? defaultValue : XtraNumber.toIntegerObject(args[index], defaultValue);
    }
}
