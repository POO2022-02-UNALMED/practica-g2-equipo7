package gestion;
import java.util.*;

public class Facultad {
	private String nombre;
	ArrayList<Profesor> profesoresVinculados = new ArrayList<profesor>();
	ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
	
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
	
}
