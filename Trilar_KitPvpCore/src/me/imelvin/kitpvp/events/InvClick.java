package me.imelvin.kitpvp.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.imelvin.kitpvp.utils.KitPvPGUI;
import me.imelvin.kitpvp.utils.Kits;
import me.imelvin.kitpvp.utils.Kits.Kit;

import java.util.Objects;

public class InvClick implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
		ItemStack i = e.getCurrentItem();
		switch (ChatColor.stripColor(p.getOpenInventory().getTitle())) {
			case "KitPvP Menu":
				e.setCancelled(true);
				if (i.getType() == Material.PLAYER_HEAD) {
					p.closeInventory();
					KitPvPGUI.profileMenu(p);
				} else if (i.getType() == Material.DIAMOND_SWORD) {
					// join game
				} else if (i.getType() == Material.CHEST) {
					p.closeInventory();
					KitPvPGUI.kitsMenu(p);
				}
				break;
			case "Profile":
				e.setCancelled(true);
				if (i.getType() == Material.DARK_OAK_DOOR) {
					p.closeInventory();
					KitPvPGUI.globalMenu(p);
				}
				break;
			case "Kits":
				e.setCancelled(true);
				if (i.getType() == Material.DIAMOND_CHESTPLATE) {
					if (e.getAction() == InventoryAction.NOTHING) {
						Kits.giveKit(p, Kit.TANK);
					} else {
						// view kit
					}
				} else if (i.getType() == Material.STONE_SWORD) {
					if (e.getAction() == InventoryAction.NOTHING) {
						Kits.giveKit(p, Kit.PVP);
					} else {
						// view kit
					}
				} else if (i.getType() == Material.STICK) {
					if (e.getAction() == InventoryAction.NOTHING) {
						Kits.giveKit(p, Kit.TROLL);
					} else {
						// view kit
					}
				} else if (i.getType() == Material.DIAMOND_AXE) {
					if (e.getAction() == InventoryAction.NOTHING) {
						Kits.giveKit(p, Kit.AXE);
					} else {
						// view kit
					}
				} else if (i.getType() == Material.BLAZE_ROD) {
					if (e.getAction() == InventoryAction.NOTHING) {
						Kits.giveKit(p, Kit.TROLL_2);
					} else {
						// view kit
					}
				} else if (i.getType() == Material.BOW) {
					if (e.getAction() == InventoryAction.NOTHING) {
						Kits.giveKit(p, Kit.ARCHER);
					} else {
						// view kit
					}
				} else if (i.getType() == Material.DIAMOND_SWORD) {
					if (e.getAction() == InventoryAction.NOTHING) {
						Kits.giveKit(p, Kit.OP);
					} else {
						// view kit
					}
				} else if (i.getType() == Material.POTION) {
					if (ChatColor.stripColor(Objects.requireNonNull(i.getItemMeta()).getDisplayName()).equals("No Debuff Kit")) {
						if (e.getAction() == InventoryAction.NOTHING) {
							Kits.giveKit(p, Kit.NO_DEBUFF);
						} else {
							// view kit
						}
					} else if (ChatColor.stripColor(i.getItemMeta().getDisplayName()).equals("Debuff Kit")) {
						if (e.getAction() == InventoryAction.NOTHING) {
							Kits.giveKit(p, Kit.DEBUF);
						} else {
							// view kit
						}
					} else if (ChatColor.stripColor(i.getItemMeta().getDisplayName()).equals("Invisible Kit")) {
						if (e.getAction() == InventoryAction.NOTHING) {
							Kits.giveKit(p, Kit.INVISIBLE);
						} else {
							// view kit
						}
					} else if (ChatColor.stripColor(i.getItemMeta().getDisplayName()).equals("Strength Kit")) {
						if (e.getAction() == InventoryAction.NOTHING) {
							Kits.giveKit(p, Kit.STRENGTH);
						} else {
							// view kit
						}
					}
				}
				break;
		}
	}
}
