package me.imelvin.kitpvp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class PvPConfig {
	private static PvPConfig instance = new PvPConfig();
	private FileConfiguration config;
	private File cfile;
	private FileConfiguration playerData;
	private File pdf;
	private FileConfiguration arenas;
	private File carena;

	public static PvPConfig getInstance() {
		return instance;
	}

	public void setup(Plugin p) {
		cfile = new File(p.getDataFolder(), "config.yml");
		pdf = new File(p.getDataFolder(), "playerdata.yml");
		if (!p.getDataFolder().exists()) {
			try {
				Path path = Paths.get(cfile.getAbsolutePath());
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!cfile.exists()) {
			try {
				Path path = Paths.get(cfile.getAbsolutePath());
				Files.createFile(path);
				config = YamlConfiguration.loadConfiguration(cfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			config = p.getConfig();
			config.options().copyDefaults(true);
			saveConfig();
		}
		if (!pdf.exists()) {
			try {
				Path path = Paths.get(pdf.getAbsolutePath());
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		playerData = YamlConfiguration.loadConfiguration(pdf);
		if (!carena.exists()) {
			try {
				Path path = Paths.get(carena.getAbsolutePath());
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		arenas = YamlConfiguration.loadConfiguration(carena);
	}

	public void saveConfig() {
		try {
			config.save(cfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(cfile);
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public void savePlayerData() {
		try {
			config.save(pdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reloadPlayerData() {
		playerData = YamlConfiguration.loadConfiguration(pdf);
	}

	public FileConfiguration getPlayerData() {
		return playerData;
	}
	
	public void saveArenas() {
		try {
			arenas.save(carena);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reloadArenas() {
		arenas = YamlConfiguration.loadConfiguration(carena);
	}
	
	public FileConfiguration getArenasFile() {
		return arenas;
	}
	
	public void setNPCLocation(double x, double y, double z, float yaw, float pitch, String world) {
		config.set("settings.npc.x", x);
		config.set("settings.npc.y", y);
		config.set("settings.npc.z", z);
		config.set("settings.npc.yaw", yaw);
		config.set("settings.npc.pitch", pitch);
		config.set("settings.npc.world", world);
		saveConfig();
		Location l = new Location(Bukkit.getWorld(world), x, y, z);
		l.setYaw(yaw);
		l.setPitch(pitch);
		Kitpvp.v.teleport(l);
	}
	
	public Location getNPCLocation() {
		Location l = null;
		double x;
		double y;
		double z;
		float yaw;
		float pitch;
		World w;
		if (config.get("spawn.x") != null && config.get("spawn.y") != null && config.get("spawn.z") != null && config.get("spawn.yaw") != null
				&& config.get("spawn.pitch") != null && config.get("spawn.world") != null) {
			x = config.getDouble("spawn.x");
			y = config.getDouble("spawn.y");
			z = config.getDouble("spawn.z");
			yaw = (float) config.getDouble("spawn.yaw");
			pitch = (float) config.getDouble("spawn.pitch");
			w = Bukkit.getServer().getWorld(config.getString("spawn.world"));
			l = new Location(w, x, y, z);
			l.setYaw(yaw);
			l.setPitch(pitch);
		}
		return l;
	}
	
	public String getNPCWorld() {
		String w = "";
		if (config.get("spawn.world") != null) {
			w = config.getString("spawn.world");
		}
		return w;
	}
}
