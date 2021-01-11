package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import modelo.dto.ClientesDto;

public class GeneraClientes {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList<ClientesDto> clientes = new ArrayList<ClientesDto>();

	public static void main(String[] args) {
		String nombreFichero = "CLIENTES" + dimeFechaActualConFormato() + "_" + dimeContadorDiario() + ".bin";
		boolean salir = false;
		do {
			menuPrincipal();

			switch (introduceOpcion()) {
			case 1:
				introduceDatosDelProducto();
				break;
			case 2:
				menuSalida();
				switch (introduceOpcion()) {
				case 1:
					System.out.println("*Introduciendo datos en el archivo*");
					introduceDatosEnElArchivoBinario(nombreFichero);
					salir = true;
					break;
				case 2:
					System.out.println("Ha insertado no salir");
					break;
				default:
					mensajeDeError();
					break;
				}
				break;
			default:
				mensajeDeError();
				break;
			}

		} while (!salir);
		scanner.close();
		System.out.println("HASTA LUEGO");
	}

	private static void menuPrincipal() {
		System.out.println("Selecciona una opcion : ");
		System.out.println("1.- Crear un producto");
		System.out.println("2.- Salir");
		System.out.println("--------------------------------------");
	}

	private static void menuSalida() {
		System.out.println("¿Esta seguro que desea salir? ");
		System.out.println("1.- Si");
		System.out.println("2.- No");
		System.out.println("--------------------------------------");
	}

	private static int introduceOpcion() {
		int opcion = 0;
		do {
			try {
				opcion = Integer.parseInt(inserccionDatoStringAuxiliar());
				if (opcion == 1 || opcion == 2) {
					return opcion;

				} else {
					mensajeDeError();
				}
			} catch (Exception e) {
				mensajeDeError();
			}
		} while (opcion != 1 || opcion != 2);
		return opcion;
	}

	private static void introduceDatosDelProducto() {
		clientes.add(new ClientesDto(0, introduceNombreDelCliente(), introduceDireccionDelCliente(),
				introducePoblacionDelCliente(), introduceTelefonoDelCliente(), introduceNIFDelCliente()));
	}/*
		 * private int idCliente; private String nombre; private String direccion;
		 * private String poblacion; private String telefono; private String nif;
		 */

	private static void enunciadoNombreCliente() {
		System.out.println("Inserte el NOMBRE del cliente");
		System.out.println("--------------------------------------");
	}

	private static String introduceNombreDelCliente() {
		String nombre;
		do {
			enunciadoNombreCliente();
			nombre = escribirYComprobarString();

		} while (nombre.trim().equals(""));

		return nombre;
	}

	private static void enunciadoDireccionCliente() {
		System.out.println("Inserte la DIRECCION del cliente");
		System.out.println("--------------------------------------");
	}

	private static String introduceDireccionDelCliente() {
		String direccion;
		do {
			enunciadoDireccionCliente();
			direccion = escribirYComprobarString();

		} while (direccion.trim().equals(""));

		return direccion;
	}

	private static void enunciadoPoblacionCliente() {
		System.out.println("Inserte la POBLACION del cliente");
		System.out.println("--------------------------------------");
	}

	private static String introducePoblacionDelCliente() {
		String poblacion;
		do {
			enunciadoPoblacionCliente();
			poblacion = escribirYComprobarString();

		} while (poblacion.trim().equals(""));

		return poblacion;
	}

	private static void enunciadoTelefonoCliente() {
		System.out.println("Inserte el TELEFONO del cliente");
		System.out.println("--------------------------------------");
	}

	private static String introduceTelefonoDelCliente() {
		int opcion = 0;
		do {
			enunciadoTelefonoCliente();
			try {
				opcion = Integer.parseInt(inserccionDatoStringAuxiliar());
				return opcion + "";
			} catch (Exception e) {
				mensajeDeError();
			}

		} while (true);
	}

	private static void enunciadoNIFCliente() {
		System.out.println("Inserte el NIF del cliente");
		System.out.println("--------------------------------------");
	}

	private static String introduceNIFDelCliente() {
		String nif;
		do {
			enunciadoNIFCliente();
			nif = escribirYComprobarString();

		} while (nif.trim().equals(""));

		return nif;
	}

	private static String escribirYComprobarString() {
		String aux = "";
		aux = scanner.nextLine();
		if (aux.trim().equals("")) {
			mensajeDeError();
		}
		return aux;
	}

	private static void mensajeDeError() {
		// System.out.println("|--------------------------------------|");
		System.out.println("ERROR:								   ");
		System.out.println("Se ha introducido un caracter erroneo.");
		System.out.println("Introduzca de nuevo el dato correcto. ");
		System.out.println("--------------------------------------");
	}

	private static String inserccionDatoStringAuxiliar() {
		String opcionRecogida = scanner.nextLine();
		return opcionRecogida;
	}

	private static void introduceDatosEnElArchivoBinario(String nombreFichero) {// como nuestro fichero siempre sera el
																				// primer

		System.out.println(nombreFichero);
		try {
			FileOutputStream fileout = new FileOutputStream(nombreFichero);
			ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
			for (int i = 0; i < clientes.size(); i++) { // recorro los arrays
				ClientesDto c = new ClientesDto(clientes.get(i).getIdCliente(), clientes.get(i).getNombre(),
						clientes.get(i).getDireccion(), clientes.get(i).getPoblacion(), clientes.get(i).getTelefono(),
						clientes.get(i).getNif());
				
				dataOS.writeObject(c);
			}
			dataOS.close(); // cerrar stream de salida
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private static String dimeFechaActualConFormato() {// pedimos al sistema la fecha con el formato dd/mm/aaaa y lo
														// modificamos al formato ddmmaa requerido
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String today = formatter.format(date);
		return dimeDiaFecha(today.toString()) + "" + dimeMesFecha(today.toString()) + ""
				+ dimeAnyoFecha(today.toString());
	}

	private static String dimeDiaFecha(String fecha) {
		return fecha.substring(0, 2);
	}

	private static String dimeMesFecha(String fecha) {
		return fecha.substring(3, 5);
	}

	private static String dimeAnyoFecha(String fecha) {
		return fecha.substring(8, 10);
	}

	// mira los archivos con la misma fecha
	private static String dimeContadorDiario() {
		int i = 0;
		int v = 1;
		String version = "01";// version que se enviara despues del metodo, si no existe ningun archivo con la
								// misma fecha, se enviara la version 01
		String fechaActual = dimeFechaActualConFormato();// pedimos la fecha actual para compararla con las fechas de
															// los archivos ya escritos
		String carpetaActual = System.getProperty("user.dir");// direccion de la carpeta actual
		File carpeta = new File(carpetaActual); // cargamos la carpeta actual

		String[] listadoTXT = carpeta.list(); // hacemos un "dir" dentro de la carpeta para que nos muestren los

		do {
			try {
				while (i < listadoTXT.length) {// buscamos dentro del array un archivo
					String fechaArchivoString = listadoTXT[i].substring(8, 14);
					if (fechaActual.equals(fechaArchivoString)) {
						int aux = Integer.parseInt(listadoTXT[i].substring(15, 17));
						if (v < aux) {
							v = aux;
						}
					}
					i++;
				}
			} catch (Exception e) {
				i++;
			}

		} while (i != (listadoTXT.length));
		if (v >= 9)
			version = "" + (v + 1);
		else
			version = "0" + (v + 1);
		return version;
	}

}
