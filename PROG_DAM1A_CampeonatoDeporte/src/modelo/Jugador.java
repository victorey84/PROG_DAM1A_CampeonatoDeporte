package modelo;



/*
 * Beni Burdet DAM 1�A
 */

public class Jugador {

	private int codigo;
	private String nombre;
	private String fechaNacimiento;
	private String nacionalidad;
	private String posicion;

	public Jugador(int codigo, String nombre, String fecha_nacimiento, String nacionalidad, String posicion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaNacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
	}

	public Jugador(String nombre, String fecha_nacimiento, String nacionalidad, String posicion) {
		super();
		this.nombre = nombre;
		this.fechaNacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha_nacimiento() {
		return fechaNacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fechaNacimiento = fecha_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setSocios_aficionados(String posicion) {
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return "Jugador [codigo=" + codigo + ", nombre=" + nombre + ", fecha_nacimiento=" + fechaNacimiento
				+ ", nacionalidad=" + nacionalidad + ", posicion=" + posicion + "]";
	}

}
