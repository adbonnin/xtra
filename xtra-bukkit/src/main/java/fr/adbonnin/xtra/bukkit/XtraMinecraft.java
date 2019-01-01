package fr.adbonnin.xtra.bukkit;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public final class XtraMinecraft {

    public static final int CHUNK_SIZE = 16;

    public static boolean isChangedIntoFallingBlock(EntityChangeBlockEvent event) {
        return event.getEntityType() == EntityType.FALLING_BLOCK &&
            event.getTo() == Material.AIR;
    }

    private XtraMinecraft() { /* Cannot be instantiated*/ }
}
