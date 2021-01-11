package modelo.dto;

import java.io.Serializable;

/*idVenta int not null primary key,
    fechaVenta date not null,
    idCliente int,
    idProducto int,
    cantidad int,
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)*/
public class VentasDto implements Serializable{

	private int idVenta;
	private String fechaVenta;
	private int idCliente;
	private int idProducto;
	private int cantidad;
	
	public VentasDto(int idV, String fecha, int idC, int idP, int c) {
		idVenta = idV;
		fechaVenta = fecha;
		idCliente = idC;
		idProducto = idP;
		cantidad = c;
	}
	public VentasDto() {
		idVenta = 0;
		fechaVenta = "";
		idCliente = 0;
		idProducto = 0;
		cantidad = 0;
	}
	public int getIdVenta() {
		return idVenta;
	}
	public String getFechaVenta() {
		return fechaVenta;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
