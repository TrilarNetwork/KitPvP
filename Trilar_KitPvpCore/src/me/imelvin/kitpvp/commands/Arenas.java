package me.imelvin.kitpvp.commands;

import me.imelvin.kitpvp.objects.Arena;
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

public class Arenas implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("arena")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/arena <set|list|remove> [name]");
						p.sendMessage(Kitpvp.PREFIX + "'Set' sets the spawn point of the arena, not the entire region!");
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("list")) {
							p.sendMessage(Kitpvp.PREFIX + "These arenas are currently saved and functional:");
							p.sendMessage(Kitpvp.arenas.listArenas());
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/arena <set|list|remove> [name]");
						}
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("set")) {
							final Location l = p.getLocation();
							final Location arenaLoc = new Location(l.getWorld(), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());
							if (Kitpvp.arenas.getNames().contains(args[1])) {
								Kitpvp.arenas.getArena(args[1]).updateLocation(arenaLoc);
							} else {
								Kitpvp.arenas.addArena(new Arena(arenaLoc, args[1]));
							}
							p.sendMessage(Kitpvp.PREFIX + "You have successfully set the spawn location of " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
						} else if (args[0].equalsIgnoreCase("remove")) {
							if (Kitpvp.arenas.getNames().contains(args[1])) {
								Kitpvp.arenas.removeArena(Kitpvp.arenas.getArena(args[1]));
								p.sendMessage(Kitpvp.PREFIX + "You have removed the arena with the name " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(Kitpvp.PREFIX + "This arena does not exist!");
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
}