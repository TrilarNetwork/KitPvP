package me.imelvin.kitpvp.objects;

import me.imelvin.kitpvp.Kitpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ArenaHandler {
    private ArrayList<Arena> arenas;

    public ArenaHandler() {
        arenas = new ArrayList<>();
    }

    public ArrayList<Arena> getArenas() {
        return arenas;
    }

    public Arena getArena(String name) {
        Arena arena = null;
        for (Arena arenaIterator : arenas) {
            if (arenaIterator.name.equalsIgnoreCase(name)) {
                arena = arenaIterator;
            }
        }
        return arena;
    }

    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Arena arena : arenas) {
            names.add(arena.name);
        }
        return names;
    }

    public void addArena(Arena arena) {
        arenas.add(arena);
    }

    public void removeArena(Arena arena) {
        arena.delete();
        arenas.remove(arena);
    }

    public void loadArenas() {
        for (Arena arena : arenas) {
            arena.load();
        }
    }

    public void save() {
        for (Arena arena : arenas) {
            arena.save();
        }
    }

    public String listArenas() {
        StringBuilder sb = new StringBuilder();
        for (Arena arena : arenas) {
            sb.append(ChatColor.GOLD).append(arena.name).append(ChatColor.WHITE).append(", ");
        }
        return sb.toString().trim();
    }
}
