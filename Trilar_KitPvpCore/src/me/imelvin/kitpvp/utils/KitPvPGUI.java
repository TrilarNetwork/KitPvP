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
		double kills = PlayerDataManager.getKills(p);
		double deaths = PlayerDataManager.getDeaths(p);
		return kills / deaths;
	}

	public static void kitsMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 4 * 9, ChatColor.GOLD + "Kits");
		Kit k = null;
		if (Kits.kits.containsKey(p.getName())) {
			k = Kits.kits.get(p.getName());
		}
		T_ItemStack k1 = new T_ItemStack(CI.createItem(Material.DIAMOND_CHESTPLATE, 1, -1, "Tank Kit"));
		assert k != null;
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
		//Potion ndebuff = new Potion(PotionType.INSTANT_HEAL, 1);
		//ndebuff.setSplash(true);
		//ItemStack ndbuff = ndebuff.toItemStack(1);
		//ndbuff.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "No Debuff Kit");
		//Potion debuff = new Potion(PotionType.INSTANT_DAMAGE, 1);
		//debuff.setSplash(true);
		//ItemStack dbuff = debuff.toItemStack(1);
		//dbuff.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Debuff Kit");
		//Potion invpotion = new Potion(PotionType.INVISIBILITY, 1);
		//invpotion.setSplash(true);
		//ItemStack invisinvp = invpotion.toItemStack(1);
		//invisinvp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Invisible Kit");
		//Potion strengthpot = new Potion(PotionType.STRENGTH, 1);
		//strengthpot.setSplash(true);
		//ItemStack strengthpotion = strengthpot.toItemStack(1);
		//strengthpotion.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Strength Kit");

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
		//inv.setItem(16, ndbuff);
		//inv.setItem(18, dbuff);
		//inv.setItem(20, invisinvp);
		//inv.setItem(22, strengthpotion);
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
		inv.setItem(30, tankhealp);
		inv.setItem(31, tankspeedp);
		inv.setItem(32, tankregp);
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
		inv.setItem(30, pvphealp);
		inv.setItem(31, pvpspeedp);
		inv.setItem(32, pvpregp);
	}

	public static void openTrol(User p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "Troll 1 Kit");
		ItemStack trolhelmet = CI.createItem(Material.LEATHER_HELMET, 1, -1, ChatColor.GOLD + "Troll Helmet");
		trolhelmet.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack trolchestplate = CI.createItem(Material.LEATHER_CHESTPLATE, 1, -1, ChatColor.GOLD + "Troll Chestplate");
		trolchestplate.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack trolleggings = CI.createItem(Material.LEATHER_LEGGINGS, 1, -1, ChatColor.GOLD + "Troll Leggings");
		trolleggings.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack trolboots = CI.createItem(Material.LEATHER_BOOTS, 1, -1, ChatColor.GOLD + "Troll Boots");
		trolboots.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack trolstick = CI.createItem(Material.STICK, 1, -1, ChatColor.GOLD + "Troll Stick");
		trolstick.addEnchantment(Enchantment.KNOCKBACK, 1);
		ItemStack trolbow = CI.createItem(Material.BOW, 1, -1, ChatColor.GOLD + "Troll Bow");
		trolbow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		trolbow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		ItemStack trolarrow = CI.createItem(Material.ARROW, 1, -1, ChatColor.GOLD + "Troll Arrow");
		ItemStack trolgapple = CI.createItem(Material.GOLDEN_APPLE, 3, 0, ChatColor.GOLD + "Troll Apple");
		ItemStack trolpearl = CI.createItem(Material.ENDER_PEARL, 4, -1, ChatColor.GOLD + "Troll Pearl");
		Potion speedpoti = new Potion(PotionType.SPEED, 1);
		speedpoti.setSplash(true);
		ItemStack trolspeedp = speedpoti.toItemStack(1);
		trolspeedp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Troll Speed Pot"); 
		Potion healpoti = new Potion(PotionType.INSTANT_HEAL, 1);
		healpoti.setSplash(true);
		ItemStack trolhealp = healpoti.toItemStack(1);
		trolhealp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Troll Heal Pot");
		Potion regpoti = new Potion(PotionType.REGEN, 1);
		regpoti.setSplash(true);
		ItemStack trolregp = regpoti.toItemStack(1);
		trolregp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Troll Regen Pot");
		
		inv.setItem(20, trolhelmet);
		inv.setItem(21, trolchestplate);
		inv.setItem(22, trolleggings);
		inv.setItem(23, trolboots);
		inv.setItem(19, trolstick);
		inv.setItem(24, trolbow);
		inv.setItem(25, trolarrow);
		inv.setItem(29, trolgapple);
		inv.setItem(33, trolpearl);
		inv.setItem(30, trolhealp);
		inv.setItem(31, trolspeedp);
		inv.setItem(32, trolregp);
	}
	public static void openAxe(User p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "Axe Kit");
		ItemStack axehelmet = CI.createItem(Material.GOLD_HELMET, 1, -1, ChatColor.GOLD + "Axe Helmet");
		axehelmet.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack axechestplate = CI.createItem(Material.GOLD_CHESTPLATE, 1, -1, ChatColor.GOLD + "Axe Chestplate");
		axechestplate.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack axeleggings = CI.createItem(Material.GOLD_LEGGINGS, 1, -1, ChatColor.GOLD + "Axe Leggings");
		axeleggings.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack axeboots = CI.createItem(Material.GOLD_BOOTS, 1, -1, ChatColor.GOLD + "Axe Boots");
		axeboots.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack axeaxe = CI.createItem(Material.IRON_AXE, 1, -1, ChatColor.GOLD + "Axe");
		ItemStack axebow = CI.createItem(Material.BOW, 1, -1, ChatColor.GOLD + "Axe Bow");
		axebow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		axebow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		ItemStack axearrow = CI.createItem(Material.ARROW, 1, -1, ChatColor.GOLD + "Axe Arrow");
		ItemStack axegapple = CI.createItem(Material.GOLDEN_APPLE, 3, 0, ChatColor.GOLD + "Axe Apple");
		ItemStack axepearl = CI.createItem(Material.ENDER_PEARL, 4, -1, ChatColor.GOLD + "Axe Pearl");
		Potion speedpotio = new Potion(PotionType.SPEED, 1);
		speedpotio.setSplash(true);
		ItemStack axespeedp = speedpotio.toItemStack(1);
		axespeedp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Axe Speed Pot"); 
		Potion healpotio = new Potion(PotionType.INSTANT_HEAL, 1);
		healpotio.setSplash(true);
		ItemStack axehealp = healpotio.toItemStack(1);
		axehealp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Axe Heal Pot");
		Potion regpotio = new Potion(PotionType.REGEN, 1);
		regpotio.setSplash(true);
		ItemStack axeregp = regpotio.toItemStack(1);
		axeregp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Axe Regen Pot");
		
		inv.setItem(20, axehelmet);
		inv.setItem(21, axechestplate);
		inv.setItem(22, axeleggings);
		inv.setItem(23, axeboots);
		inv.setItem(19, axeaxe);
		inv.setItem(24, axebow);
		inv.setItem(25, axearrow);
		inv.setItem(29, axegapple);
		inv.setItem(33, axepearl);
		inv.setItem(30, axehealp);
		inv.setItem(31, axespeedp);
		inv.setItem(32, axeregp);
	}
	public static void openTroll(User p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "Troll 2 Kit");
		ItemStack trollhelmet = CI.createItem(Material.LEATHER_HELMET, 1, -1, ChatColor.GOLD + "Troll Helmet");
		trollhelmet.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack trollchestplate = CI.createItem(Material.LEATHER_CHESTPLATE, 1, -1, ChatColor.GOLD + "Troll Chestplate");
		trollchestplate.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack trollleggings = CI.createItem(Material.LEATHER_LEGGINGS, 1, -1, ChatColor.GOLD + "Troll Leggings");
		trollleggings.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack trollboots = CI.createItem(Material.LEATHER_BOOTS, 1, -1, ChatColor.GOLD + "Troll Boots");
		trollboots.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack trollstick = CI.createItem(Material.STICK, 1, -1, ChatColor.GOLD + "Troll Stick");
		trollstick.addEnchantment(Enchantment.KNOCKBACK, 2);
		ItemStack trollbow = CI.createItem(Material.BOW, 1, -1, ChatColor.GOLD + "Troll Bow");
		trollbow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		trollbow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		ItemStack trollarrow = CI.createItem(Material.ARROW, 1, -1, ChatColor.GOLD + "Troll Arrow");
		ItemStack trollgapple = CI.createItem(Material.GOLDEN_APPLE, 3, 0, ChatColor.GOLD + "Troll Apple");
		ItemStack trollpearl = CI.createItem(Material.ENDER_PEARL, 4, -1, ChatColor.GOLD + "Troll Pearl");
		Potion speedpotion = new Potion(PotionType.SPEED, 1);
		speedpotion.setSplash(true);
		ItemStack trollspeedp = speedpotion.toItemStack(1);
		trollspeedp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Troll Speed Pot"); 
		Potion healpotion = new Potion(PotionType.INSTANT_HEAL, 1);
		healpotion.setSplash(true);
		ItemStack trollhealp = healpotion.toItemStack(1);
		trollhealp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Troll Heal Pot");
		Potion regpotion = new Potion(PotionType.REGEN, 1);
		regpotion.setSplash(true);
		ItemStack trollregp = regpotion.toItemStack(1);
		trollregp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Troll Regen Pot");
		
		inv.setItem(20, trollhelmet);
		inv.setItem(21, trollchestplate);
		inv.setItem(22, trollleggings);
		inv.setItem(23, trollboots);
		inv.setItem(19, trollstick);
		inv.setItem(24, trollbow);
		inv.setItem(25, trollarrow);
		inv.setItem(29, trollgapple);
		inv.setItem(33, trollpearl);
		inv.setItem(30, trollhealp);
		inv.setItem(31, trollspeedp);
		inv.setItem(32, trollregp);
	}
	public static void openArcher(User p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "Archer Kit");
		ItemStack archerhelmet = CI.createItem(Material.LEATHER_HELMET, 1, -1, ChatColor.DARK_AQUA + "Archer Helmet");
		archerhelmet.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack archerchestplate = CI.createItem(Material.LEATHER_CHESTPLATE, 1, -1, ChatColor.DARK_AQUA + "Archer Chestplate");
		archerchestplate.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack archerleggings = CI.createItem(Material.LEATHER_LEGGINGS, 1, -1, ChatColor.DARK_AQUA + "Archer Leggings");
		archerleggings.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack archerboots = CI.createItem(Material.LEATHER_BOOTS, 1, -1, ChatColor.DARK_AQUA + "Archer Boots");
		archerboots.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack archersword = CI.createItem(Material.WOOD_SWORD, 1, -1, ChatColor.DARK_AQUA + "Archer Sword");
		ItemStack archerbow = CI.createItem(Material.BOW, 1, -1, ChatColor.DARK_AQUA + "Archer Bow");
		archerbow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		archerbow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		archerbow.addEnchantment(Enchantment.ARROW_FIRE, 1);
		ItemStack archerarrow = CI.createItem(Material.ARROW, 1, -1, ChatColor.DARK_AQUA + "Archer Arrow");
		ItemStack archergapple = CI.createItem(Material.GOLDEN_APPLE, 3, 0, ChatColor.DARK_AQUA + "Archer Apple");
		ItemStack archerpearl = CI.createItem(Material.ENDER_PEARL, 4, -1, ChatColor.DARK_AQUA + "Archer Pearl");
		Potion speedpotioniii = new Potion(PotionType.SPEED, 1);
		speedpotioniii.setSplash(true);
		ItemStack archerspeedp = speedpotioniii.toItemStack(1);
		archerspeedp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Archer Speed Pot"); 
		Potion healpotioniii = new Potion(PotionType.INSTANT_HEAL, 1);
		healpotioniii.setSplash(true);
		ItemStack archerhealp = healpotioniii.toItemStack(1);
		archerhealp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Archer Heal Pot");
		Potion regpotioniii = new Potion(PotionType.REGEN, 1);
		regpotioniii.setSplash(true);
		ItemStack archerregp = regpotioniii.toItemStack(1);
		archerregp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "Archer Regen Pot");
		
		inv.setItem(20, archerhelmet);
		inv.setItem(21, archerchestplate);
		inv.setItem(22, archerleggings);
		inv.setItem(23, archerboots);
		inv.setItem(19, archersword);
		inv.setItem(24, archerbow);
		inv.setItem(25, archerarrow);
		inv.setItem(29, archergapple);
		inv.setItem(33, archerpearl);
		inv.setItem(30, archerhealp);
		inv.setItem(31, archerspeedp);
		inv.setItem(32, archerregp);
	}
	public static void openOP(User p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, ChatColor.GOLD + "OP Kit");
		ItemStack ophelmet = CI.createItem(Material.LEATHER_HELMET, 1, -1, ChatColor.GOLD + "OP Helmet");
		ophelmet.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack opchestplate = CI.createItem(Material.LEATHER_CHESTPLATE, 1, -1, ChatColor.GOLD + "OP Chestplate");
		opchestplate.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack opleggings = CI.createItem(Material.LEATHER_LEGGINGS, 1, -1, ChatColor.GOLD + "OP Leggings");
		opleggings.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack opboots = CI.createItem(Material.LEATHER_BOOTS, 1, -1, ChatColor.GOLD + "OP Boots");
		opboots.addEnchantment(Enchantment.DURABILITY, 3);
		ItemStack opsword = CI.createItem(Material.DIAMOND_SWORD, 1, -1, ChatColor.GOLD + "OP Sword");
		ItemStack oppearl = CI.createItem(Material.ENDER_PEARL, 4, -1, ChatColor.DARK_AQUA + "OP Pearl");
		Potion speedpotionii = new Potion(PotionType.SPEED, 1);
		speedpotionii.setSplash(true);
		ItemStack opspeedp = speedpotionii.toItemStack(1);
		opspeedp.getItemMeta().setDisplayName(ChatColor.DARK_AQUA + "OP Speed Pot");
		
		inv.setItem(20, ophelmet);
		inv.setItem(21, opchestplate);
		inv.setItem(22, opleggings);
		inv.setItem(23, opboots);
		inv.setItem(19, opsword);
		inv.setItem(24, oppearl);
		inv.setItem(29, opspeedp);
	}
}
