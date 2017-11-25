package me.imelvin.kitpvp.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.imelvin.kitpvp.utils.KitPvPGUI;

public class InvClick implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
		ItemStack i = e.getCurrentItem();
		if (ChatColor.stripColor(p.getOpenInventory().getTitle()).equals("KitPvP Menu")) {
			e.setCancelled(true);
			if (i.getType() == Material.SKULL_ITEM) {
				p.closeInventory();
				KitPvPGUI.profileMenu(p);
			} else if (i.getType() == Material.DIAMOND_SWORD) {
				// join game
			} else if (i.getType() == Material.CHEST) {
				p.closeInventory();
				KitPvPGUI.kitsMenu(p);
			}
		} else if (ChatColor.stripColor(p.getOpenInventory().getTitle()).equals("Profile")) {
			e.setCancelled(true);
			if (i.getType() == Material.DARK_OAK_DOOR) {
				p.closeInventory();
				KitPvPGUI.globalMenu(p);
			}
		} else if (ChatColor.stripColor(p.getOpenInventory().getTitle()).equals("Kits")) {
			e.setCancelled(true);
			if (i.getType() == Material.WOOD_SWORD) {
				// open starter kit
			} else if (i.getType() == Material.STONE_SWORD) {
				// open normal kit
			} else if (i.getType() == Material.IRON_SWORD) {
				// open legend kit
			} else if (i.getType() == Material.GOLD_SWORD) {
				// open ultra kit
			} else if (i.getType() == Material.DIAMOND_SWORD) {
				// open god kit
			}
		}
	}
}
