package me.imelvin.kitpvp.commands;

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

public class Arenas implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("arena")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/arena <set|list|remove> [name]");
						p.sendMessage(Kitpvp.prefix + "'Set' sets the spawnpoint of the arena, not the entire region!");
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("list")) {
							listArenas(p);
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/arena <set|list|remove> [name]");
						}
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("set")) {
							Location l = p.getLocation();
							setArenas(args[1], l.getX(), l.getY(), l.getZ(), l.getPitch(), l.getYaw(), l.getWorld().getName());
							p.sendMessage(Kitpvp.prefix + "You have successfully set the spawn location of " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
						} else if (args[0].equalsIgnoreCase("remove")) {
							if (Kitpvp.c.getArenasFile().contains("arenas." + args[1])) {
								removeArena(args[1]);
								p.sendMessage(Kitpvp.prefix + "You have removed the arena with the name: " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(Kitpvp.prefix + "This arena does not exist!");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/arena <set|list|remove> [name]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/arena <set|list|remove> [name]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/arena");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
	
	private void listArenas(User p) {
		StringBuilder sb = new StringBuilder();
		for (String s : Kitpvp.c.getArenasFile().getConfigurationSection("arenas").getKeys(false)) {
			sb.append(ChatColor.GOLD + s).append(ChatColor.WHITE + ", ");
		}
		String msg = sb.toString().substring(0, sb.length() - 2);
		p.sendMessage(Kitpvp.prefix + "These arenas are currently saved and functional:");
		p.sendMessage(msg);
	}
	
	private void setArenas(String name, double x, double y, double z, float pitch, float yaw, String wname) {
		Kitpvp.c.getArenasFile().set("arenas." + name + ".x", x);
		Kitpvp.c.getArenasFile().set("arenas." + name + ".y", y);
		Kitpvp.c.getArenasFile().set("arenas." + name + ".z", z);
		Kitpvp.c.getArenasFile().set("arenas." + name + ".pitch", pitch);
		Kitpvp.c.getArenasFile().set("arenas." + name + ".yaw", yaw);
		Kitpvp.c.getArenasFile().set("arenas." + name + ".world", wname);
		Kitpvp.c.saveArenas();
	}
	
	private void removeArena(String name) {
		Kitpvp.c.getArenasFile().set("arenas." + name, null);
		Kitpvp.c.saveArenas();
	}
}