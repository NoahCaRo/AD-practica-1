package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.conexion.ConexionMysql;
import modelo.conexion.ConexionSqlite;
import modelo.dto.VentasDto;

public class VentasDao {
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

	public void registrarVenta(VentasDto miVenta) {
		try {
			if (buscarVentaPorId(miVenta.getIdProducto()) == 1) {
				String sql = "INSERT INTO clientes VALUES (" + miVenta.getIdVenta() + ", '" + miVenta.getFechaVenta()
						+ "', " + miVenta.getIdCliente() + ", " + miVenta.getIdProducto() + ", "
						+ miVenta.getCantidad();
				operacionUpdateEnMysql(sql);
				operacionUpdateEnSQLite(sql);
				JOptionPane.showMessageDialog(null, "Se ha insertado con exito", "Informacion",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
	}

	public int buscarVentaPorId(int idVenta) {
		String sql = "SELECT * FROM Ventas WHERE idVenta = " + idVenta;
		if (buscarVentaPorIDEnMySQL(sql) && buscarVentasPorIdEnSQLite(sql)) {
			return 0;// esta en las dos tablas
		}
		if (!buscarVentaPorIDEnMySQL(sql) && buscarVentasPorIdEnSQLite(sql)) {
			return 2;// esta en mysql
		}
		if (buscarVentaPorIDEnMySQL(sql) && !buscarVentasPorIdEnSQLite(sql)) {
			return 3;// esta en sqlite
		}
		return 1; // no esta en ninguna de las dos tablas
	}

	public boolean buscarVentaPorIDEnMySQL(String sql) {
		ConexionMysql miConexion = new ConexionMysql();
		try {
			Statement miStatement = miConexion.getConexion().createStatement();
			ResultSet miResultSet;

			miResultSet = miStatement.executeQuery(sql);
			if (miResultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean buscarVentasPorIdEnSQLite(String sql) {
		ConexionSqlite miConexion = new ConexionSqlite();
		try {
			Statement miStatement = miConexion.getConexion().createStatement();
			ResultSet miResultSet;

			miResultSet = miStatement.executeQuery(sql);
			if (miResultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void borrarProductoPorId(int idVenta) {
		try {

			String sql = "DELETE FROM ventas WHERE idVentas = '" + idVenta + "'";
			operacionUpdateEnMysql(sql);
			operacionUpdateEnSQLite(sql);
			JOptionPane.showMessageDialog(null, "Se ha borrado con exito", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
	}

	public ArrayList<VentasDto> consultarProductos() {
		ArrayList<VentasDto> ventas = new ArrayList<VentasDto>();
		ConexionMysql con = new ConexionMysql();
		VentasDto venta = new VentasDto();

		try {
			PreparedStatement consulta = con.getConexion().prepareStatement("SELECT * FROM clientes");
			ResultSet registro = consulta.executeQuery();
			while (registro.next()) {
				venta.setIdVenta(registro.getInt("idVenta"));
				venta.setFechaVenta(String.valueOf(registro.getDate("fechaVenta")));
				venta.setIdCliente(registro.getInt("idCliente"));
				venta.setIdProducto(registro.getInt("idProducto"));
				venta.setCantidad(registro.getInt("cantidad"));
				ventas.add(venta);
			}
			registro.close();
			con.desconectar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}
		if (ventas.size() > 0) {
			return ventas;
		} else
			return null;
	}
	public int idMaximo() {
		int idMaximo = 1;
		ConexionMysql con = new ConexionMysql();

		try {
			PreparedStatement consulta = con.getConexion().prepareStatement("SELECT MAX(idVenta) FROM Ventas ");
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