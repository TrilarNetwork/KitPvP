package me.imelvin.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import me.shizleshizle.core.commands.Skulls;
import me.shizleshizle.core.utils.CI;

public class GUI {
	
	public static void globalMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3*9, ChatColor.GOLD + "KitPvP Menu");
		
		ItemStack profile = Skulls.getSkull(p.getName(), 1, ChatColor.GOLD + p.getName() + "'s Profile");
		ItemStack join = CI.createItem(Material.DIAMOND_SWORD, 1, -1, ChatColor.DARK_BLUE + "Join the game");
		ItemStack kits = CI.createItem(Material.CHEST, 1, -1, ChatColor.DARK_AQUA + "Kits");
		
		inv.setItem(11, profile);
		inv.setItem(13, join);
		inv.setItem(15, kits);
		p.openInventory(inv);
	}
	
	public static void profileMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 5*9, ChatColor.GOLD + "Profile");
		
		ItemStack kills = CI.createItem(Material.DIAMOND_SWORD, 1, -1, ChatColor.GOLD + "Kills: " ); // add kills
		ItemStack deaths = CI.createItem(Material.SKULL_ITEM, 1, 0, ChatColor.DARK_RED + "Deaths: " ); // add deaths;
		ItemStack ratio = CI.createItem(Material.DEAD_BUSH, 1, -1, ChatColor.DARK_BLUE + "K/D Ratio: " ); // add ratio
		ItemStack lstreak = CI.createItem(Material.DRAGON_EGG, 1, -1, ChatColor.DARK_GREEN + "Longest Streak: " ); // streak
		ItemStack back = CI.createItem(Material.DARK_OAK_DOOR, 1, -1, ChatColor.RED + "Back to KitPvP Menu");
		
		inv.setItem(11, kills);
		inv.setItem(13, deaths);
		inv.setItem(15, ratio);
		inv.setItem(30, lstreak);
		inv.setItem(32, back);
		p.openInventory(inv);	
	}
	
	public static void kitsMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 4*9, ChatColor.GOLD + "Kits");
		
		ItemStack k1 = CI.createItem(Material.DIAMOND_CHESTPLATE, 1, -1, "Tank Kit");
		ItemStack k2 = CI.createItem(Material.STONE_SWORD, 1, -1, "Pvp Kit");
		ItemStack k3 = CI.createItem(Material.STICK, 1, -1, "Troll Kit");
		ItemStack k4 = CI.createItem(Material.DIAMOND_AXE, 1, -1, "Axe Kit");
		ItemStack k5 = CI.createItem(Material.BLAZE_ROD, 1, -1, "Troll 2 Kit");
		ItemStack k6 = CI.createItem(Material.BOW, 1, -1, "Archer Kit");
		ItemStack k7 = CI.createItem(Material.DIAMOND_SWORD, 1, -1, "Op Kit");
		Potion ndebuff = new Potion(PotionType.INSTANT_HEAL, 1);
		ndebuff.setSplash(true);
		ItemStack ndbuff = ndebuff.toItemStack(1);
		ndbuff.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "No Debuff Kit");
		Potion debuff = new Potion(PotionType.INSTANT_DAMAGE, 1);
		debuff.setSplash(true);
		ItemStack dbuff = debuff.toItemStack(1);
		dbuff.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Debuff Kit");
		Potion invpotion = new Potion(PotionType.INVISIBILITY, 1);
		invpotion.setSplash(true);
		ItemStack invisinvp = invpotion.toItemStack(1);
		invisinvp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Invisible Kit");
		Potion strengthpot = new Potion(PotionType.STRENGTH, 1);
		strengthpot.setSplash(true);
		ItemStack strengthpotion = strengthpot.toItemStack(1);
		invisinvp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Strength Kit"); 
		
		inv.setItem(11, k1);
		inv.setItem(13, k2);
		inv.setItem(15, k3);
		inv.setItem(21, k4);
		inv.setItem(23, k5);
		inv.setItem(25, k6);
		inv.setItem(27, k7);
		inv.setItem(29, ndbuff);
		inv.setItem(31, dbuff);
		inv.setItem(33, invisinvp);
		inv.setItem(35, strengthpotion);
		p.openInventory(inv);
	}
}
