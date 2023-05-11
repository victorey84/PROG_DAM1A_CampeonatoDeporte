package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConfigBD;
import modelo.Equipo;
import modelo.Partido;

/**
 * @author YASSINE EL ATTAR
 *
 */
public class AccesoPartido {

	//insertar un partido con una sentencia insert 
	//usamos el objeto partido para obtener los valores de un partido
	public static boolean insertarPartido(Partido partido) {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = ConfigBD.abrirConexion();
			System.out.println("Conectado");
			String sentenciaInsertar = "INSERT INTO partido (codigo_equipo_local, codigo_equipo_visitante, a�o_temporada, fecha, puntuacion_local, puntuacion_visitante) "
					+ "VALUES (?,?,?,?,?,?)";

			System.out.println(sentenciaInsertar);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
			sentencia.setInt(1, partido.getEquipoLocal().getCodigo());
			sentencia.setInt(2, partido.getEquipoVisitante().getCodigo());
			sentencia.setDouble(3, partido.getAñoTemporada());
			sentencia.setString(4, partido.getFecha());
			sentencia.setInt(5, partido.getPuntuacionLocal());
			sentencia.setInt(6, partido.getPuntuacionVisitante());
			
			
			int filasInsertadas = sentencia.executeUpdate();
			if (filasInsertadas == 0) {
				System.out.println("Ya existe ese partido en la base de datos.");
				return false;
			} else {
				System.out.println("Se ha insertado el partido en la base de datos.");
				return true;
			}
		}

