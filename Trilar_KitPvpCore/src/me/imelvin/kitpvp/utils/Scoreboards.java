package me.imelvin.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.imelvin.kitpvp.Kitpvp;

public class Scoreboards implements Listener {

	public void scoreboard(Player p) {
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		Scoreboard board = sm.getNewScoreboard();
		Objective o = board.registerNewObjective("kitpvp", "dummy");

		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.BLUE + "KitPvp");

		o.getScore(ChatColor.AQUA + "").setScore(10);
		o.getScore(p.getName()).setScore(9);

		p.setScoreboard(board);
	}

	// SCOREBOARD UPDATER

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Kitpvp.p, new Runnable() {
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers())
					scoreboard(all);
			}
		}, 1L, 60L);
	}
}
