package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Profesor extends Persona implements Serializable, Comparable<Profesor>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Float> asignaturasDictadas;
	private Facultad facultad;
	private Date fechaIngreso;
	public static ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();


	public Profesor(int documento, String nombre, int edad, HashMap<String, Float> asignaturasDictadas,
			Facultad facultad, Date fechaIngreso) {
		super(documento, nombre, edad);
		this.asignaturasDictadas = asignaturasDictadas;
		this.facultad = facultad;
		this.fechaIngreso = fechaIngreso;
		Profesor.listaProfesores.add(this);
	}

	@Override
	public float calcularPromedio() {
		float sum = 0;
		for (String key : asignaturasDictadas.keySet()) {
			sum += asignaturasDictadas.get(key);
		}
		return sum / asignaturasDictadas.size();
	}

	public HashMap<String, Float> getAsignaturasDictadas() {
		return asignaturasDictadas;
	}

	public void setAsignaturasDictadas(HashMap<String, Float> asignaturasDictadas) {
		this.asignaturasDictadas = asignaturasDictadas;
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public static ArrayList<Profesor> getListaProfesores() {
		return listaProfesores;
	}

	public static void setListaProfesores(ArrayList<Profesor> listaProfesores) {
		Profesor.listaProfesores = listaProfesores;
	}

	@Override
	public int compareTo(Profesor o) {
		return (int) ((o.calcularPromedio()*1000) - (this.calcularPromedio()*1000));
	}
	
	
	
	
}