package me.drawlin.kitpvp.kit.event;

import lombok.Getter;
import me.drawlin.kitpvp.kit.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class KitUnequipEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private Kit kit;

    public KitUnequipEvent(Player player, Kit kit) {
        this.player = player;
        this.kit = kit;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
