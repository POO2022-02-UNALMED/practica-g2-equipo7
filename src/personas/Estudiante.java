package personas;

import gestion.Asignatura;
<<<<<<< Updated upstream
import org.javatuples.Pair;
=======
<<<<<<< HEAD
import gestion.LineasEnfasis;
=======
import org.javatuples.Pair;
>>>>>>> 63903186f37eb145a38c7e380941c16c95f40433
>>>>>>> Stashed changes

import java.util.*;

public class Estudiante extends Persona {

	private ArrayList<Pair<Asignatura, float>> asignaturasInscritas;
	private float promedio;
	private int semestre;
	private LineasEnfasis lineaEnfasis;
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
		ArrayList<Asignatura> listaEnfasis = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listado = Asignatura.getListaAsignaturas();
		for (Asignatura i: listado) {
			if (i.getLineaEnfasis().equals(lineaEnfasis)){
				listaEnfasis.add(i);
			}
		}
		return listado;
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

	public LineasEnfasis getLineaEnfasis() {
		return lineaEnfasis;
	}

	public void setLineaEnfasis(LineasEnfasis lineaEnfasis) {
		this.lineaEnfasis = lineaEnfasis;
	}

	public ArrayList<Asignatura> getAsignaturasAprobadas() {
		return asignaturasAprobadas;
	}

	public void setAsignaturasAprobadas(ArrayList<Asignatura> asignaturasAprobadas) {
		this.asignaturasAprobadas = asignaturasAprobadas;
	}
}
