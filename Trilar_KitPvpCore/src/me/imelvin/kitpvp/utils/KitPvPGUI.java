package me.imelvin.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import me.imelvin.kitpvp.utils.Kits.Kit;
import me.shizleshizle.core.commands.Skulls;
import me.shizleshizle.core.utils.CI;
import me.shizleshizle.core.utils.T_ItemStack;

public class KitPvPGUI {
	
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
		
		ItemStack kills = CI.createItem(Material.DIAMOND_SWORD, 1, -1, ChatColor.GOLD + "Kills: " + ChatColor.YELLOW + PlayerDataManager.getKills(p)); // add kills
		ItemStack deaths = CI.createItem(Material.SKULL_ITEM, 1, 0, ChatColor.DARK_RED + "Deaths: " + ChatColor.YELLOW + PlayerDataManager.getDeaths(p)); // add deaths;
		ItemStack ratio = CI.createItem(Material.DEAD_BUSH, 1, -1, ChatColor.DARK_BLUE + "K/D Ratio: " + ChatColor.YELLOW + getKDRatio(p)); // add ratio
		ItemStack lstreak = CI.createItem(Material.DRAGON_EGG, 1, -1, ChatColor.DARK_GREEN + "Longest Streak: " + ChatColor.YELLOW + PlayerDataManager.getStreak(p)); // streak
		ItemStack back = CI.createItem(Material.DARK_OAK_DOOR, 1, -1, ChatColor.RED + "Back to KitPvP Menu");
		
		inv.setItem(11, kills);
		inv.setItem(13, deaths);
		inv.setItem(15, ratio);
		inv.setItem(30, lstreak);
		inv.setItem(32, back);
		p.openInventory(inv);	
	}
	
	//FOR ABOVE
	private static double getKDRatio(Player p) {
		int kills = PlayerDataManager.getKills(p);
		int deaths = PlayerDataManager.getDeaths(p);
		double fin = kills / deaths;
		return fin;
	}
	
	public static void kitsMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 4*9, ChatColor.GOLD + "Kits");
		Kit k = null;
		if (Kits.kits.containsKey(p.getName())) {
			k = Kits.kits.get(p.getName());
		}
		T_ItemStack k1 = new T_ItemStack(CI.createItem(Material.DIAMOND_CHESTPLATE, 1, -1, "Tank Kit"));
		if (Kits.kits.containsKey(p.getName()) && k.equals(Kit.TANK)) {
			k1.addGlow();
		}
		T_ItemStack k2 = new T_ItemStack(CI.createItem(Material.STONE_SWORD, 1, -1, "Pvp Kit"));
		if (Kits.kits.containsKey(p.getName()) && k.equals(Kit.PVP)) {
			k2.addGlow();
		}
		T_ItemStack k3 = new T_ItemStack(CI.createItem(Material.STICK, 1, -1, "Troll Kit"));
		if (Kits.kits.containsKey(p.getName()) && k.equals(Kit.TROLL)) {
			k3.addGlow();
		}
		T_ItemStack k4 = new T_ItemStack(CI.createItem(Material.DIAMOND_AXE, 1, -1, "Axe Kit"));
		if (Kits.kits.containsKey(p.getName()) && k.equals(Kit.AXE)) {
			k4.addGlow();
		}
		T_ItemStack k5 = new T_ItemStack(CI.createItem(Material.BLAZE_ROD, 1, -1, "Troll 2 Kit"));
		if (Kits.kits.containsKey(p.getName()) && k.equals(Kit.TROLL_2)) {
			k5.addGlow();
		}
		T_ItemStack k6 = new T_ItemStack(CI.createItem(Material.BOW, 1, -1, "Archer Kit"));
		if (Kits.kits.containsKey(p.getName()) && k.equals(Kit.ARCHER)) {
			k6.addGlow();
		}
		T_ItemStack k7 = new T_ItemStack(CI.createItem(Material.DIAMOND_SWORD, 1, -1, "Op Kit"));
		if (Kits.kits.containsKey(p.getName()) && k.equals(Kit.OP)) {
			k7.addGlow();
		}
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
		
		ItemStack i = CI.createItem(Material.DARK_OAK_DOOR, 1, -1, ChatColor.DARK_AQUA + "Go back to main menu!");
		ItemStack i2 = CI.createItem(Material.SIGN_POST, 1, -1, ChatColor.WHITE + "Information", ChatColor.GOLD + "Right-Click an item to select the kit.", 
				ChatColor.YELLOW + "Left-Click an item to preview the kit.");
		
		inv.setItem(2, k1.toItemStack());
		inv.setItem(4, k2.toItemStack());
		inv.setItem(6, k3.toItemStack());
		inv.setItem(8, k4.toItemStack());
		inv.setItem(10, k5.toItemStack());
		inv.setItem(12, k6.toItemStack());
		inv.setItem(14, k7.toItemStack());
		inv.setItem(16, ndbuff);
		inv.setItem(18, dbuff);
		inv.setItem(20, invisinvp);
		inv.setItem(22, strengthpotion);
		inv.setItem(31, i);
		inv.setItem(35, i2);
		p.openInventory(inv);
	}
}
