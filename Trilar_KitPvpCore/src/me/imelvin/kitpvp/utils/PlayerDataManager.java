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
	private static HashMap<String, Integer> longestStreak = new HashMap<>();
	public static HashMap<String, Integer> streak = new HashMap<>();
	
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
		ArrayList<String> longStreak = new ArrayList<>();
		for (String name : longestStreak.keySet()) {
			longStreak.add(name + "|" + longestStreak.get(name));
		}
		Kitpvp.c.getPlayerData().set("data.streak", longStreak);
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
				longestStreak.put(data[0], s);
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
		return kills.get(p.getName());
	}
	
	public static int getDeaths(Player p) {
		return deaths.get(p.getName());
	} 
	
	public static void setLongestStreak(Player p, int streak) {
		longestStreak.put(p.getName(), streak);
	}
	
	public static int getLongestStreak(Player p) {
		return longestStreak.get(p.getName());
	}

	public static boolean hasStreak(Player p) {
		return streak.containsKey(p.getName());
	}

	public static void setStreak(Player p, int newStreak) {
		streak.put(p.getName(), newStreak);
	}

	public static int getStreak(Player p) {
		return streak.get(p.getName());
	}
}
