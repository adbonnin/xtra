package fr.adbonnin.xtra.bukkit.party;

import fr.adbonnin.xtra.collect.XtraIterators;
import fr.adbonnin.xtra.predicate.Predicate;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class PartyManager<Pa extends Party<?>> {

    private final Map<String, Pa> partiesByName = new HashMap<>();

    public Pa findPartyByName(String name) {
        return partiesByName.get(name);
    }

    public Iterator<Pa> findPartiesByPlayer(Player player) {
        return player == null ? Collections.<Pa>emptyIterator() : findPartiesByPlayer(player.getUniqueId());
    }

    public Iterator<Pa> findPartiesByPlayer(final UUID uuid) {
        return XtraIterators.filter(partiesByName.values().iterator(), new Predicate<Pa>() {
            @Override
            public boolean evaluate(Pa value) {
                return value.containsPlayer(uuid);
            }
        });
    }

    public boolean addParty(Pa party) {

        if (party == null) {
            return false;
        }

        final String name = party.getName();
        if (partiesByName.containsKey(name)) {
            return false;
        }

        partiesByName.put(name, party);
        return true;
    }

    public boolean addPlayerToParty(String party, Player player, boolean multiParty) {
        return false;
    }

    private enum PartyStatePredicate implements Predicate<Party> {
        IN_GAME_PARTY(Party.State.IN_GAME);

        private final Party.State state;

        PartyStatePredicate(Party.State state) {
            this.state = state;
        }

        @Override
        public boolean evaluate(Party party) {
            return party.getState() == state;
        }
    }
}
