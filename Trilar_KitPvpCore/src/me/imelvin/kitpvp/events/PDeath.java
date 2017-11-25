package me.imelvin.kitpvp.events;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.imelvin.kitpvp.utils.PlayerDataManager;

public class PDeath implements Listener {
	public static HashMap<String, Integer> streak = new HashMap<>();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (!(p.getKiller() instanceof Player)) return;
		Player killer = p.getKiller();
		if (streak.containsKey(p.getName())) {
			streak.put(p.getName(), 0);
		}
		PlayerDataManager.setDeaths(p, PlayerDataManager.getDeaths(p) + 1);
		PlayerDataManager.setKills(killer, PlayerDataManager.getKills(p) + 1);
		if (streak.containsKey(killer.getName())) {
			int cstreak = streak.get(killer.getName());
			streak.put(killer.getName(), streak.get(killer.getName()) + 1);
			int streak = PlayerDataManager.getStreak(killer);
			if (cstreak > streak) {
				PlayerDataManager.setLongestStreak(killer, cstreak);
			}
		}
	}
}
