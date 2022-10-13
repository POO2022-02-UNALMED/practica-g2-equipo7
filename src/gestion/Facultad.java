package gestion;

import java.util.*;
import personas.Estudiante;
import personas.Profesor;

public class Facultad {
	private String nombre;
	private ArrayList<Profesor> profesoresVinculados = new ArrayList<Profesor>();
	private ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
	private static ArrayList<Facultad> facultades = new ArrayList<>();

	public Facultad(String nombre, ArrayList<Profesor> profesoresVinculados, ArrayList<Estudiante> estudiantes) {
		this.nombre = nombre;
		this.profesoresVinculados = profesoresVinculados;
		this.estudiantes = estudiantes;
		facultades.add(this);
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
	}

	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public static ArrayList<Facultad> getFacultades() {
		return facultades;
	}

	public static void setFacultades(ArrayList<Facultad> facultades) {
		Facultad.facultades = facultades;
	}
}
