package main;

import dao.AccesoEquipo;
import entrada.Teclado;
import modelo.Equipo;
//Hecho por Alvaro Delicado
public class MenuEquipo {

	public static int escribirMenuEquipo() {
		System.out.println();
		System.out.println("(0) Salir de la tabla.");
		System.out.println("(1) Insertar una fila en la tabla equipo");
		System.out.println("(2) Actualizar una fila en la tabla equipo");
		System.out.println("(3) Eliminar una fila en la tabla equipo");
		System.out.println("(4) Consultar todas las filas de la tabla equipo");
		System.out.println("(5) Consultar una fila de la tabla equipo (por codigo)");
		System.out.println("(6) Exportar la tabla equipo a un fichero");
		System.out.println("(7) Importar la tabla equipo desde un fichero");

		int opcion = Teclado.leerEntero("¿Opción (0-7)? ");
		return opcion;
	}

	public static void gestionarMenu() {
		int opcion;

		do {
			opcion = escribirMenuEquipo();
			switch (opcion) {

			case 0:
				break;
			case 1:
				String nombre = Teclado.leerCadena("Dime el nombre del equipo");
				int añoFundacion = Teclado.leerEntero("Dime el año de fundacion del equipo");
				String lugarSede = Teclado.leerCadena("Dime el lugar de la sede del equipo");
				String estadio = Teclado.leerCadena("Dime el estadio del equipo");
				int sociosAficionados = Teclado.leerEntero("Dime el numero de aficionados/socios del equipo");
				Equipo e = new Equipo(nombre, añoFundacion, lugarSede, estadio, sociosAficionados);
				if (AccesoEquipo.insertar(e)) {
					System.out.println("Se ha insertado el equipo correctamente");
				} else {
					System.out.println("No se ha insertado el equipo");
					if (AccesoEquipo.esta(e.getCodigo())) {
						System.out.println("El equipo con ese codigo ya existe");
					}
				}

				break;
			case 2:
				int codigo = Teclado.leerEntero("Dime el codigo del equipo a modificar");
				if (AccesoEquipo.esta(codigo)) {
					String nombre2 = Teclado.leerCadena("Dime el nuevo nombre del equipo");
					int añoFundacion2 = Teclado.leerEntero("Dime el nuevo año de fundacion del equipo");
					String lugarSede2 = Teclado.leerCadena("Dime el nuevo lugar de la sede del equipo");
					String estadio2 = Teclado.leerCadena("Dime el nuevo estadio del equipo");
					int sociosAficionados2 = Teclado
							.leerEntero("Dime el nuevo numero de aficionados/socios del equipo");
					Equipo e2 = new Equipo(nombre2, añoFundacion2, lugarSede2, estadio2, sociosAficionados2);
					if (AccesoEquipo.actualizar(codigo, e2)) {
						System.out.println("Se ha actualizado el equipo correctamente");
					}
				} else {
					System.out.println("El equipo con ese codigo no existe");
				}
				break;
			case 3:
				int codigo2 = Teclado.leerEntero("Dime el codigo del equipo a eliminar");
				if (AccesoEquipo.esta(codigo2)) {
					if (AccesoEquipo.eliminar(codigo2)) {
						System.out.println("Se ha eliminado el equipo correctamente");
					}
				} else {
					System.out.println("El equipo con ese codigo no existe");
				}
				break;
			case 4:
				System.out.println(AccesoEquipo.consultarTodo());
				break;
			case 5:
				int codigo3 = Teclado.leerEntero("Dime el codigo del equipo a consultar");
				if (AccesoEquipo.esta(codigo3)) {
					System.out.println(AccesoEquipo.consultar(codigo3));
				} else {
					System.out.println("El equipo con ese codigo no existe");
				}
				break;
			case 6:
				String cadena = Teclado.leerCadena("Dime la ruta donde quieres exportar la tabla equipo");
				AccesoEquipo.exportarEquipos(cadena);
				break;
			case 7:
				String cadena2 = Teclado.leerCadena("Dime la ruta de donde quieres importar la tabla equipo");
				AccesoEquipo.importarEquipos(cadena2);
				break;
			}
		} while (opcion != 0);

	}

	//public static void main(String args[]) {
	//	MainEquipo.escribirMenuEquipo();
	//	MainEquipo.menuEquipo();
	//
	//}
}
