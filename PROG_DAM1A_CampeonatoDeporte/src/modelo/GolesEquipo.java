package modelo;

public class GolesEquipo {

	private double golesAnotados;
	private double golesRecibidos;
	
	public GolesEquipo(double golesAnotados, double golesRecibidos) {
		super();
		this.golesAnotados = golesAnotados;
		this.golesRecibidos = golesRecibidos;
	}

	public double getGolesAnotados() {
		return golesAnotados;
	}

	public void setGolesAnotados(double golesAnotados) {
		this.golesAnotados = golesAnotados;
	}

	public double getGolesRecibidos() {
		return golesRecibidos;
	}

	public void setGolesRecibidos(double golesRecibidos) {
		this.golesRecibidos = golesRecibidos;
	}

	@Override
	public String toString() {
		return "GolesEquipo [Media goles anotados=" + golesAnotados + ", media goles recibidos=" + golesRecibidos + "]";
	}
	
}
