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

public class KitSelectorPlacement implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("npcplace")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 0) {
						Location l = p.getLocation();
						Kitpvp.c.setNPCLocation(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch(), p.getWorld().toString());
						p.sendMessage(Kitpvp.prefix + "You have set the NPC to your location!");
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/npcplace");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/npcplace");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
