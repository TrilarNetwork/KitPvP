package me.imelvin.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import me.imelvin.kitpvp.utils.Kits.Kit;
import me.shizleshizle.core.commands.Skulls;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.utils.CI;
import me.shizleshizle.core.utils.T_ItemStack;

public class KitPvPGUI {

	public static void globalMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, ChatColor.GOLD + "KitPvP Menu");

		ItemStack profile = Skulls.getSkull(p.getName(), 1, ChatColor.GOLD + p.getName() + "'s Profile");
		ItemStack join = CI.createItem(Material.DIAMOND_SWORD, 1, -1, ChatColor.DARK_BLUE + "Join the game");
		ItemStack kits = CI.createItem(Material.CHEST, 1, -1, ChatColor.DARK_AQUA + "Kits");

		inv.setItem(11, profile);
		inv.setItem(13, join);
		inv.setItem(15, kits);
		p.openInventory(inv);
	}

	public static void profileMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, ChatColor.GOLD + "Profile");

		ItemStack kills = CI.createItem(Material.DIAMOND_SWORD, 1, -1,
				ChatColor.GOLD + "Kills: " + ChatColor.YELLOW + PlayerDataManager.getKills(p)); // add
																								// kills
		ItemStack deaths = CI.createItem(Material.SKULL_ITEM, 1, 0,
				ChatColor.DARK_RED + "Deaths: " + ChatColor.YELLOW + PlayerDataManager.getDeaths(p)); // add
																										// deaths;
		ItemStack ratio = CI.createItem(Material.DEAD_BUSH, 1, -1,
				ChatColor.DARK_BLUE + "K/D Ratio: " + ChatColor.YELLOW + getKDRatio(p)); // add
																							// ratio
		ItemStack lstreak = CI.createItem(Material.DRAGON_EGG, 1, -1,
				ChatColor.DARK_GREEN + "Longest Streak: " + ChatColor.YELLOW + PlayerDataManager.getStreak(p)); // streak
		ItemStack back = CI.createItem(Material.DARK_OAK_DOOR, 1, -1, ChatColor.RED + "Back to KitPvP Menu");

		inv.setItem(11, kills);
		inv.setItem(13, deaths);
		inv.setItem(15, ratio);
		inv.setItem(30, lstreak);
		inv.setItem(32, back);
		p.openInventory(inv);
	}

	// FOR ABOVE
	private static double getKDRatio(Player p) {
		int kills = PlayerDataManager.getKills(p);
		int deaths = PlayerDataManager.getDeaths(p);
		double fin = kills / deaths;
		return fin;
	}

	public static void kitsMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 4 * 9, ChatColor.GOLD + "Kits");
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
		ItemStack i2 = CI.createItem(Material.SIGN_POST, 1, -1, ChatColor.WHITE + "Information",
				ChatColor.GOLD + "Right-Click an item to select the kit.",
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

	public static void openTank(User p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "Tank kit");

		ItemStack tankhelmet = CI.createItem(Material.CHAINMAIL_HELMET, 1, -1, ChatColor.DARK_AQUA + "Tank Helmet");
		tankhelmet.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack tankchestplate = CI.createItem(Material.CHAINMAIL_CHESTPLATE, 1, -1,
				ChatColor.DARK_AQUA + "Tank Chestplate");
		tankchestplate.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack tankleggings = CI.createItem(Material.CHAINMAIL_LEGGINGS, 1, -1,
				ChatColor.DARK_AQUA + "Tank Leggings");
		tankleggings.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack tankboots = CI.createItem(Material.CHAINMAIL_BOOTS, 1, -1, ChatColor.DARK_AQUA + "Tank Boots");
		tankboots.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack tanksword = CI.createItem(Material.STONE_SWORD, 1, -1, ChatColor.DARK_AQUA + "Tank Sword");
		ItemStack tankbow = CI.createItem(Material.BOW, 1, -1, ChatColor.DARK_AQUA + "Tank Bow");
		tankbow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		tankbow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		ItemStack tankarrow = CI.createItem(Material.ARROW, 1, -1, ChatColor.DARK_AQUA + "Tank Arrow");
		ItemStack tankgapple = CI.createItem(Material.GOLDEN_APPLE, 3, 0, ChatColor.DARK_AQUA + "Tank Apple");
		ItemStack tankpearl = CI.createItem(Material.ENDER_PEARL, 4, -1, ChatColor.DARK_AQUA + "Tank Pearl");
		Potion tankspeed = new Potion(PotionType.SPEED, 1);
		tankspeed.setSplash(true);
		ItemStack tankspeedp = tankspeed.toItemStack(1);
		tankspeedp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Tank Speed Pot");
		Potion tankheal = new Potion(PotionType.INSTANT_HEAL, 1);
		tankheal.setSplash(true);
		ItemStack tankhealp = tankheal.toItemStack(1);
		tankhealp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Tank Heal Pot");
		Potion tankregen = new Potion(PotionType.REGEN, 1);
		tankregen.setSplash(true);
		ItemStack tankregp = tankregen.toItemStack(1);
		tankregp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Tank Regen Pot");

		inv.setItem(20, tankhelmet);
		inv.setItem(21, tankchestplate);
		inv.setItem(22, tankleggings);
		inv.setItem(23, tankboots);
		inv.setItem(19, tanksword);
		inv.setItem(24, tankbow);
		inv.setItem(25, tankarrow);
		inv.setItem(29, tankgapple);
		inv.setItem(33, tankpearl);
		// inv.setItem(30, tankheal);
		// inv.setItem(31, tankspeed);
		// inv.setItem(32, tankregen);
	}

	public static void openPvp(User p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "Pvp Kit");

		ItemStack pvphelmet = CI.createItem(Material.LEATHER_HELMET, 1, -1, ChatColor.GOLD + "PvP Helmet");
		pvphelmet.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack pvpchestplate = CI.createItem(Material.LEATHER_CHESTPLATE, 1, -1, ChatColor.GOLD + "PvP Chestplate");
		pvpchestplate.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack pvpleggings = CI.createItem(Material.LEATHER_LEGGINGS, 1, -1, ChatColor.GOLD + "PvP Leggings");
		pvpleggings.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack pvpboots = CI.createItem(Material.LEATHER_BOOTS, 1, -1, ChatColor.GOLD + "PvP Boots");
		pvpboots.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack pvpsword = CI.createItem(Material.STONE_SWORD, 1, -1, ChatColor.GOLD + "PvP Sword");
		ItemStack pvpbow = CI.createItem(Material.BOW, 1, -1, ChatColor.GOLD + "PvP Bow");
		pvpbow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		pvpbow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		ItemStack pvparrow = CI.createItem(Material.ARROW, 1, -1, ChatColor.GOLD + "PvP Arrow");
		ItemStack pvpgapple = CI.createItem(Material.GOLDEN_APPLE, 3, 0, ChatColor.GOLD + "PvP Apple");
		ItemStack pvppearl = CI.createItem(Material.ENDER_PEARL, 4, -1, ChatColor.GOLD + "PvP Pearl");
		Potion pvpspeed = new Potion(PotionType.SPEED, 1);
		pvpspeed.setSplash(true);
		ItemStack pvpspeedp = pvpspeed.toItemStack(1);
		pvpspeedp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Pvp Speed Pot");
		Potion pvpheal = new Potion(PotionType.INSTANT_HEAL, 1);
		pvpheal.setSplash(true);
		ItemStack pvphealp = pvpheal.toItemStack(1);
		pvphealp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Pvp Heal Pot");
		Potion pvpregen = new Potion(PotionType.REGEN, 1);
		pvpregen.setSplash(true);
		ItemStack pvpregp = pvpregen.toItemStack(1);
		pvpregp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Pvp Regen Pot");

		inv.setItem(20, pvphelmet);
		inv.setItem(21, pvpchestplate);
		inv.setItem(22, pvpleggings);
		inv.setItem(23, pvpboots);
		inv.setItem(19, pvpsword);
		inv.setItem(24, pvpbow);
		inv.setItem(25, pvparrow);
		inv.setItem(29, pvpgapple);
		inv.setItem(33, pvppearl);
		// inv.setItem(30, pvpheal);
		// inv.setItem(31, pvpspeed);
		// inv.setItem(32, pvpregen);
	}

	public static void openNoDebuff(User p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "No debuff Kit");
	}
}
