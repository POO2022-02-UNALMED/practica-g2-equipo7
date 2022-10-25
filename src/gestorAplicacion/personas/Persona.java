package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public abstract class Persona implements Serializable {
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
	public abstract Facultad getFacultad();
	public abstract LineasEnfasis getLineaEnfasis();

	
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
