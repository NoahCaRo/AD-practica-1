package Main;

import java.util.Scanner;

public class generaProductos {
	static Scanner scanner = new Scanner(System.in);
	static String fechaActual = dimeFechaActualConFormato();
	
//genera productos leyendo de teclado y crea un archivo binario 
	public static void main(String[] args) {
		boolean salir = false;

		do {
			muestraMenu();
			introduceOpcion();
			String nombreFichero = "PRODUCTOS" + fechaActual + "_" + dimeContadorDiario();
			
		} while (!salir);

	}

	// muestra por teclado el menu (crear producto, "mostrar productos del archivo",
	// salir)
	private static void muestraMenu() {

	}
	private static void introduceOpcion() {
		

	}
	private static String dimeFechaActualConFormato() {
		
		return null;
	}
	//mira los archivos con la misma fecha
	private static String dimeContadorDiario() {
		
		return null;
	}

}
