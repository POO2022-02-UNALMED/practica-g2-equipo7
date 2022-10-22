package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Estudiante extends Persona implements Serializable, Comparable<Estudiante> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Asignatura, Float> asignaturasInscritas;
	private float promedio;
	private int semestre;
	private LineasEnfasis lineaEnfasis;
	private ArrayList<Asignatura> asignaturasAprobadas;
	private Facultad facultad;
	public static ArrayList<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
	int estrato;
	float ingresosFamiliares;
	boolean colegioPublico;
	int numeroFamiliares;

	public Estudiante(int documento, String nombre, int edad, HashMap<Asignatura, Float> asignaturasInscritas,
			float promedio, int semestre, LineasEnfasis lineaEnfasis, ArrayList<Asignatura> asignaturasAprobadas,
			int estrato, float ingresosFamiliares, boolean colegioPublico, int numeroFamiliares) {
		super(documento, nombre, edad);
		this.asignaturasInscritas = asignaturasInscritas;
		this.promedio = this.calcularPromedio();
		this.semestre = semestre;
		this.lineaEnfasis = lineaEnfasis;
		this.asignaturasAprobadas = asignaturasAprobadas;
		this.facultad = null;
		this.estrato = estrato;
		this.ingresosFamiliares = ingresosFamiliares;
		this.colegioPublico = colegioPublico;
		this.numeroFamiliares = numeroFamiliares;
		Estudiante.listaEstudiantes.add(this);
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}


	public float calcularPromedio() {
		float promedio = 0;
		int creditosInscritos = 0;

		if (this.asignaturasInscritas.size() == 0) {
			return 3f;
		}

		for (Asignatura informacionAsignatura : asignaturasInscritas.keySet()) {

			float nota = asignaturasInscritas.get(informacionAsignatura);
			int creditos = informacionAsignatura.getCreditos();

			creditosInscritos += creditos;
			promedio += creditos * nota;
		}

		promedio = promedio / creditosInscritos;
		return promedio;
	}

	public CalidadEstudiante calidadEstudiante() {
		return CalidadEstudiante.ObtenerCalidadEstudiante(this.promedio);
	}

	// Funcionalidad posición en el semestre
	public String posicionSemestre(Estudiante estudiante) {
		int posEstudiante = 1;

		for (Facultad f : Facultad.getListaFacultades()) {
			if (f.getEstudiantes().contains(estudiante)) {
				for (Estudiante e : f.getEstudiantes()) {
					if (e.getSemestre() == estudiante.getSemestre()) {
						if (e.getPromedio() > estudiante.getPromedio()) {
							posEstudiante += 1;
						}
					}
				}
				return "Posición " + posEstudiante + " entre estudiantes del semestre " + estudiante.getSemestre()
						+ " de la facultad " + f.getNombre();
			}
		}
		return "Estudiante no encontrado";
	}

	// Funcionalidad Recomendacion de asignaturas por linea de enfasis
	public ArrayList<Asignatura> RecomendarAsignaturas() {
		ArrayList<Asignatura> listaRecomendar = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listaEnfasis = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listado = Asignatura.getListaAsignaturas();

		for (Asignatura i : listado) {
			if (lineaEnfasis != null && i.getLineaEnfasis() != null) {
				if (i.getLineaEnfasis().equals(lineaEnfasis) && !asignaturasAprobadas.contains(i)
						&& !asignaturasInscritas.containsKey(i)) {
					listaEnfasis.add(i);
				}
			}
		}

		for (Asignatura i : listaEnfasis) {
			if (i.getPrerrequisitos() != null) {
				if (asignaturasAprobadas.containsAll(i.getPrerrequisitos())) {
					listaRecomendar.add(i);
				}
			}

			else {
				listaRecomendar.add(i);
			}
		}

		return listaRecomendar;
	}

	// Funcionalidad Recomendacion de asignaturas Basicas
	public ArrayList<Asignatura> RecomendarAsignaturasBasicas() {
		ArrayList<Asignatura> listaRecomendar = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listado = Asignatura.getListaAsignaturas();
		ArrayList<Asignatura> listadoBasicas = new ArrayList<Asignatura>();

		for (Asignatura e : listado) {
			if (e.getLineaEnfasis() == LineasEnfasis.BASICAS && !asignaturasAprobadas.contains(e)
					&& !asignaturasInscritas.containsKey(e)) {
				listadoBasicas.add(e);
			}
		}

		for (Asignatura e : listadoBasicas) {
			if (e.getPrerrequisitos() != null) {
				if (asignaturasAprobadas.containsAll(e.getPrerrequisitos())) {
					listaRecomendar.add(e);
				}
			}

			else {
				listaRecomendar.add(e);
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

	public static ArrayList<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public static void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		Estudiante.listaEstudiantes = listaEstudiantes;
	}

	@Override
	public int compareTo(Estudiante o) {
		return (int) ((o.getPromedio() * 1000) - (this.getPromedio() * 1000));
	}

	public int getEstrato() {
		return estrato;
	}

	public void setEstrato(int estrato) {
		this.estrato = estrato;
	}

	public float getIngresosFamiliares() {
		return ingresosFamiliares;
	}

	public void setIngresosFamiliares(float ingresosFamiliares) {
		this.ingresosFamiliares = ingresosFamiliares;
	}

	public boolean isColegioPublico() {
		return colegioPublico;
	}

	public void setColegioPublico(boolean colegioPublico) {
		this.colegioPublico = colegioPublico;
	}

	public int getNumeroFamiliares() {
		return numeroFamiliares;
	}

	public void setNumeroFamiliares(int numeroFamiliares) {
		this.numeroFamiliares = numeroFamiliares;
	}

}
