package modelo.dto;

import java.io.Serializable;

/*idProducto int not NULL PRIMARY KEY,
    descripcion VARCHAR(50),
    stockAnual int,
    pvp int*/
public class ProductosDto implements Serializable {

	private int idProducto;
	private String descripcion;
	private int stockAnual;
	private double pvp;

	public ProductosDto(int id, String desc, int stock, double pvp) {
		idProducto = id;
		descripcion = desc;
		stockAnual = stock;
		this.pvp = pvp;
	}
	public ProductosDto() {
		idProducto = 0;
		descripcion = "";
		stockAnual = 0;
		pvp = 0;
	}
	

	public int getIdProducto() {
		return idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getStockAnual() {
		return stockAnual;
	}

	public double getPvp() {
		return pvp;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setStockAnual(int stockAnual) {
		this.stockAnual = stockAnual;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

}
