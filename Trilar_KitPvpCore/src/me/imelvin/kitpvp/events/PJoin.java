package me.imelvin.kitpvp.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.imelvin.kitpvp.utils.PlayerDataManager;
import me.imelvin.kitpvp.utils.Scoreboards;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.utils.CI;
import net.md_5.bungee.api.ChatColor;

public class PJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		User p2 = new User(p);
		PlayerDataManager.setStreak(p, 0);
		Scoreboards.updateScoreboard();
		if (!p.hasPlayedBefore()) {
			PlayerDataManager.setDeaths(p, 0);
			PlayerDataManager.setKills(p, 0);
			PlayerDataManager.setLongestStreak(p, 0);
		}
		p2.clearInventory();
		p2.addItem(CI.createItem(Material.DIAMOND, 1, -1, ChatColor.GOLD + "Global Menu"));
	}
}