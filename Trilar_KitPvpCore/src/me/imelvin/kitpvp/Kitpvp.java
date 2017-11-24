package me.imelvin.kitpvp;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.imelvin.kitpvp.events.InventoryClick;
import me.imelvin.kitpvp.events.PlayerDeath;
import me.imelvin.kitpvp.events.PlayerJoin;
import me.imelvin.kitpvp.utils.EpearlCD;
import me.imelvin.kitpvp.utils.Kits;
import me.imelvin.kitpvp.utils.PlayerDataManager;
import me.shizleshizle.core.utils.DisableAI;

public class Kitpvp extends JavaPlugin {
	public static PvPConfig c = PvPConfig.getInstance();
	public static Plugin p;	
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "KitPvP" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	public static Villager v;
	
	public void onEnable(){
		Logger l = getLogger();
		l.info("KitPvP Core >> Enabling...");
		long b = System.currentTimeMillis();
		p = this;
		c.setup(p);
		PlayerDataManager.load();
		Kits.setupKits();
		if (c.getNPCLocation() != null) {
			v = (Villager) Bukkit.getServer().getWorld(c.getNPCWorld()).spawnEntity(c.getNPCLocation(), EntityType.VILLAGER);
			v.setAdult();
			v.setAgeLock(true);
			v.setBreed(false);
			v.setCanPickupItems(false);
			v.setProfession(Profession.BLACKSMITH);
			DisableAI.disableAI(v);
		}
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new EpearlCD(), this);
		long e = System.currentTimeMillis();
		l.info("KitPvP Core >> Enabled! (" + (e - b) + " ms)");
	}
	
	
	public void onDisable(){
		Logger l = getLogger();
		l.info("KitPvp Core >> Disabling...");
		long b = System.currentTimeMillis();
		PlayerDataManager.save();
		long e = System.currentTimeMillis();
		l.info("KitPvp Core >> Disabled! (" + (e - b) + " ms)");
	}
}