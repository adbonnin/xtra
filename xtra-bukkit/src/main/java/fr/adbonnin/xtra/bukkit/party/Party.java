package fr.adbonnin.xtra.bukkit.party;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static fr.adbonnin.xtra.base.XtraObjects.requireNotEmpty;
import static java.util.Objects.requireNonNull;

public class Party<P> {

    private final Map<UUID, P> players = new HashMap<>();

    private String name;

    private State state = State.WAITING_FOR_PLAYERS;

    private Integer minPlayers;

    private Integer maxPlayers;

    public boolean containsPlayer(Player player) {
        return player != null && containsPlayer(player.getUniqueId());
    }

    public boolean containsPlayer(UUID uuid) {
        return players.containsKey(uuid);
    }

    public Party(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireNotEmpty(name, "'name' must not be empty");
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = requireNonNull(state, "'state' must not be empty");
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public enum State {
        WAITING_FOR_PLAYERS,
        WAITING_LOBBY,
        IN_GAME,
        IN_PAUSE,
        GAME_OVER
    }
}
