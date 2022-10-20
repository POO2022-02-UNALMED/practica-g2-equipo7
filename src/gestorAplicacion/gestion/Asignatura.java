package gestorAplicacion.gestion;

import java.io.Serializable;
import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Asignatura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int creditos;
	private String nombre;
	private ArrayList<Profesor> profesor;
	private ArrayList<Asignatura> prerrequisitos;
	private LineasEnfasis lineaEnfasis;
	private static ArrayList<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();

	public Asignatura(String nombre, int creditos, ArrayList<Profesor> profesor, ArrayList<Asignatura> prerrequisitos,
			LineasEnfasis lineaEnfasis) {
		super();
		this.nombre = nombre;
		this.creditos = creditos;
		this.profesor = profesor;
		this.prerrequisitos = prerrequisitos;
		this.lineaEnfasis = lineaEnfasis;
		Asignatura.listaAsignaturas.add(this);
	}

	public Asignatura() {
		this("NR",3, null, null, null);
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public ArrayList<Profesor> getProfesor() {
		return profesor;
	}

	public void setProfesor(ArrayList<Profesor> profesor) {
		this.profesor = profesor;
	}

	public LineasEnfasis getLineaEnfasis() {
		return lineaEnfasis;
	}

	public void setLineaEnfasis(LineasEnfasis lineaEnfasis) {
		this.lineaEnfasis = lineaEnfasis;
	}

	public ArrayList<Asignatura> getPrerrequisitos() {
		return prerrequisitos;
	}

	public void setPrerrequisitos(ArrayList<Asignatura> prerrequisitos) {
		this.prerrequisitos = prerrequisitos;
	}

	public static ArrayList<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public static void setListaAsignaturas(ArrayList<Asignatura> listaAsignaturas) {
		Asignatura.listaAsignaturas = listaAsignaturas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void agregarProfesor(Profesor nombre) {
		this.profesor.add(nombre);
	}

}
