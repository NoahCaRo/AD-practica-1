package Main;


import java.io.File;
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
		
		/*idProducto int not NULL PRIMARY KEY,
	    descripcion VARCHAR(50),
	    stockAnual int,
	    pvp int*/
		
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
		String version = "01";//version que se enviara despues del metodo, si no existe ningun archivo con la misma fecha, se enviara la version 01
		String fechaActual = dimeFechaActualConFormato();//pedimos la fecha actual para compararla con las fechas de los archivos ya escritos
		String carpetaActual = System.getProperty("user.dir");//direccion de la carpeta actual
		File carpeta = new File(carpetaActual);		//cargamos la carpeta actual
		
	 	String[] listadoTXT =  carpeta.list(); 		//hacemos un "dir" dentro de la carpeta para que nos muestren los 
	 	
	 	for (int i = 0; i < listadoTXT.length; i++) {
			if(fechaActual.equals(listadoTXT[i].substring(11, 15))) {
				int v = Integer.parseInt(listadoTXT[i].substring(16, 18));
				version = "0"+v;
			}
		}
		return version;
	}
	
	

}
