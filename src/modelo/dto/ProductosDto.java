package modelo.dto;
/*idProducto int not NULL PRIMARY KEY,
    descripcion VARCHAR(50),
    stockAnual int,
    pvp int*/
public class ProductosDto {

	private int idProducto;
	private String descripcion;
	private int stockAnual;
	private int pvp;
	
	public int getIdProducto() {
		return idProducto;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getStockAnual() {
		return stockAnual;
	}
	
	public int getPvp() {
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
	
	public void setPvp(int pvp) {
		this.pvp = pvp;
	}
	
}
