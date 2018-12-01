package fr.adbonnin.xtra.bukkit.party

import spock.lang.Specification

class PartySpec extends Specification {

    void "should set properties to party"() {
        given:
        def party = new Party('pt')

        when:
        party."$property" = value

        then:
        party."$property" == value

        where:
        property     | value
        'name'       | 'party'
        'state'      | Party.State.IN_GAME
        'minPlayers' | -1
        'maxPlayers' | null
    }

    void "should set state of party"() {
        given:
        def party = new Party('pt')

        when:
        party."$stateProperty" = state

        then:
        party."$stateProperty" == state

        where:
        stateProperty         | state
        'waitingForPlayers' | Party.State.WAITING_FOR_PLAYERS
        'waitingLobby'       | Party.State.WAITING_LOBBY
        'in_game'             | Party.State.IN_GAME
        'IN_PAUSE'            | Party.State.IN_PAUSE
        'GAME_OVER'           | Party.State.GAME_OVER
    }
}
