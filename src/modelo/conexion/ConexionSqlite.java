package modelo.conexion;

import java.sql.DriverManager;
import java.sql.Connection;


public class ConexionSqlite {
	
	static String database = "";
	static String loginString = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost/"+database;
	
	Connection conexionSqlite = null;
	
	public ConexionSqlite() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexionSqlite=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\borja\\Documents\\Ainhoa\\basesDatos\\sqlite\\ejemplo.db");
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Connection getConexion() {
		return conexionSqlite;
	}
	public void desconectar() {
		conexionSqlite = null;
	}
	
	
	
}