		catch (ClassNotFoundException cnfe) {
			System.out.println("Error al cargar el conector de SQLite: " + cnfe.getMessage());
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException sqle) {
				System.out.println("Error al cerrar la base de datos: " + sqle.getMessage());
				sqle.printStackTrace();
			}
		}

		return false;
	}
	
	//actualizar un partido usando un objeto
	//el usuario proporciona un partido como objeto
	//proporciona el SET y las claves primarias en un solo objeto
	public static boolean actualizarPartido(Partido partido) {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = ConfigBD.abrirConexion();
			System.out.println("Conectado");
		
			String sentenciaInsertar = 
					"UPDATE partido "
					+ "SET fecha = ? , "
					+ "SET puntuacion_local = ?, "
					+ "SET puntuacion_visitante = ?, "
					+ "WHERE codigo_equipo_local = ?, "
					+ "and codigo_equipo_visitante = ?, "
					+ "and a�o_temporada = ?,";
			
			System.out.println(sentenciaInsertar);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
			sentencia.setInt(4, partido.getEquipoLocal().getCodigo());
			sentencia.setInt(5, partido.getEquipoVisitante().getCodigo());
			sentencia.setDouble(6, partido.getAñoTemporada());
			sentencia.setString(1, partido.getFecha());
			sentencia.setInt(2, partido.getPuntuacionLocal());
			sentencia.setInt(3, partido.getPuntuacionVisitante());
			
			
			int filasInsertadas = sentencia.executeUpdate();
			if (filasInsertadas == 0) {
				System.out.println("Error al actualizar");
				return false;
			} else {
				System.out.println("Se ha actualizado el partido en la base de datos.");
				return true;
			}
		}

		catch (ClassNotFoundException cnfe) {
			System.out.println("Error al cargar el conector de SQLite: " + cnfe.getMessage());
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException sqle) {
				System.out.println("Error al cerrar la base de datos: " + sqle.getMessage());
				sqle.printStackTrace();
			}
		}
		return false;
	}

	//para borrar un partido usamos un objeto partido con solo las claves primarias
	public static boolean eliminarPartido(Partido partidoEliminar) {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = ConfigBD.abrirConexion();
			System.out.println("Conectado");
			
			String sentenciaInsertar = 
					"DELETE FROM partido "
					+ "WHERE codigo_equipo_local = ?, "
					+ "and codigo_equipo_visitante = ?, "
					+ "and a�o_temporada = ?,";
					
			System.out.println(sentenciaInsertar);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
			sentencia.setInt(1, partidoEliminar.getEquipoLocal().getCodigo());
			sentencia.setInt(2, partidoEliminar.getEquipoVisitante().getCodigo());
			sentencia.setDouble(3, partidoEliminar.getAñoTemporada());
			
			int filasInsertadas = sentencia.executeUpdate();
			if (filasInsertadas == 0) {
				System.out.println("Error al intentar borrar el partido de la base de datos.");
				return false;
			} else {
				System.out.println("Se ha borrado el partido en la base de datos.");
				return true;
			}
		}

		catch (ClassNotFoundException cnfe) {
			System.out.println("Error al cargar el conector de SQLite: " + cnfe.getMessage());
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException sqle) {
				System.out.println("Error al cerrar la base de datos: " + sqle.getMessage());
				sqle.printStackTrace();
			}
		}
		return false;
	}
	
	//una consulta 
	public static Partido consultarPartido(Partido partidoEntrada) {
		Partido partido = null;
		Equipo equipoLocal = null;
		Equipo equipoVisitante = null;
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaConsultar = 
					"SELECT "
					+ "el.codigo as codigoLocal"
					+ "el.nombre as nombreLocal"
					+ "el.añoFundacion as añoFundacionLocal"
					+ "el.lugarSede as lugarSedeLocal"
					+ "el.estadio as estadioLocal"
					+ "el.sociosAficionado as sociosAficionadosLocal "
					
					+ "ev.codigo as codigoVisitante"
					+ "ev.nombre as nombreVisitante"
					+ "ev.añoFundacion as añoFundacionVisitante"
					+ "ev.lugarSede as lugarSedeVisitante"
					+ "ev.estadio as estadioVisitante"
					+ "ev.sociosAficionados as sociosAficionadosVisitante"
					
					+ "p.año_temporada, "
					+ "p.fecha, "
					+ "p.puntuacion_local, "
					+ "p.puntuacion_visitante "
					+ "FROM partido p join equipo el "
					+ "on p.codigoLocal = el.codigoLocal"
					+ "join equipo ev"
					+ "on p.codigoVisitante = ev.codigoVisitante"
					+ "WHERE p.codigoLocal = ?, "
					+ "and p.codigoVisitante = ?, "
					+ "and p.año_temporada = ?"
					+ "ORDER BY fecha";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaConsultar);
			sentencia.setInt(1, partidoEntrada.getEquipoLocal().getCodigo());
			sentencia.setInt(2, partidoEntrada.getEquipoVisitante().getCodigo());
			sentencia.setDouble(3, partidoEntrada.getAñoTemporada());
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while (resultados.next()) {
				
				int codigoLocal = resultados.getInt("codigoLocal");
				String nombreLocal = resultados.getString("nombreLocal");
				int añoFundacionLocal = resultados.getInt("añoFundacionLocal");
				String lugarSedeLocal = resultados.getString("lugarSedeLocal");
				String estadioLocal = resultados.getString("estadioLocal");
				int sociosAficionadosLocal = resultados.getInt("sociosAficionadosLocal");
				
				int codigoVisitante = resultados.getInt("codigoVisitante");
				String nombreVisitante = resultados.getString("nombreVisitante");
				int añoFundacionVisitante = resultados.getInt("añoFundacionVisitante");
				String lugarSedeVisitante = resultados.getString("lugarSedeVisitante");
				String estadioVisitante = resultados.getString("estadioVisitante");
				int sociosAficionadosVisitante = resultados.getInt("sociosAficionadosVisitante");
				
				
				int año_temporada = resultados.getInt("año_temporada");
				String fecha = resultados.getString("fecha"); 
				int puntuacion_local = resultados.getInt("puntuacion_local"); 
				int puntuacion_visitante = resultados.getInt("puntuacion_visitante");
				
		
				

				
				equipoLocal = new Equipo(codigoLocal,nombreLocal,añoFundacionLocal,lugarSedeLocal,estadioLocal,sociosAficionadosLocal);
				equipoVisitante = new Equipo(codigoVisitante,nombreVisitante,añoFundacionVisitante,lugarSedeVisitante,estadioVisitante,sociosAficionadosVisitante);
				partido = new Partido(equipoLocal, equipoVisitante, año_temporada, fecha, puntuacion_local, puntuacion_visitante);
			}
			resultados.close();
			sentencia.close();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (conexion != null) {
				ConfigBD.cerrarConexion(conexion);
			}
		}
		return partido;
	}

	public static List<Partido> consultarTodosPartidos() {
		List<Partido> partidos = new ArrayList<Partido>();
		Equipo equipoLocal = null;
		Equipo equipoVisitante = null;
		Partido partido = null;
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaConsultar = "SELECT "
					+ "el.codigo as codigoLocal"
					+ "el.nombre as nombreLocal"
					+ "el.añoFundacion as añoFundacionLocal"
					+ "el.lugarSede as lugarSedeLocal"
					+ "el.estadio as estadioLocal"
					+ "el.sociosAficionado as sociosAficionadosLocal "
					
					+ "ev.codigo as codigoVisitante"
					+ "ev.nombre as nombreVisitante"
					+ "ev.añoFundacion as añoFundacionVisitante"
					+ "ev.lugarSede as lugarSedeVisitante"
					+ "ev.estadio as estadioVisitante"
					+ "ev.sociosAficionados as sociosAficionadosVisitante"
					
					+ "p.año_temporada, "
					+ "p.fecha, "
					+ "p.puntuacion_local, "
					+ "p.puntuacion_visitante "
					+ "FROM partido p join equipo el "
					+ "on p.codigoLocal = el.codigoLocal"
					+ "join equipo ev"
					+ "on p.codigoVisitante = ev.codigoVisitante"
					+ "WHERE p.codigoLocal = ?, "
					+ "and p.codigoVisitante = ?, "
					+ "and p.año_temporada = ?"
					+ "ORDER BY fecha";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaConsultar);
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while (resultados.next()) {

				int codigoLocal = resultados.getInt("codigoLocal");
				String nombreLocal = resultados.getString("nombreLocal");
				int añoFundacionLocal = resultados.getInt("añoFundacionLocal");
				String lugarSedeLocal = resultados.getString("lugarSedeLocal");
				String estadioLocal = resultados.getString("estadioLocal");
				int sociosAficionadosLocal = resultados.getInt("sociosAficionadosLocal");
				
				int codigoVisitante = resultados.getInt("codigoVisitante");
				String nombreVisitante = resultados.getString("nombreVisitante");
				int añoFundacionVisitante = resultados.getInt("añoFundacionVisitante");
				String lugarSedeVisitante = resultados.getString("lugarSedeVisitante");
				String estadioVisitante = resultados.getString("estadioVisitante");
				int sociosAficionadosVisitante = resultados.getInt("sociosAficionadosVisitante");
				
				
				int año_temporada = resultados.getInt("año_temporada");
				String fecha = resultados.getString("fecha"); 
				int puntuacion_local = resultados.getInt("puntuacion_local"); 
				int puntuacion_visitante = resultados.getInt("puntuacion_visitante");
				
		
				

				
				equipoLocal = new Equipo(codigoLocal,nombreLocal,añoFundacionLocal,lugarSedeLocal,estadioLocal,sociosAficionadosLocal);
				equipoVisitante = new Equipo(codigoVisitante,nombreVisitante,añoFundacionVisitante,lugarSedeVisitante,estadioVisitante,sociosAficionadosVisitante);
				partido = new Partido(equipoLocal, equipoVisitante, año_temporada, fecha, puntuacion_local, puntuacion_visitante);
				 partidos.add(partido);
			}
			resultados.close();
			sentencia.close();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (conexion != null) {
				ConfigBD.cerrarConexion(conexion);
			}
		}
		return  partidos;
	}
	
	public static boolean importarPartidos(String path) {
		BufferedReader flujoEntrada = null;
		//List<Partido> partidos = new ArrayList<Partido>();
		try {
			File fichero = new File(path);
			flujoEntrada = new BufferedReader(new FileReader(fichero));

			String linea = flujoEntrada.readLine();
			while (linea != null) {
				
				Partido partido = new Partido(linea);
				
				insertarPartido(partido);
				linea = flujoEntrada.readLine();

			}
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error al abrir el fichero: " + fnfe.getMessage());
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Error al leer del fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Error al convertir de cadena a numero: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			try {
				if (flujoEntrada != null) {
					flujoEntrada.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean exportarPartidos(String path) {
		BufferedWriter flujoSalida = null;
		List<Partido> partidos = new ArrayList<Partido>();
		try {
			flujoSalida = new BufferedWriter(new FileWriter(path, false));
			for (int i = 0; i < partidos.size(); i++) {

				flujoSalida.write(partidos.get(i).toStringWithSeparators());
				flujoSalida.newLine();
			}

		} catch (IOException ioe) {
			System.out.println("Error al escribir en el fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			try {
				if (flujoSalida != null) {
					flujoSalida.close();
					return true;
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean rollback() {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = ConfigBD.abrirConexion();
			System.out.println("Conectado");
			
			String sentenciaRollback = "ROLLBACK;";
					
			System.out.println(sentenciaRollback);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaRollback);
			
			int filasInsertadas = sentencia.executeUpdate();
			if (filasInsertadas == 0) {
				System.out.println("Error.");
				return false;
			} else {
				System.out.println("RollBack exitoso");
				return true;
			}
		}

		catch (ClassNotFoundException cnfe) {
			System.out.println("Error al cargar el conector de SQLite: " + cnfe.getMessage());
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException sqle) {
				System.out.println("Error al cerrar la base de datos: " + sqle.getMessage());
				sqle.printStackTrace();
			}
		}
		return false;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
