package main;

import java.util.List;

import dao.AccesoJugador;
import modelo.GolesEquipo;
import modelo.Jugador;
import entrada.Teclado;

/*
 * @author Beni Burdet DAM 1A
 */

public class MenuJugador {

	public static int menuOpciones() {
		System.out.println("\n(0) Salir de la tabla.");
		System.out.println("(1) Insertar un jugador en la base de datos.");
		System.out.println("(2) Actualizar un jugador, por código, de la base de datos.");
		System.out.println("(3) Eliminar un jugador, por código, de la base de datos.");
		System.out.println("(4) Consultar un jugador, por código, de la base de datos.");
		System.out.println("(5) Consultar todos los jugadores de la base de datos ordenados por posicion.");
		System.out.println("(6) Exportar los jugadores de la base de datos al fichero de texto.");
		System.out.println("(7) Importar los jugadores del fichero a la base de datos.");
		System.out.println("(8) Consultar goles anotados y recibidos de un equipo durante una temporada.");

		int opcion = Teclado.leerEntero("¿Opción (0-8)?");
		return opcion;
	}

	public static void gestionarMenu() {
		int opcion = -1;

		do {

			opcion = menuOpciones();
			switch (opcion) {
			case 0:
				break;

			case 1:
				String nombre = Teclado.leerCadena("Introduce el nombre del jugador:");
				String fechaNacimiento = Teclado.leerCadena("Introduce fecha de nacimiento del jugador:");
				String nacionalidad = Teclado.leerCadena("Introduce la nacionalidad del jugador:");
				String posicion = Teclado.leerCadena("Introduce la posicion del jugador:");
				
				Jugador jugador = new Jugador(nombre, fechaNacimiento, nacionalidad, posicion);
				boolean insertado = AccesoJugador.insertar(jugador);
				if(insertado) {
					System.out.println("Se ha insertado un jugador en la base de datos.");
				}else {
					System.out.println("Ya existe ese jugador en la base de datos.");
				}
				break;
				
			case 2:
				int codigo = Teclado.leerEntero("Codigo del jugador a actualizar:");
				nombre = Teclado.leerCadena("Introduce el nombre del jugador:");
				fechaNacimiento = Teclado.leerCadena("Introduce fecha de nacimiento del jugador:");
				nacionalidad = Teclado.leerCadena("Introduce la nacionalidad del jugador:");
				posicion = Teclado.leerCadena("Introduce la posicion del jugador:");
				
				Jugador jugador2 = new Jugador(codigo, nombre, fechaNacimiento, nacionalidad, posicion);
				boolean actualizado = AccesoJugador.actualizar(jugador2);
				if(actualizado) {
					System.out.println("Se ha actualizado un jugador en la base de datos.");
				}else {
					System.out.println("No existe un jugador con ese código en la base de datos.");
				}
				break;
				
			case 3:
				codigo = Teclado.leerEntero("Codigo del jugador a eliminar:");
				
				boolean eliminado = AccesoJugador.eliminar(codigo);
				if(eliminado) {
					System.out.println("Se ha eliminado un jugador en la base de datos.");
				}else {
					System.out.println("No existe un jugador con ese código en la base de datos.");
				}
				break;
				
			case 4:
				codigo = Teclado.leerEntero("Codigo del jugador a consultar:");
				Jugador jugador3 = AccesoJugador.consultar(codigo);
				
				if(jugador3 == null) {
					System.out.println("No existe un jugador con ese código en la base de datos.");
				}else {
					System.out.println(jugador3);
				}
				break;
				
			case 5:
				List<Jugador> jugadores = AccesoJugador.consultarTodos();
				if(jugadores == null) {
					System.out.println("No hay jugadores en la base de datos.");
				}else {
					System.out.println(jugadores);
				}
				
				break;
				
			case 6:
				boolean escrito = AccesoJugador.exportarTabla();
				if(escrito) {
					System.out.println("Se ha exportado la tabla al fichero.");
				}else {
					System.out.println("No se ha podido exportar la tabla al fichero.");
				}
				break;
				
			case 7:
				List<Jugador> jugadores_importados = AccesoJugador.importarTabla();
				if(jugadores_importados == null) {
					System.out.println("No se han importado jugadores del fichero de texto.");
				}else {
					System.out.println(jugadores_importados);
				}
				break;
				
			case 8:
				codigo = Teclado.leerEntero("Codigo del equipo:");
				int tempor = Teclado.leerEntero("Temporada: ");
				List<GolesEquipo> puntos = AccesoJugador.consultarGolesEquipo(codigo, tempor);
				if(puntos == null) {
					System.out.println("No hay jugadores en la base de datos.");
				}else {
					System.out.println(puntos);
				}
				
				break;
			default:
				System.out.println("La opción debe estar comprendida entre 0 y 8.");
			
			}
		} while (opcion != 0);
	}
	
	public static void main(String[] args) {
		gestionarMenu();
	}
}
