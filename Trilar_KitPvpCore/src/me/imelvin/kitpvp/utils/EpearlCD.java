package me.imelvin.kitpvp.utils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.shizleshizle.core.Main;

// ENDER PEARL COOLDOWN
public class EpearlCD implements Listener {

	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		Player p = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getItemInHand().getType() == Material.ENDER_PEARL) {
				if (cooldown.containsKey(p.getUniqueId()) && cooldown.get(p.getUniqueId()) > System.currentTimeMillis()) {
					event.setCancelled(true);
					p.updateInventory();
					long remainingTime = cooldown.get(p.getUniqueId()) - System.currentTimeMillis();
					p.sendMessage(Main.prefix + "You cannot enderpearl for another " + remainingTime / 1000 + " seconds");
				} else {
					cooldown.put(p.getUniqueId(), System.currentTimeMillis() + (10 * 1000));
				}
			}
		}
	}

}
