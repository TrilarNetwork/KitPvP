package me.imelvin.kitpvp.events;

import java.util.HashMap;
import java.util.UUID;

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
	
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();

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
				if (cooldown.containsKey(p.getUniqueId()) && cooldown.get(p.getUniqueId()) > System.currentTimeMillis()) {
					e.setCancelled(true);
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
