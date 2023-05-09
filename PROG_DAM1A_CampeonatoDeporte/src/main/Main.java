/**
 * @author Victor Estella
 */
package main;

import entrada.Teclado;

public class Main {

	public static int mostrarMenu() {
		System.out.println();
		System.out.println("(0) Salir del programa.");
		System.out.println("(1) Operar en la tabla equipo.");
		System.out.println("(2) Operar en la tabla jugador.");
		System.out.println("(3) Operar en la tabla jugador_equipo.");
		System.out.println("(4) Operar en la tabla partido.");
		return Teclado.leerEntero("¿Opción (0-4)? ");
	}
	
	public static void main(String[] args) {
		int opcion;
		
		do {
			opcion = mostrarMenu();
			switch (opcion) {
			case 0:
				break;
			case 1:
				MenuEquipo.gestionarMenu();
				break;
			case 2:
				MenuJugador.gestionarMenu();
				break;
			case 3:
				MenuJugadorEquipo.gestionarMenu();
				break;
			case 4:
				MenuPartido.gestionarMenu();
				break;
			default:
				System.out.println("La opción de menú debe estar comprendida entre 0 y 4.");
			}
		} while (opcion != 0);

	}

}
