package personas;

import Persona;
import gestion.Asignatura;

import java.util.*;

public class Estudiante extends Persona {

	private ArrayList<Dictionary> asignaturasInscritas;
	private float promedio;
	private int semestre;
	private String LineaEnfasis;
	private ArrayList<Asignatura> asignaturasAprobadas;
	
	@Override
	public float calcularPromedio() {

	}
	
	public String calidadEstudiante() {
		
	}
	
	public ArrayList<Asignatura> recomendarAsignaturas(){
		
	}

	public ArrayList<Dictionary> getAsignaturasInscritas() {
		return asignaturasInscritas;
	}

	public void setAsignaturasInscritas(ArrayList<Dictionary> asignaturasInscritas) {
		this.asignaturasInscritas = asignaturasInscritas;
	}

	public float getPromedio() {
		return promedio;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getLineaEnfasis() {
		return LineaEnfasis;
	}

	public void setLineaEnfasis(String lineaEnfasis) {
		LineaEnfasis = lineaEnfasis;
	}

	public ArrayList<Asignatura> getAsignaturasAprobadas() {
		return asignaturasAprobadas;
	}

	public void setAsignaturasAprobadas(ArrayList<Asignatura> asignaturasAprobadas) {
		this.asignaturasAprobadas = asignaturasAprobadas;
	}
}
