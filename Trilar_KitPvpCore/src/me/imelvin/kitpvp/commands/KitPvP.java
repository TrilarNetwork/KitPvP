package me.imelvin.kitpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.imelvin.kitpvp.Kitpvp;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

import java.sql.SQLException;

public class KitPvP implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kitpvp")) {
			if (sender instanceof Player) {
				final Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("npc")) {

						} else if (args[0].equalsIgnoreCase("reload")) {
							saveFiles();
							reloadFiles();
							try {
								if (Kitpvp.sql.hasConnection()) {
									Kitpvp.sql.closeConnection();
									Kitpvp.sql.openConnection();
								}
							} catch (SQLException e) {
								Bukkit.getLogger().info("KitPvP >> MySQL Error: " + e);
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/kitpvp <reload|npc> [set]");
						}
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("npc") && args[1].equalsIgnoreCase("set")) {
							Location l = p.getLocation();
							Kitpvp.c.setNPCLocation(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch(), p.getWorld().toString());
							p.sendMessage(Kitpvp.PREFIX + "You have set the NPC to your location!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/kitpvp <reload|npc> [set]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/kitpvp <reload|npc> [set]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/kitpvp");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}

	private void reloadFiles() {
		Kitpvp.c.reloadArenas();
		Kitpvp.c.reloadConfig();
		Kitpvp.c.reloadPlayerData();
	}

	private void saveFiles() {
		Kitpvp.c.saveArenas();
		Kitpvp.c.saveConfig();
		Kitpvp.c.savePlayerData();
	}
}
