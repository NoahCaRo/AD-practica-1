package modelo.dto;

import java.io.Serializable;
import java.util.Dictionary;

/* idCliente int not null PRIMARY key,
    nombre varchar(50) not null,
    direccion varchar(50) not null, 
    poblacion varchar(50) not null,
    telefono varchar(20) not null,
    nif varchar(10) not null UNIQUE*/
public class ClientesDto implements Serializable{
	private int idCliente;
	private String nombre;
	private String direccion;
	private String poblacion;
	private String telefono;
	private String nif;
	
	public ClientesDto(int id, String n, String d, String p, String t, String nif) {
		idCliente = id;
		nombre = n;
		direccion = d;
		poblacion = p;
		telefono = t;
		this.nif = nif;
	}
	public ClientesDto() {
		idCliente = 0;
		nombre = "";
		direccion = "";
		poblacion = "";
		telefono = "";
		nif = "";
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	
}
