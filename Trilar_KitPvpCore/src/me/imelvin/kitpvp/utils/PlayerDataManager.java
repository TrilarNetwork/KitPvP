package me.imelvin.kitpvp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;

import me.imelvin.kitpvp.Kitpvp;

public class PlayerDataManager {
	private static HashMap<String, Integer> kills = new HashMap<>();
	private static HashMap<String, Integer> deaths = new HashMap<>();
	private static HashMap<String, Integer> lstreak = new HashMap<>();
	
	public static void save() {
		ArrayList<String> killz = new ArrayList<>();
		for (String name : kills.keySet()) {
			killz.add(name + "|" + kills.get(name));
		}
		Kitpvp.c.getPlayerData().set("data.kills", killz);
		ArrayList<String> deathz = new ArrayList<>();
		for (String name : deaths.keySet()) {
			deathz.add(name + "|" + deaths.get(name));
		}
		Kitpvp.c.getPlayerData().set("data.deaths", deathz);
		ArrayList<String> streak = new ArrayList<>();
		for (String name : lstreak.keySet()) {
			streak.add(name + "|" + lstreak.get(name));
		}
		Kitpvp.c.getPlayerData().set("data.streak", streak);
		Kitpvp.c.savePlayerData();
	}
	
	public static void load() {
		if (Kitpvp.c.getPlayerData().get("data.kills") != null) {
			List<String> killz = Kitpvp.c.getPlayerData().getStringList("data.kills");
			for (String key : killz) {
				String[] data = key.split(Pattern.quote("|"));
				int k = 0;
				try {
					k = Integer.parseInt(data[1]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				kills.put(data[0], k);
			}
		}
		if (Kitpvp.c.getPlayerData().get("data.deaths") != null) {
			List<String> deathz = Kitpvp.c.getPlayerData().getStringList("data.deaths");
			for (String key : deathz) {
				String[] data = key.split(Pattern.quote("|"));
				int d = 0;
				try {
					d = Integer.parseInt(data[1]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				deaths.put(data[0], d);
			}
		}
		if (Kitpvp.c.getPlayerData().get("data.streak") != null) {
			List<String> streak = Kitpvp.c.getPlayerData().getStringList("data.streak");
			for (String key : streak) {
				String[] data = key.split(Pattern.quote("|"));
				int s = 0;
				try {
					s = Integer.parseInt(data[1]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				lstreak.put(data[0], s);
			}
		}
	}
	
	public static void setKills(Player p, int kill) {
		kills.put(p.getName(), kill);
	}
	
	public static void setDeaths(Player p, int death) {
		deaths.put(p.getName(), death);
	}
	
	public static int getKills(Player p) {
		if (kills.get(p.getName()) != null) {
			return kills.get(p.getName());
		} else {
			return 0;
		}
	}
	
	public static int getDeaths(Player p) {
		if (deaths.get(p.getName()) != null) {
			return deaths.get(p.getName());
		} else {
			return 0;
		}
	} 
	
	public static void setLongestStreak(Player p, int streak) {
		lstreak.put(p.getName(), streak);
	}
	
	public static int getStreak(Player p) {
		if (lstreak.get(p.getName()) != null) {
			return lstreak.get(p.getName());
		} else {
			return 0;
		}
	}
}
