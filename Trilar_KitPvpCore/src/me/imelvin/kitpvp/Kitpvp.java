package me.imelvin.kitpvp;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.imelvin.kitpvp.commands.Arenas;
import me.imelvin.kitpvp.commands.KitSelectorPlacement;
import me.imelvin.kitpvp.events.InvClick;
import me.imelvin.kitpvp.events.PDeath;
import me.imelvin.kitpvp.events.PInteract;
import me.imelvin.kitpvp.events.PJoin;
import me.imelvin.kitpvp.utils.Kits;
import me.imelvin.kitpvp.utils.PlayerDataManager;
import me.shizleshizle.core.mysql.MySQLManager;
import me.shizleshizle.core.utils.DisableAI;

public class Kitpvp extends JavaPlugin {
	public static PvPConfig c = PvPConfig.getInstance();
	public static Plugin p;	
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "KitPvP" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	public static Villager v;
	public static MySQLManager sql;
	public static String host;
	public static String db;
	public static String user;
	public static String pw;
	public static int port;
	
	public void onEnable(){
		Logger l = getLogger();
		l.info("KitPvP Core >> Enabling...");
		long b = System.currentTimeMillis();
		p = this;
		c.setup(p);
		sql = MySQLManager.getInstance();
		sql.setup();
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
		getCommand("arena").setExecutor(new Arenas());
		getCommand("npcplace").setExecutor(new KitSelectorPlacement());
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new InvClick(), this);
		pm.registerEvents(new PDeath(), this);
		pm.registerEvents(new PJoin(), this);
		pm.registerEvents(new PInteract(), this);
		long e = System.currentTimeMillis() - b;
		l.info("KitPvP Core >> Enabled! (" + e + " ms)");
	}
	
	
	public void onDisable(){
		Logger l = getLogger();
		l.info("KitPvp Core >> Disabling...");
		long b = System.currentTimeMillis();
		try {
			if (sql.checkConnection()) {
				sql.closeConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PlayerDataManager.save();
		long e = System.currentTimeMillis() - b;
		l.info("KitPvp Core >> Disabled! (" + e + " ms)");
	}
}