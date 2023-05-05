/**
 * @author Victor Estella
 */
package modelo;

public class JugadorEquipo {
	Equipo equipo;
	Jugador jugador;
	int añoEntrada;
	int añoSalida;
	int partidosTitular;
	
	/**
	 * @param equipo
	 * @param jugador
	 * @param añoEntrada
	 * @param añoSalida
	 * @param partidosTitular
	 */
	public JugadorEquipo(Equipo equipo, Jugador jugador, int añoEntrada, int añoSalida, int partidosTitular) {
		this.equipo = equipo;
		this.jugador = jugador;
		this.añoEntrada = añoEntrada;
		this.añoSalida = añoSalida;
		this.partidosTitular = partidosTitular;
	}

	/**
	 * @return the equipo
	 */
	public Equipo getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	/**
	 * @return the jugador
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * @param jugador the jugador to set
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * @return the añoEntrada
	 */
	public int getAñoEntrada() {
		return añoEntrada;
	}

	/**
	 * @param añoEntrada the añoEntrada to set
	 */
	public void setAñoEntrada(int añoEntrada) {
		this.añoEntrada = añoEntrada;
	}

	/**
	 * @return the añoSalida
	 */
	public int getAñoSalida() {
		return añoSalida;
	}

	/**
	 * @param añoSalida the añoSalida to set
	 */
	public void setAñoSalida(int añoSalida) {
		this.añoSalida = añoSalida;
	}

	/**
	 * @return the partidosTitular
	 */
	public int getPartidosTitular() {
		return partidosTitular;
	}

	/**
	 * @param partidosTitular the partidosTitular to set
	 */
	public void setPartidosTitular(int partidosTitular) {
		this.partidosTitular = partidosTitular;
	}

	@Override
	public String toString() {
		return "JugadorEquipo ["
				+ "equipo=" + equipo
				+ ", jugador=" + jugador
				+ ", añoEntrada=" + añoEntrada
				+ ", añoSalida=" + añoSalida
				+ ", partidosTitular=" + partidosTitular
				+ "]";
	}
	
	
}
