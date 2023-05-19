/**
 * @author Victor Estella
 */
package main;

import java.util.List;

import dao.AccesoEquipo;
import dao.AccesoJugador;
import dao.AccesoJugadorEquipo;
import entrada.Teclado;
import modelo.Equipo;
import modelo.Jugador;
import modelo.JugadorEquipo;
import modelo.JugadoresTemporada;

public class MenuJugadorEquipo {

	public static int mostrarMenu() {
		System.out.println("\n(0) Salir de la tabla.");
		System.out.println("(1) Insertar un jugador en un equipo de la base de datos.");
		System.out.println("(2) Actualizar un jugador en un equipo, por código, de la base de datos.");
		System.out.println("(3) Eliminar un jugador de un equipo, por código, de la base de datos.");
		System.out.println("(4) Consultar un jugador en un equipo, por código, de la base de datos.");
		System.out.println("(5) Consultar todos los jugadores en equipos de la base de datos ordenados por año de entrada.");
		System.out.println("(6) Exportar los jugadores en equipos de la base de datos al fichero de texto.");
		System.out.println("(7) Importar los jugadores en equipos del fichero a la base de datos.");
		System.out.println("(8) Consultar jugadores en equipo por temporada.");
		return Teclado.leerEntero("¿Opción (0-7)? ");
	}
	
