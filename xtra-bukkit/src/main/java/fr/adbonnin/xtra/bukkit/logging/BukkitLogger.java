package fr.adbonnin.xtra.bukkit.logging;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class BukkitLogger {

    private static final Level LEVEL_DEBUG = Level.FINE;

    private static final Level LEVEL_INFO = Level.INFO;

    private static final Level LEVEL_WARNING = Level.WARNING;

    private static final Level LEVEL_ERROR = Level.SEVERE;

    private final JavaPlugin javaPlugin;

    private final String sourceClass;

    public BukkitLogger(JavaPlugin javaPlugin, Class<?> clazz) {
        this(javaPlugin, clazz.getName());
    }

    public BukkitLogger(JavaPlugin javaPlugin, String sourceClass) {
        this.javaPlugin = requireNonNull(javaPlugin);
        this.sourceClass = sourceClass;
    }

    public Logger getLogger() {
        return javaPlugin.getLogger();
    }

    public void debug(String message) {
        getLogger().logp(LEVEL_DEBUG, sourceClass, null, message);
    }

    public void debug(String message, Throwable cause) {
        getLogger().logp(LEVEL_DEBUG, sourceClass, null, message, cause);
    }

    public void info(String message) {
        getLogger().logp(LEVEL_INFO, sourceClass, null, message);
    }

    public void info(String message, Throwable cause) {
        getLogger().logp(LEVEL_INFO, sourceClass, null, message, cause);
    }

    public void warn(String message) {
        getLogger().logp(LEVEL_WARNING, sourceClass, null, message);
    }

    public void warn(String message, Throwable cause) {
        getLogger().logp(LEVEL_WARNING, sourceClass, null, message, cause);
    }

    public void error(String message) {
        getLogger().logp(LEVEL_ERROR, sourceClass, null, message);
    }

    public void error(String message, Throwable cause) {
        getLogger().logp(LEVEL_ERROR, sourceClass, null, message, cause);
    }

    public boolean isDebugEnabled() {
        return getLogger().isLoggable(LEVEL_DEBUG);
    }

    public boolean isInfoEnabled() {
        return getLogger().isLoggable(LEVEL_INFO);
    }

    public boolean isWarnEnabled() {
        return getLogger().isLoggable(LEVEL_WARNING);
    }

    public boolean isErrorEnabled() {
        return getLogger().isLoggable(LEVEL_ERROR);
    }

    @Override
    public String toString() {
        return sourceClass;
    }
}
