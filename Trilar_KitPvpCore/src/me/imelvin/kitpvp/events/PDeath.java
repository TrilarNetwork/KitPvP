package me.imelvin.kitpvp.events;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.imelvin.kitpvp.utils.PlayerDataManager;

public class PDeath implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (p.getKiller() == null) return;
		Player killer = p.getKiller();
		if (PlayerDataManager.hasStreak(p)) {
			PlayerDataManager.setStreak(p, 0);
		}
		PlayerDataManager.setDeaths(p, PlayerDataManager.getDeaths(p) + 1);
		PlayerDataManager.setKills(killer, PlayerDataManager.getKills(p) + 1);
		if (PlayerDataManager.hasStreak(killer)) {
			int currentStreak = PlayerDataManager.getStreak(killer) + 1;
			PlayerDataManager.setStreak(killer, currentStreak);
			int streak = PlayerDataManager.getLongestStreak(killer);
			if (currentStreak > streak) {
				PlayerDataManager.setLongestStreak(killer, currentStreak);
			}
		}
	}
}
