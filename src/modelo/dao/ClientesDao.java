package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.conexion.ConexionMysql;
import modelo.conexion.ConexionSqlite;
import modelo.dto.ClientesDto;

/* idCliente int not null PRIMARY key,
nombre varchar(50) not null,
direccion varchar(50) not null, 
poblacion varchar(50) not null,
telefono varchar(20) not null,
nif varchar(10) not null UNIQUE*/
public class ClientesDao {

	public void operacionUpdateEnSQLite(String sql) {
		ConexionSqlite conexion = new ConexionSqlite();
		try {
			Statement miStatement = conexion.getConexion().createStatement();
			miStatement.executeUpdate(sql);
			miStatement.close();
			conexion.desconectar();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void operacionUpdateEnMysql(String sql) {
		ConexionMysql conexion = new ConexionMysql();
		try {
			Statement miStatement = conexion.getConexion().createStatement();
			miStatement.executeUpdate(sql);
			miStatement.close();
			conexion.desconectar();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void registrarClientes(ClientesDto miCliente) {
		try {
			if (buscarClientePorNIF(miCliente.getNif()) == 1) {
				String sql = "INSERT INTO clientes VALUES (" + miCliente.getIdCliente() + ", '" + miCliente.getNombre()
						+ "', " + "'" + miCliente.getDireccion() + "', '" + miCliente.getPoblacion() + "', '"
						+ miCliente.getTelefono() + "'," + "'" + miCliente.getNif() + "'";
				operacionUpdateEnMysql(sql);
				operacionUpdateEnSQLite(sql);
				JOptionPane.showMessageDialog(null, "Se ha insertado con exito", "Informaci�n",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
	}

	public int buscarClientePorNIF(String nif) {
		String sql = "SELECT * FROM clientes WHERE nif = '" + nif + "'";
		if (buscarClientesPorNIFEnMySQL(sql) && buscarClientesPorNIFEnSQLite(sql)) {
			return 0;// esta en las dos tablas
		}
		if (!buscarClientesPorNIFEnMySQL(sql) && buscarClientesPorNIFEnSQLite(sql)) {
			return 2;// esta en mysql
		}
		if (buscarClientesPorNIFEnMySQL(sql) && !buscarClientesPorNIFEnSQLite(sql)) {
			return 3;// esta en sqlite
		}
		return 1; // no esta en ninguna de las dos tablas
	}
	public int buscarClientePorID(int id) {
		String sql = "SELECT * FROM clientes WHERE idCliente = " + id + "";
		if (buscarClientesPorNIFEnMySQL(sql) && buscarClientesPorNIFEnSQLite(sql)) {
			return 0;// esta en las dos tablas
		}
		if (!buscarClientesPorNIFEnMySQL(sql) && buscarClientesPorNIFEnSQLite(sql)) {
			return 2;// esta en mysql
		}
		if (buscarClientesPorNIFEnMySQL(sql) && !buscarClientesPorNIFEnSQLite(sql)) {
			return 3;// esta en sqlite
		}
		return 1; // no esta en ninguna de las dos tablas
	}

	public boolean buscarClientesPorNIFEnMySQL(String sql) {
		ConexionMysql miConexion = new ConexionMysql();
		try {
			Statement miStatement = miConexion.getConexion().createStatement();
			ResultSet miResultSet;

			miResultSet = miStatement.executeQuery(sql);
			if (miResultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean buscarClientesPorNIFEnSQLite(String sql) {
		ConexionSqlite miConexion = new ConexionSqlite();
		try {
			Statement miStatement = miConexion.getConexion().createStatement();
			ResultSet miResultSet;

			miResultSet = miStatement.executeQuery(sql);
			if (miResultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void borrarClientePorNIF(String nif) {

		try {

			String sql = "DELETE FROM clientes where NIF like '" + nif + "'";
			operacionUpdateEnMysql(sql);
			operacionUpdateEnSQLite(sql);
			JOptionPane.showMessageDialog(null, "Se ha borrado con exito", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}

	}

	public ArrayList<ClientesDto> consultarClientes() {
		ArrayList<ClientesDto> clientes = new ArrayList<ClientesDto>();
		ConexionMysql con = new ConexionMysql();
		ClientesDto miCliente = new ClientesDto();

		try {
			PreparedStatement consulta = con.getConexion().prepareStatement("SELECT * FROM clientes");
			ResultSet registro = consulta.executeQuery();
			while (registro.next()) {
				miCliente.setIdCliente(registro.getInt("idCliente"));
				miCliente.setNombre(registro.getString("nombre"));
				miCliente.setDireccion(registro.getString("direccion"));
				miCliente.setPoblacion(registro.getString("poblacion"));
				miCliente.setTelefono(registro.getString("telefono"));
				miCliente.setNif(registro.getString("nif"));

				clientes.add(miCliente);
			}
			registro.close();
			con.desconectar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}

		if (clientes.size() > 0) {
			return clientes;
		} else
			return null;
	}

	public void modificarPoblacionClientePorNif(String nif, String poblacion) {
		try {
			String sql = "UPDATE clientes SET poblacion='" + poblacion + "' WHERE nif='" + nif + "'";
			operacionUpdateEnMysql(sql);
			operacionUpdateEnSQLite(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void modificarDireccionClientePorNif(String nif, String direccion) {
		try {
			String sql = "UPDATE clientes SET direccion='" + direccion + "' WHERE nif='" + nif + "'";
			operacionUpdateEnMysql(sql);
			operacionUpdateEnSQLite(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void modificarTelefonoClientePorNif(String nif, String telefono) {
		try {
			String sql = "UPDATE clientes SET telefono='" + telefono + "' WHERE nif='" + nif + "'";
			operacionUpdateEnMysql(sql);
			operacionUpdateEnSQLite(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public int idMaximo() {
		int idMaximo = 1;
		ConexionMysql con = new ConexionMysql();

		try {
			PreparedStatement consulta = con.getConexion().prepareStatement("SELECT MAX(idCliente) FROM clientes ");
			ResultSet registro = consulta.executeQuery();
			while (registro.next()) {
				idMaximo = registro.getInt("idProducto");
			}
			registro.close();
			con.desconectar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}
		return idMaximo;
		
		
	}
}
