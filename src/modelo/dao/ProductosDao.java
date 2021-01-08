package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.conexion.ConexionMysql;
import modelo.conexion.ConexionSqlite;
import modelo.dto.ProductosDto;


public class ProductosDao {
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

	public void registrarProducto(ProductosDto miProductos) {
		try {
			if (buscarProductoPorId(miProductos.getIdProducto()) == 1) {
				String sql = "INSERT INTO productos VALUES (" + miProductos.getIdProducto() + ", '" + miProductos.getDescripcion()
						+ "', " + miProductos.getStockAnual() + ", " + miProductos.getPvp();
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

	public int buscarProductoPorId(int idProducto) {
		String sql = "SELECT * FROM productos WHERE idProducto = " + idProducto ;
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
			e.printStackTrace();
		}
		return false;
	}

	public void borrarProductoPorId(int idProducto){
		try {

			String sql = "DELETE FROM productos WHERE idProducto = '" + idProducto + "'";
			operacionUpdateEnMysql(sql);
			operacionUpdateEnSQLite(sql);
			JOptionPane.showMessageDialog(null, "Se ha borrado con exito", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
	}
	
	public ArrayList<ProductosDto> consultarProductos(){
		ArrayList<ProductosDto> productos = new ArrayList<ProductosDto>();
		ConexionMysql con = new ConexionMysql();
		ProductosDto producto = new ProductosDto();

		try {
			PreparedStatement consulta = con.getConexion().prepareStatement("SELECT * FROM productos");
			ResultSet registro = consulta.executeQuery();
			while (registro.next()) {
				producto.setIdProducto(registro.getInt("idProducto"));
				producto.setDescripcion(registro.getString("descripcion"));
				producto.setStockAnual(registro.getInt("stockAnual"));
				producto.setPvp(registro.getInt("pvp"));
				productos.add(producto);
			}
			registro.close();
			con.desconectar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}
		if (productos.size() > 0) {
			return productos;
		} else
			return null;
	}
	
}
