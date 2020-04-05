package me.imelvin.kitpvp.events;

import java.util.HashMap;
import java.util.UUID;

import me.shizleshizle.core.utils.Cooldowns;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.imelvin.kitpvp.Kitpvp;
import me.imelvin.kitpvp.utils.KitPvPGUI;
import me.shizleshizle.core.Main;

public class PInteract implements Listener {

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (e.getRightClicked().equals(Kitpvp.v)) {
			e.setCancelled(true);
			KitPvPGUI.kitsMenu(p);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND)) {
				e.setCancelled(true);
				KitPvPGUI.globalMenu(p);
			} else if (p.getItemInHand().getType() == Material.ENDER_PEARL) {
				if (Cooldowns.cooldown.containsKey(p.getUniqueId())) {
					e.setCancelled(true);
					p.updateInventory();
					p.sendMessage(Kitpvp.PREFIX + "You cannot enderpearl for another " + Cooldowns.cooldown.get(p.getUniqueId()) + " seconds");
				} else {
					Cooldowns.cooldown.put(p.getUniqueId(), 10);
				}
			}
		}
	}
}
