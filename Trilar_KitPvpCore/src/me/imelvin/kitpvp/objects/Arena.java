package me.imelvin.kitpvp.objects;

import me.imelvin.kitpvp.Kitpvp;
import me.shizleshizle.core.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class Arena {
    Location arenaLoc;
    String name;
    final FileConfiguration arenaFile = Kitpvp.c.getArenasFile();

    public Arena(Location arenaLoc, String arenaName) {
        this.arenaLoc = arenaLoc;
        name = arenaName;
        load();
    }

    public void updateLocation(Location newLocation) {
        arenaLoc = newLocation;
    }

    public void save() {
        arenaFile.set("arenas." + name + ".x", arenaLoc.getX());
        arenaFile.set("arenas." + name + ".y", arenaLoc.getY());
        arenaFile.set("arenas." + name + ".z", arenaLoc.getZ());
        arenaFile.set("arenas." + name + ".pitch", arenaLoc.getPitch());
        arenaFile.set("arenas." + name + ".yaw", arenaLoc.getYaw());
        arenaFile.set("arenas." + name + ".world", arenaLoc.getWorld());
        Kitpvp.c.saveArenas();
    }

    public void load() {
        final String path = "arenas." + name + ".";
        Location loc = new Location(Bukkit.getWorld(arenaFile.getString(path + "world")),
            arenaFile.getDouble(path + "x"), arenaFile.getDouble(path + "y"),
                arenaFile.getDouble(path + "z"));
        loc.setYaw((float) arenaFile.getDouble(path + "yaw"));
        loc.setPitch((float) arenaFile.getDouble(path + "pitch"));
        arenaLoc = loc;
    }

    public void delete() {
        arenaFile.set("arenas." + name, null);
    }

    public void teleportTo(User p) {
        p.teleport(arenaLoc);
    }
}
