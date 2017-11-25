package me.imelvin.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.imelvin.kitpvp.Kitpvp;
import me.shizleshizle.core.objects.User;

public class Scoreboards implements Listener {

	private static void scoreboard(User p) {
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		Scoreboard board = sm.getNewScoreboard();
		Objective o = board.registerNewObjective("kitpvp", "dummy");

		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.GOLD + "KitPvP");

		o.getScore(ChatColor.AQUA + "").setScore(10); //EMPTY
		o.getScore(p.getDisplayName()).setScore(9);  //DISPLAY NAME
		o.getScore(ChatColor.WHITE + "").setScore(8); //EMPTY
		o.getScore(ChatColor.GOLD + "Kills: " + ChatColor.YELLOW + PlayerDataManager.getKills(p.getUser())).setScore(7);; //KILLS
		o.getScore(ChatColor.RED + "").setScore(6); //EMPTY
		o.getScore(ChatColor.GOLD + "Deaths: " + ChatColor.YELLOW + PlayerDataManager.getDeaths(p.getUser())).setScore(5);; //DEATHS
		o.getScore(ChatColor.BLACK + "").setScore(4); //EMPTY
		o.getScore(ChatColor.GOLD + "Longest Streak: " + ChatColor.YELLOW + PlayerDataManager.getStreak(p.getUser())).setScore(3);; //STREAK
		o.getScore(ChatColor.GREEN + "").setScore(2); //EMPTY
		p.setScoreboard(board);
	}
	
	public static void updateScoreboard() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Kitpvp.p, () -> {
			for (Player all : Bukkit.getOnlinePlayers()) {
				User p = new User(all);
				scoreboard(p);
			}
		}, 1L, 60L);
	}
}
