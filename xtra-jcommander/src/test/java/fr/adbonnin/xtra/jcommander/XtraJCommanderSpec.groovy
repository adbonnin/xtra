package fr.adbonnin.xtra.jcommander

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameters
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class XtraJCommanderSpec extends Specification {

    @Shared
    HelpCommand helpCommand = new HelpCommand()

    @Shared
    FooCommand fooCommmand = new FooCommand()

    @Shared
    BarCommand barCommand = new BarCommand()

    @Unroll
    def "should parse #labelCommand for #args"() {

        given:
        def jc = new JCommander()
        [fooCommmand, barCommand].each { jc.addCommand(it) }

        expect:
        XtraJCommander.parseCommand(jc, helpCommand, *args) == parsedCommand

        where:
        args            || parsedCommand
        ['foo-command'] || fooCommmand
        ['bar-cmd']     || barCommand
        []              || helpCommand

        labelCommand = parsedCommand.class.simpleName
        labelArgs = args.toString()
    }

    @Parameters(commandNames = ['help-command', 'hlp-cmd'])
    static class HelpCommand {

    }

    @Parameters(commandNames = ['foo-command', 'foo-cmd'])
    static class FooCommand {

    }

    @Parameters(commandNames = ['bar-command', 'bar-cmd'])
    static class BarCommand {

    }
}
