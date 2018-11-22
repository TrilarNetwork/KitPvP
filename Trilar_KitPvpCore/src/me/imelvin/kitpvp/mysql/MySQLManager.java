package me.imelvin.kitpvp.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import org.bukkit.Bukkit;

import me.shizleshizle.core.objects.User;

public class MySQLManager extends MySQL {
	public MySQL db;
	
	private static MySQLManager instance = new MySQLManager();
	
	public static MySQLManager getInstance() {
		return instance;
	}
	
	public void setup() {
		try {
			this.db = new MySQL();
			openConnection();
			Statement s = getConnection().createStatement();
			Statement s1 = getConnection().createStatement();
			Statement s2 = getConnection().createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS player_ranks (player varchar(32), rank varchar(50))");
			s1.executeUpdate("CREATE TABLE IF NOT EXISTS player_ips (player varchar(32), ip varchar(32))");
			s2.executeUpdate("CREATE TABLE IF NOT EXISTS tickets (id INTEGER AUTO_INCREMENT PRIMARY KEY, owner varchar(32), status varchar(32), description varchar(128), "
					+ "x double, y double, z double, world varchar(128))");
			s.close();
			s1.close();
		} catch (SQLException e) {
			Bukkit.getServer().getLogger().info("McCore >> Error: " + e);
		}
	}
	
	public void getReady() {
		if (this.db.getConnection() == null) {
			this.db.openConnection();
		}
	}
	
	public synchronized String getIP(String name) {
        String ip = "";
        String pn = name.toLowerCase();
        try {
            getReady();
            Statement s = getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM player_ips WHERE player='" + pn + "'");
            if (rs.next()) {
                ip = rs.getString("ip");
            }
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
        }
        return ip;
    }
	
	public synchronized void updatePlayer(User p) {
        String pn = p.getName().toLowerCase();
        String ip = p.getAddress().toString();
        ip = ip.substring(0, ip.length() - 6);
        try {
            getReady();
            PreparedStatement s;
            String getip = getIP(p.getName());
            if (getip.equals("")) {
                s = getConnection().prepareStatement("INSERT INTO player_ips (player, ip) VALUES ('" + pn + "', '" + ip + "')");
            } else {
                s = getConnection().prepareStatement("UPDATE player_ips SET ip='" + ip + "' WHERE player='" + pn + "'");
            }
            s.executeUpdate();
            s.close();
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
        }
    }
}
