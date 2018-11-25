package fr.adbonnin.xtra.bukkit.logging

import org.bukkit.plugin.java.JavaPlugin
import spock.lang.Specification
import spock.lang.Subject

import java.util.logging.Level
import java.util.logging.Logger

class BukkitLoggerSpec extends Specification {

    def mockLogger = Mock(Logger)

    def mockJavaPlugin = Mock(JavaPlugin)

    @Subject
    def bukkitLogger = Spy(BukkitLogger, constructorArgs: [mockJavaPlugin, SourceClass]) {
        getLogger() >> mockLogger
    }

    def "should call logger method"() {
        when:
        bukkitLogger."$method"(message)

        then:
        1 * mockLogger.logp(expectedLevel, sourceClass, null, message)

        when:
        bukkitLogger."$method"(message, cause)

        then:
        1 * mockLogger.logp(expectedLevel, sourceClass, null, message, cause)

        where:
        method  || expectedLevel
        'debug' || Level.FINE
        'info'  || Level.INFO
        'warn'  || Level.WARNING
        'error' || Level.SEVERE

        sourceClass = SourceClass.name
        message = 'abc'
        cause = new Exception()
    }

    def "should call logger isLoggable"() {
        when:
        bukkitLogger."$method"()

        then:
        1 * mockLogger.isLoggable(expectedLevel)

        where:
        method           || expectedLevel
        'isDebugEnabled' || Level.FINE
        'isInfoEnabled'  || Level.INFO
        'isWarnEnabled'  || Level.WARNING
        'isErrorEnabled' || Level.SEVERE
    }

    static class SourceClass {}
}
