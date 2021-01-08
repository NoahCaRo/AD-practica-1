package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConexionMysql {
	static String database = "";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost/"+database;
	
	Connection conexionMySQL = null;
	
	public ConexionMysql() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexionMySQL = DriverManager.getConnection(url, login, password);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Connection getConexion() {
		return conexionMySQL;
	}
	
	public void desconectar() {
		conexionMySQL = null;
	}
	
}
