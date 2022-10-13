package personas;

import gestion.Asignatura;
import gestion.Facultad;
import gestion.LineasEnfasis;
import gestion.CalidadEstudiante;
import java.util.*;

public class Estudiante extends Persona {

	private HashMap<Asignatura, Float> asignaturasInscritas;
	private float promedio;
	private int semestre;
	private LineasEnfasis lineaEnfasis;
	private ArrayList<Asignatura> asignaturasAprobadas;

	public Estudiante(java.util.HashMap<Asignatura, Float> asignaturasInscritas, float promedio,
					  int semestre, LineasEnfasis lineaEnfasis,
					  java.util.ArrayList<Asignatura> asignaturasAprobadas) {

		this.asignaturasInscritas = asignaturasInscritas;
		this.promedio = promedio;
		this.semestre = semestre;
		this.lineaEnfasis = lineaEnfasis;
		this.asignaturasAprobadas = asignaturasAprobadas;
	}

	@Override
	public float calcularPromedio() {
		float promedio = 0;
		int creditosInscritos = 0;

		for (Asignatura informacionAsignatura: asignaturasInscritas.keySet()) {

			promedio = asignaturasInscritas.get(informacionAsignatura);
			int creditos = informacionAsignatura.getCreditos();

			creditosInscritos += creditos;
			promedio += creditos * nota;
		}

		promedio = promedio / creditosInscritos;
		return promedio;
	}
	
	public String calidadEstudiante() {
		return CalidadEstudiante.ObtenerCalidadEstudiante(this.promedio);
	}

	// Funcionalidad posición en el semestre
	public String posicionSemestre(Estudiante estudiante){
		int posEstudiante = 1;

		for (Facultad f: Facultad.getFacultades()) {
			if(f.getEstudiantes().contains(estudiante)){
				for (Estudiante e: f.getEstudiantes()){
					if(e.getSemestre() == estudiante.getSemestre()){
						if(e.getPromedio() > estudiante.getPromedio()){
							posEstudiante += 1;
						}
					}
				}
				return "Posición " + posEstudiante +
						" entre estudiantes del semestre " + estudiante.getSemestre() +
						" de la facultad " + f.getNombre();
			}
		}
		return "Estudiante no encontrado";
	}

	// Funcionalidad Recomendacion de asignaturas
	public ArrayList<Asignatura> recomendarAsignaturas(){
		ArrayList<Asignatura> listaRecomendar = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listaEnfasis = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listado = Asignatura.getListaAsignaturas();
		
		for (Asignatura i: listado) {
			if (i.getLineaEnfasis().equals(lineaEnfasis) && !asignaturasAprobadas.contains(i)){
				listaEnfasis.add(i);
			}
		}
		
		for (Asignatura i: listaEnfasis) {
			if (asignaturasAprobadas.containsAll(i.getPrerrequisitos())) {
				listaRecomendar.add(i);
			}
		}
		
		return listaRecomendar;
	}

	public HashMap<Asignatura, Float> getAsignaturasInscritas() {
		return asignaturasInscritas;
	}

	public void setAsignaturasInscritas(HashMap<Asignatura, Float> asignaturasInscritas) {
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
