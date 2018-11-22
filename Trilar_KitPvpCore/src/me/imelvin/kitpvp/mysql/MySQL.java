package me.imelvin.kitpvp.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.imelvin.kitpvp.Kitpvp;

/**
 * Connects to and uses a MySQL database
 * 
 * @author -_Husky_-
 * @author tips48
 */
public class MySQL extends Database {
	private String user;
	private String database;
	private String password;
	private int port;
	private String hostname;

	private Connection conn;
	/**
	 * Creates a new MySQL instance
	 *
	 * @param hostname
	 *            Name of the host
	 * @param port
	 *            Port number
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 */
	/*@Deprecated
	public MySQL(String hostname, String port, String username,
			String password) {
		this(hostname, port, null, username, password);
	}

	 * Creates a new MySQL instance for a specific database
	 *
	 * @param hostname
	 *            Name of the host
	 * @param port
	 *            Port number
	 * @param database
	 *            Database name
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 *
	@Deprecated
	public MySQL(String hostname, String port, String database,
			String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = username;
		this.password = password;
	} */
	
	/**
	 * Creates a new MySQL instance for a specific database
	 *
	 * @param hostname
	 *            Name of the host
	 * @param port
	 *            Port number
	 * @param database
	 *            Database name
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 */
	public MySQL() {
		this.hostname = Kitpvp.host;
		this.port = Kitpvp.port;
		this.database = Kitpvp.db;
		this.user = Kitpvp.user;
		this.password = Kitpvp.pw;
		this.conn = openConnection();
	}

	public Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://185.211.51.30:3306/core?connectTimeout=0&socketTimeout=0&autoReconnect=true", "root", "Pedo1234");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database 
					+ "?connectTimeout=0&socketTimeout=0&autoReconnect=true", this.user, this.password);
			//Connection conn = DriverManager.getConnection("jdbc:mysql://51.254.224.38:3306/uc_core?autoReconnect=true&connectTimeout=0&socketTimeout=0", 
					//"Quinten", "vRvs66z2RpBWnJmb");
			this.conn = conn;
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return this.conn;
	}
	
	public Connection getConnection() {
		return this.conn;
	}
	
	public boolean hasConnection() {
		return (conn != null);
	}
}
