package modelo;

public class JugadoresTemporada {
	private int añoEntrada;
	private int numJugadores;
	
	public JugadoresTemporada(int añoEntrada, int numJugadores) {
		this.añoEntrada = añoEntrada;
		this.numJugadores = numJugadores;
	}

	public int getAñoEntrada() {
		return añoEntrada;
	}

	public void setAñoEntrada(int añoEntrada) {
		this.añoEntrada = añoEntrada;
	}

	public int getNumJugadores() {
		return numJugadores;
	}

	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
	}

	public String toString() {
		return "JugadoresTemporada [añoEntrada = " + añoEntrada + ", numJugadores = " + numJugadores + "]";
	}
	
	

}