	public static void gestionarMenu() {
		int opcion;
		do {
			opcion = mostrarMenu();
			switch (opcion) {
			case 0: {
				break;
			}
			case 1: {
				// (1) Insertar un jugador en un equipo de la base de datos.
				List<Jugador> jugadores = AccesoJugador.consultarTodos();
				System.out.println("Existen los siguientes jugadores:");
				for (Jugador jugador: jugadores) {
					System.out.println(jugador);
				}
				int codigoJugador = Teclado.leerEntero("¿Código de jugador? ");
				Jugador jugadorElegido = null;
				for (Jugador jugador: jugadores) {
					if (jugador.getCodigo() == codigoJugador) {
						jugadorElegido = jugador;
					}
				}
				List<Equipo> equipos = AccesoEquipo.consultarTodo();
				System.out.println("Existen los siguientes equipos:");
				for (Equipo equipo: equipos) {
					System.out.println(equipo);
				}
				int codigoEquipo = Teclado.leerEntero("¿Código de equipo? ");
				Equipo equipoElegido = null;
				for (Equipo equipo: equipos) {
					if (equipo.getCodigo() == codigoEquipo) {
						equipoElegido = equipo;
					}
				}
				int añoEntrada = Teclado.leerEntero("¿Año de entrada? ");
				int añoSalida = Teclado.leerEntero("¿Año de salida? ");
				int partidosTitular = Teclado.leerEntero("¿Partidos en los que ha sido titular? ");
				JugadorEquipo je = new JugadorEquipo(equipoElegido, jugadorElegido, añoEntrada, añoSalida, partidosTitular);
				if (AccesoJugadorEquipo.insertar(je)) {
					System.out.println("Se ha insertado un jugador en equipo en la base de datos.");
				} else {
					System.out.println("No se ha podido insertar.");
				}
				break;
			}
			case 2: {
				// (2) Actualizar un jugador en un equipo, por código, de la base de datos.
				List<Jugador> jugadores = AccesoJugador.consultarTodos();
				System.out.println("Existen los siguientes jugadores:");
				for (Jugador jugador: jugadores) {
					System.out.println(jugador);
				}
				int codigoJugador = Teclado.leerEntero("¿Código de jugador? ");
				Jugador jugadorElegido = null;
				for (Jugador jugador: jugadores) {
					if (jugador.getCodigo() == codigoJugador) {
						jugadorElegido = jugador;
					}
				}
				List<Equipo> equipos = AccesoEquipo.consultarTodo();
				System.out.println("Existen los siguientes equipos:");
				for (Equipo equipo: equipos) {
					System.out.println(equipo);
				}
				int codigoEquipo = Teclado.leerEntero("¿Código de equipo? ");
				Equipo equipoElegido = null;
				for (Equipo equipo: equipos) {
					if (equipo.getCodigo() == codigoEquipo) {
						equipoElegido = equipo;
					}
				}
				if (AccesoJugadorEquipo.consultar(jugadorElegido, equipoElegido) != null) {
					int añoEntrada = Teclado.leerEntero("¿Año de entrada? ");
					int añoSalida = Teclado.leerEntero("¿Año de salida? ");
					int partidosTitular = Teclado.leerEntero("¿Partidos en los que ha sido titular? ");
					JugadorEquipo je = new JugadorEquipo(equipoElegido, jugadorElegido, añoEntrada, añoSalida, partidosTitular);
					if (AccesoJugadorEquipo.actualizar(je)) {
						System.out.println("Se ha actualizado un jugador en equipo en la base de datos.");
					} else {
						System.out.println("No se ha podido actualizar.");
					}
				} else {
					System.out.println("No existe ese jugador en equipo.");
				}
				break;
			}
			case 3: {
				// (3) Eliminar un jugador de un equipo, por código, de la base de datos.
				List<Jugador> jugadores = AccesoJugador.consultarTodos();
				System.out.println("Existen los siguientes jugadores:");
				for (Jugador jugador: jugadores) {
					System.out.println(jugador);
				}
				int codigoJugador = Teclado.leerEntero("¿Código de jugador? ");
				List<Equipo> equipos = AccesoEquipo.consultarTodo();
				System.out.println("Existen los siguientes equipos:");
				for (Equipo equipo: equipos) {
					System.out.println(equipo);
				}
				int codigoEquipo = Teclado.leerEntero("¿Código de equipo? ");
				if (AccesoJugadorEquipo.eliminar(codigoEquipo, codigoJugador)) {
					System.out.println("Se ha eliminado un jugador en equipo de la base de datos.");
				}else {
					System.out.println("No existe ese jugador en equipo en la base de datos.");
				}
				break;
			}
			case 4: {
				// (4) Consultar un jugador en un equipo, por código, de la base de datos.
				List<Jugador> jugadores = AccesoJugador.consultarTodos();
				System.out.println("Existen los siguientes jugadores:");
				for (Jugador jugador: jugadores) {
					System.out.println(jugador);
				}
				int codigoJugador = Teclado.leerEntero("¿Código de jugador? ");
				Jugador jugadorElegido = null;
				for (Jugador jugador: jugadores) {
					if (jugador.getCodigo() == codigoJugador) {
						jugadorElegido = jugador;
					}
				}
				List<Equipo> equipos = AccesoEquipo.consultarTodo();
				System.out.println("Existen los siguientes equipos:");
				for (Equipo equipo: equipos) {
					System.out.println(equipo);
				}
				int codigoEquipo = Teclado.leerEntero("¿Código de equipo? ");
				Equipo equipoElegido = null;
				for (Equipo equipo: equipos) {
					if (equipo.getCodigo() == codigoEquipo) {
						equipoElegido = equipo;
					}
				}
				JugadorEquipo je = AccesoJugadorEquipo.consultar(jugadorElegido, equipoElegido);
				if (je == null) {
					System.out.println("No se ha encontrado ese jugador en equipo.");
				} else {
					System.out.println(je);
				}
				break;
			}
			case 5: {
				// (5) Consultar todos los jugadores en equipos de la base de datos ordenados por año de entrada.
				List<JugadorEquipo> listaJE = AccesoJugadorEquipo.consultarTodo();
				for (JugadorEquipo je: listaJE) {
					System.out.println(je);
				}
				if (listaJE.size() == 0) {
					System.out.println("No jugadores en equipo en la base de datos.");
				} else {
					System.out.println("Se han consultado " + listaJE.size() + " registros.");
				}
				break;
			}
			case 6: {
				// (6) Exportar los jugadores en equipos de la base de datos al fichero de texto.
				if (AccesoJugadorEquipo.exportar()) {
					System.out.println("Se ha exportado la tabla al fichero.");
				} else {
					System.out.println("No se ha podido exportar la table al fichero.");
				}
				break;
			}
			case 7: {
				// (7) Importar los jugadores en equipos del fichero a la base de datos.
				int importados = AccesoJugadorEquipo.importar();
				if (importados > 0) {
					System.out.println("Se han importado " + importados + " jugadores en equipos.");
				} else {
					System.out.println("No se han podido importar jugadores en equipos.");
				}
				break;
			}
			case 8: {
				// (8) Consultar jugadores en equipo por temporada.
				System.out.println("Existen los siguientes equipos:");
				List<Equipo> equipos = AccesoEquipo.consultarTodo();
				for (Equipo equipo: equipos) {
					System.out.println(equipo);
				}
				int codigoEquipo = Teclado.leerEntero("¿Código de equipo? ");
				Equipo equipo = new Equipo(codigoEquipo);
				List<JugadoresTemporada> listaJT = AccesoJugadorEquipo.consultarJugadoresTemporada(equipo);
				for (JugadoresTemporada jt: listaJT) {
					System.out.println(jt);
				}
				break;
			}
			default:
				System.out.println("La opción de menú debe estar comprendida entre 0 y 8.");
			}
		} while (opcion != 0);
	}
	
	public static void main(String[] args) {
		gestionarMenu();

	}

}
