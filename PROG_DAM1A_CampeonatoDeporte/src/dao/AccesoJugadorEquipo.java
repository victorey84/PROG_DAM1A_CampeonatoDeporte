/**
 * @author Victor Estella
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.ConfigBD;
import modelo.JugadorEquipo;

public class AccesoJugadorEquipo {
	public static boolean insertar(JugadorEquipo je) {
		PreparedStatement ps = null;
		Connection conexion = null;
		int resultado = 0;
		try {
			conexion = ConfigBD.abrirConexion();
			
			String query = "INSERT INTO jugador_equipo VALUES(?,?,?,?,?);";
			ps = conexion.prepareStatement(query);
			ps.setInt(1, je.getEquipo().getCodigo());
			ps.setInt(2, je.getJugador().getCodigo());
			ps.setInt(3, je.getAñoEntrada());
			ps.setInt(4, je.getAñoSalida());
			ps.setInt(5, je.getPartidosTitular());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la inserción: " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConfigBD.cerrarConexion(conexion);
		}
		return resultado > 0;
	}
}
