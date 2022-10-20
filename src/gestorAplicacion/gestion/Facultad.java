package gestorAplicacion.gestion;

import java.io.Serializable;
import java.util.*;

import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Facultad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private ArrayList<Profesor> profesoresVinculados;
	private ArrayList<Estudiante> estudiantes;
	private static ArrayList<Facultad> listaFacultades = new ArrayList<Facultad>();

	public Facultad(String nombre, ArrayList<Profesor> profesoresVinculados, ArrayList<Estudiante> estudiantes) {
		this.nombre = nombre;
		this.setProfesoresVinculados(profesoresVinculados);
		this.setEstudiantes(estudiantes);
		listaFacultades.add(this);
	}


	public void vincularProfesor(Profesor profesor) {
		profesoresVinculados.add(profesor);
	}

	public void crearAsignatura(Asignatura asignatura) {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Profesor> getProfesoresVinculados() {
		return profesoresVinculados;
	}

	public void setProfesoresVinculados(ArrayList<Profesor> profesoresVinculados) {
		this.profesoresVinculados = profesoresVinculados;
		for (Profesor p: profesoresVinculados) {p.setFacultad(this);}
	}

	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		for (Estudiante e: estudiantes) {e.setFacultad(this);}

		this.estudiantes = estudiantes;
	}

	public static ArrayList<Facultad> getListaFacultades() {
		return listaFacultades;
	}

	public static void setListaFacultades(ArrayList<Facultad> listaFacultades) {
		Facultad.listaFacultades = listaFacultades;
	}
}
