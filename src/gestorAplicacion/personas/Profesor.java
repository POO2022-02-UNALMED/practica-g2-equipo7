package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Profesor extends Persona implements Serializable, Comparable<Profesor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Asignatura, Float> asignaturasDictadas;
	private Facultad facultad;
	private Date fechaIngreso;
	public static ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();

	public Profesor(int documento, String nombre, int edad, HashMap<Asignatura, Float> asignaturasDictadas,
			Facultad facultad, Date fechaIngreso) {
		super(documento, nombre, edad);
		this.asignaturasDictadas = asignaturasDictadas;
		this.facultad = facultad;
		this.fechaIngreso = fechaIngreso;
		this.facultad = null;
		Profesor.listaProfesores.add(this);
	}

	@Override
	public float calcularPromedio() {
		float sum = 0;
		for (Asignatura key : asignaturasDictadas.keySet()) {
			sum += asignaturasDictadas.get(key);
		}
		return sum / asignaturasDictadas.size();
	}

	public HashMap<Asignatura, Float> getAsignaturasDictadas() {
		return asignaturasDictadas;
	}

	public void setAsignaturasDictadas(HashMap<Asignatura, Float> asignaturasDictadas) {
		for (Asignatura key : asignaturasDictadas.keySet()) {
			key.agregarProfesor(this);
		}
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
		return (int) ((o.calcularPromedio() * 1000) - (this.calcularPromedio() * 1000));
	}

	@Override
	public LineasEnfasis getLineaEnfasis() {
		return null;
	}

}