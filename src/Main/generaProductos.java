package Main;


import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;
/*
 		Formato fecha ddmmaa     (hecho)
 		introducir datos por teclado
 		seleccionar contador
 		poner datos en fichero
  */
public class generaProductos {
	static Scanner scanner = new Scanner(System.in);
	static String fechaActual = dimeFechaActualConFormato();
	
//genera productos leyendo de teclado y crea un archivo binario 
	public static void main(String[] args) {
		boolean salir = false;
		
		
		do {
			muestraMenu();
			introduceOpcion();
			String nombreFichero = "PRODUCTOS" + fechaActual + "_" + dimeContadorDiario()+".bin";
			
		} while (!salir);

	}

	

	// muestra por teclado el menu (crear producto, "mostrar productos del archivo",
	// salir)
	private static void muestraMenu() {
		System.out.println("Selecciona una opcion : ");
		System.out.println("1.- Crear un producto");
		System.out.println("2.- Salir");
		
	}
	
	private static int introduceOpcion() {
		int opcion = 0;
		String opcionRecogida;
		do {
			System.out.println("Inserte opcion");
			opcionRecogida = scanner.nextLine();
			try {
				opcion = Integer.parseInt(opcionRecogida);
				if (opcion==1  || opcion == 2) {
					System.out.println("Se ha introducido correctamente el dato");
				}else {
					System.out.println("Se ha introducido un valor numerico distinto a 1 o 2");
				}
			} catch (Exception e) {
				System.out.println("La opcion insertado no es un numero");
			}
		} while (opcion!=1||opcion!=2);
		return opcion;
	}
	
	private static String dimeFechaActualConFormato() {
		Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return dimeDiaFecha(fecha.toString())+""+dimeMesFecha(fecha.toString())+""+dimeAnyoFecha(fecha.toString());
	}
	
	private static int dimeDiaFecha(String fecha) {
		return Integer.parseInt(fecha.substring(8));
	}
	private static int dimeMesFecha(String fecha) {
		return Integer.parseInt(fecha.substring(5, 7));
	}
	private static int dimeAnyoFecha(String fecha) {
		return Integer.parseInt(fecha.substring(2, 4));
	}


	//mira los archivos con la misma fecha
	private static String dimeContadorDiario() {
		String version = "";
		
		
		return version;
	}
	
	

}
