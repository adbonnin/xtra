package fr.adbonnin.xtra.bukkit.command

import spock.lang.Specification

class CommandArgsSpec extends Specification {

    void "should get args"() {
        given:
        def commandArgs = new CommandArgs(args)

        expect:
        commandArgs.getArgs() is args

        where:
        args = ["a", "b", "c"] as String[]
    }

    void "should check that index is out of bound"() {
        given:
        def commandArgs = new CommandArgs(args)

        expect:
        commandArgs.isOutOfBounds(index) == expectedOutOfBound

        where:
        index || expectedOutOfBound
        -1    || true
        1     || false
        2     || true

        args = ['a', 'b'] as String[]
    }

    void "should get String"() {
        given:
        def commandArgs = new CommandArgs(args)

        expect:
        commandArgs.getString(index, defaultValue) == expectedValue

        where:
        index | defaultValue || expectedValue
        -1    | 'default'    || 'default'
        0     | _ as String  || 'a'
        1     | 'default'    || 'default'

        args = ['a'] as String[]
    }

    void "should get Integer"() {
        given:
        def commandArgs = new CommandArgs(args)

        expect:
        commandArgs.getInteger(index, defaultValue) == expectedValue

        where:
        index | defaultValue || expectedValue
        -1    | 2            || 2
        0     | null         || 3
        1     | 4            || 4

        args = [' 3 '] as String[]
    }
}
