package personas;

import Persona;
import gestion.Asignatura;
import org.javatuples.Pair;

import java.util.*;

public class Estudiante extends Persona {

	private ArrayList<Pair<Asignatura, float>> asignaturasInscritas;
	private float promedio;
	private int semestre;
	private String LineaEnfasis;
	private ArrayList<Asignatura> asignaturasAprobadas;
	
	@Override
	public float calcularPromedio() {
		float promedio = 0;
		int creditosInscritos = 0;

		for (Pair informacionAsignatura: asignaturasInscritas) {
			float nota = informacionAsignatura.getValue1();
			int creditos = informacionAsignatura.getValue0().getCreditos();

			creditosInscritos += creditos;
			promedio += creditos * nota;
		}

		promedio = promedio / creditosInscritos;
		return promedio;
	}
	
	public String calidadEstudiante() {
		
	}
	
	public ArrayList<Asignatura> recomendarAsignaturas(){
		
	}

	public ArrayList<Pair<Asignatura, float>> getAsignaturasInscritas() {
		return asignaturasInscritas;
	}

	public void setAsignaturasInscritas(ArrayList<Pair<Asignatura, float>> asignaturasInscritas) {
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
