package gestorAplicacion.personas;

import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public abstract class Persona {
	private int documento;
	private String nombre;
	private int edad;

	public Persona(int documento, String nombre, int edad) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.edad = edad;
	}


	public abstract float calcularPromedio();

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}
